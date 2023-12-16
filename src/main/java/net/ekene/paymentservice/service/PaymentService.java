package net.ekene.paymentservice.service;

import net.ekene.paymentservice.payload.PaymentRequest;
import net.ekene.paymentservice.response.payment.PaymentReturnResponse;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> initializePayment(PaymentRequest paymentRequest);
    PaymentReturnResponse verifyTransaction(int transactionId);


}
