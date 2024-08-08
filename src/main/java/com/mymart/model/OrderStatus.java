package com.mymart.model;

public enum OrderStatus {
	 PLACED,
	    PENDING,
	    ACCEPTED,
	    SHIPPED,
	    DELIVERED,
	    CANCELLED;


	private OrderStatus status;

	public void setStatus(String statusString) {
	    switch (statusString.toLowerCase()) {
		    case "placed":
		        this.status = OrderStatus.PLACED;
		        break;
	        case "pending":
	            this.status = OrderStatus.PENDING;
	            break;
	        case "accepted":
	            this.status = OrderStatus.ACCEPTED;
	            break;
	        case "shipped":
	            this.status = OrderStatus.SHIPPED;
	            break;
	        case "delivered":
	            this.status = OrderStatus.DELIVERED;
	            break;
	        case "cancelled":
	            this.status = OrderStatus.CANCELLED;
	            break;
	        default:
	            // Handle invalid status string
	            throw new IllegalArgumentException("Invalid order status: " + statusString);
	    }
	}
	}