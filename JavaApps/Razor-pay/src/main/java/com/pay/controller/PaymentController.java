package com.pay.controller;

import java.security.SignatureException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pay.model.Payment;
import com.pay.service.RazorPayService;
import com.pay.service.SMPTService;
import com.pay.util.Response;
import com.pay.util.Signature;

@CrossOrigin(origins = "*") // Allow requests from any origin
@RestController
@RequestMapping("/api/")
public class PaymentController {

	@Autowired
	private RazorPayService razorPayService;

	@Autowired
	private SMPTService smptService;

	/**
	 * To create the Order
	 * 
	 * @param amount
	 * @param currency
	 * @return
	 */
	@GetMapping(value = "create/order", produces = { "application/json" })
	public ResponseEntity<?> createOrder(@RequestParam int amount, String currency) {
		System.out.println("PaymentController.createOrder()");
		System.out.println("Amount ----> " + amount);
		System.out.println("Currency ----> " + currency);
		return razorPayService.createOrder(amount, currency);
	}

	/**
	 * Handle the request after successfull order creation
	 * 
	 * @param payment
	 * @return
	 * @throws SignatureException
	 */
	@RequestMapping("payment/success")
	public ResponseEntity<Response<?>> paymentSuccess(@RequestBody Payment payment) throws SignatureException {
		System.out.println("PaymentController.paymentSuccess()");
		System.out.println("payment ----> " + payment.toString());

		String generatedSignature = Signature.calculateRFC2104HMAC(
				payment.getRazorpayOrderId() + "|" + payment.getRazorpayPaymentId(), "DdLyemfAjOtpVlvSicS9Oxxg");

		if (payment.getRazorpaySignature().equals(generatedSignature)) {
			payment.setPaymentDateTime(LocalDateTime.now());
			// TODO: save payment details after successful payment
			// Sending the confirmation mail to customer
			smptService.sendMail(payment.getEmailId(), "MultiMart Order Confirmation",
					"Your order successfully Placed ID:" + payment.getRazorpayPaymentId());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Payment Successfull", payment.toString()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Transaction, Signature not verified", null));
	}

	/**
	 * To Get the List of Payments
	 * 
	 * @return
	 */
	@GetMapping(value = "get/all/payments", produces = { "application/json" })
	public ResponseEntity<Response<?>> getPayments() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Payment Details are fetched succesfully", null));
	}

	/**
	 * To get the List of orders
	 * 
	 * @return
	 */
	@GetMapping(value = "get/all/orders", produces = { "application/json" })
	public ResponseEntity<Response<?>> getOrders() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Order Details are fetched succesfully", null));
	}
}
