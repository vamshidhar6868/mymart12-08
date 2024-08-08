package com.mymart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

@Service
public class RazorpayService {

    @Autowired
    private RazorpayClient razorpayClient;

   
}
