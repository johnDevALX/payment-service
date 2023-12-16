package net.ekene.paymentservice.service;

import net.ekene.paymentservice.model.PaymentDetails;
import net.ekene.paymentservice.response.payment.PaymentData;
import org.springframework.data.domain.Page;

public interface PaymentDetailsService {
    void savePaymentDetails(PaymentData paymentData, String desc);

    Page<PaymentDetails> getPayment(int page);
}
