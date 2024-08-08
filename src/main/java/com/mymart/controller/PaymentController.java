package com.mymart.controller;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.mymart.model.*;
import com.mymart.repository.ProductsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mymart.repository.AddressRepository;
import com.mymart.service.AddressService;
import com.mymart.service.CartItemService;
import com.mymart.service.OrderItemService;
import com.mymart.service.OrderService;
import com.mymart.service.ProductService;
import com.mymart.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpServletRequest;
@Controller

public class PaymentController {

	@Autowired
	private UserService userService;

	@Autowired
	OrderItemService orderItemService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	private AddressService addressService;



	@Autowired
	private ProductsRepository productsrepo;



	@Autowired
	private CartItemService cartService;
	@Autowired
	private OrderService orderService;
	private RazorpayClient client;
	private static Gson gson = new Gson();

	private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);

	private static final String SECRET_ID = "rzp_test_bwM2xNhKd85xRB";
	private static final String SECRET_KEY = "xsKkMUfuHoW45FrWCwHFGPNv";

	public PaymentController() throws RazorpayException {
		this.client =  new RazorpayClient(SECRET_ID, SECRET_KEY); 
	}


	@RequestMapping(value="/payment")
	public String getHomeInit(@RequestParam("addressId") Integer addressId, Model model, Principal principal) {
		String username = principal.getName();


		User user = userService.findByEmail(username); 
		List<CartItem> cartItems = cartItemService.getAllCartItemsByUser(user);


		double subtotal = cartItemService.calculateSubtotal(cartItems);
		double shipping = cartItemService.calculateShipping(subtotal);
		double total = subtotal + shipping;


		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shipping", shipping);
		model.addAttribute("total", total);
		model.addAttribute("user", user);

		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		if (optionalAddress.isPresent()) {
			model.addAttribute("selectedAddress", optionalAddress.get());
			return "payment"; 
		} else {
			return "redirect:/error"; 
		}
	}

	@RequestMapping(value="/createPayment", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createOrder(@RequestBody Orders orders) {

		try {


			Order order = createRazorPayOrder( orders.getAmount() );
			RazorPay razorPay = getRazorPay((String)order.get("id"), orders);



			return new ResponseEntity<String>(gson.toJson(getResponse(razorPay, 200)),
					HttpStatus.OK);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(gson.toJson(getResponse(new RazorPay(), 500)),
				HttpStatus.EXPECTATION_FAILED);
	}






	private Response getResponse(RazorPay razorPay, int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setRazorPay(razorPay);
		return response;
	}	

	private RazorPay getRazorPay(String orderId, Orders orders) {
		RazorPay razorPay = new RazorPay();
		razorPay.setApplicationFee(convertRupeeToPaise(orders.getAmount()));

		razorPay.setPurchaseDescription("PRODUCT PURCHASES");
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(SECRET_ID);
		razorPay.setTheme("white");
		razorPay.setMerchantName("MyMart");
		razorPay.setNotes("notes"+orderId);

		return razorPay;
	}

	private Order createRazorPayOrder(String amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", convertRupeeToPaise(amount));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1);  
		return client.orders.create(options);
	}



	private String convertRupeeToPaise(String paise) {
		if (paise == null || paise.isEmpty()) {
			throw new IllegalArgumentException("Invalid input: amount cannot be empty or null.");
		}
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
	}


	@PostMapping("/storeFormData")
	public ResponseEntity<String> storeFormData( @RequestBody Orders orders) {
		try {


			orderService.saveOrder(orders);

			return new ResponseEntity<>("Form data stored successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error storing form data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/placeOrder/Online")
	public String placeOrder(@RequestParam("addressId") int addressId, Orders orders, Principal principal,Model model,HttpServletRequest request,@RequestParam("paymentId") String paymentId,
			@RequestParam("orderId") String orderId) {
		try {
			if (principal == null) {
				return "redirect:/login"; // Redirect to login if user is not authenticated
			}

			String username = principal.getName(); // Get the username from Principal
			User user = userService.findByEmail(username);
			if (user == null) {
				return "redirect:/login"; // Redirect to login if user not found
			}

			orders.setUser(user); // Set the user in the order object



			Address shippingAddress = addressService.findById(addressId); // Fetch the Address object by ID
			if (shippingAddress == null) {
				return "Invalid address ID";
			}

			orders.setShippingAddresses(shippingAddress); // Set the shipping address in the Orders object
			List<CartItem> cartItems = cartService.getAllCartItemsByUser(user);
			double subtotal = cartService.calculateSubtotal(cartItems);


			if (subtotal == 0) {
				model.addAttribute("error", "Product not added. Please add products to Place the order.");
				return "redirect:/cart"; 
			}



			double shipping = cartService.calculateShipping(subtotal);

			double total = subtotal + shipping;


			model.addAttribute("subtotal", subtotal);
			model.addAttribute("shipping", shipping);
			model.addAttribute("total", total);

			orders.setSubtotal(subtotal);

			orders.setShippingCharges(shipping); // Set the shipping charges in the Orders object


			orders.setTotalAmount(total);
			orders.setAmount(String.valueOf(total));

			Optional<Address> optionalAddress = addressRepository.findById(addressId);

			model.addAttribute("selectedAddress", optionalAddress.get());

			if (orders.getShippingMethod() == null) {
				orders.setShippingMethod(ShippingMethod.STANDARD);
			}
			orders.setStatus(OrderStatus.PLACED);


			Order order = createRazorPayOrder( orders.getAmount() );
			RazorPay razorPay = getRazorPay((String)order.get("id"), orders);



			orders.setPaymentTransactionId(paymentId);
			orders.setOrderNumber(orderId);
			orderService.saveOrder(orders);

			orderService.saveOrder(orders); 


			for (CartItem cartItem : cartItems) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(orders); // Set the order for the order item
				orderItem.setProduct(cartItem.getProduct()); // Set the product for the order item
				orderItem.setQuantity(cartItem.getQuantity()); // Set the quantity for the order item

				// Calculate and set the total price
				double totalPrice = cartItem.getQuantity() * cartItem.getProduct().getPrice();
				orderItem.setTotalPrice(totalPrice);

				// Save the order item
				orderItemService.saveOrderItem(orderItem);
			}


			cartService.clearCart(user);

			return "redirect:" + request.getContextPath() + "/orderConfirmation";
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception for debugging
			return "Error storing form data";
		}
	}







	@RequestMapping(value="/buynowpayment")
	public String getbuynowpayment(@RequestParam("productId") int id,@RequestParam("addressId") Integer addressId, Model model, Principal principal) {
		String username = principal.getName();


		User user = userService.findByEmail(username);
		List<CartItem> cartItems = cartItemService.getAllCartItemsByUser(user);


		double subtotal = cartItemService.calculateSubtotal(cartItems);
		double shipping = cartItemService.calculateShipping(subtotal);
		//		double total = subtotal + shipping;


		//		model.addAttribute("subtotal", subtotal);
		model.addAttribute("shipping", shipping);
		//		model.addAttribute("total", total);
		model.addAttribute("user", user);

		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		Product product=productsrepo.findById(id).get();
		model.addAttribute("product", product);


		if (product.getDeal() != null) { // Check if the product has a deal
			double discountPercentage = product.getDeal().getDiscount();
			double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
			double subtotal1 = discountedPrice ;
			double finalprice = subtotal1 + shipping;
			model.addAttribute("finalprice", finalprice);
			product.setDiscountedPrice(discountedPrice); // Set discounted price to product
		} else {
			double finalprice = product.getPrice()+shipping; // Use original price if no deal
			model.addAttribute("finalprice", finalprice);
		}








		//		double subtotal1 = product.getPrice();

		if (optionalAddress.isPresent()) {
			model.addAttribute("selectedAddress", optionalAddress.get());
			return "buynowpayment";
		} else {
			return "redirect:/error";
		}
	}


}
