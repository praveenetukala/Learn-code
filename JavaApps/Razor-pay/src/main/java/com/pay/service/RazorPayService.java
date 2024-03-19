package com.pay.service;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pay.util.Response;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RazorPayService {

	@Autowired
	private RazorpayClient razorpayClient;

	public ResponseEntity<?> createOrder(int amount, String currency) {
		System.out.println("RazorPayService.createOrder()");

		try {
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount * 100);
			orderRequest.put("currency", currency);
			orderRequest.put("receipt", "order_receipt_" + System.currentTimeMillis());
			orderRequest.put("payment_capture", 1);

			JSONObject notes = new JSONObject();
			// notes.put("notes_key_1", "Tea, Earl Grey, Hot");
			orderRequest.put("notes", notes);

			Order order = razorpayClient.orders.create(orderRequest);
			System.out.println("Order is created ---> " + order);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Successfully order created", order.toString()));
		} catch (RazorpayException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null));
		}
	}
}
