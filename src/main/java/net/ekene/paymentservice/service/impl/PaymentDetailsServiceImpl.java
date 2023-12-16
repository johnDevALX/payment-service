package net.ekene.paymentservice.service.impl;

import lombok.RequiredArgsConstructor;
import net.ekene.paymentservice.model.PaymentDetails;
import net.ekene.paymentservice.repository.PaymentDetailsRepository;
import net.ekene.paymentservice.response.payment.PaymentData;
import net.ekene.paymentservice.service.PaymentDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public void savePaymentDetails(PaymentData paymentData, String desc) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .amount_settled(paymentData.getAmount_settled())
                .payment_type(paymentData.getPayment_type())
                .charged_amount(paymentData.getCharged_amount())
                .processor_response(paymentData.getProcessor_response())
                .payment_description(desc)
                .email(paymentData.getCustomer().getEmail())
                .name(paymentData.getCustomer().getName())
                .build();
        paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public Page<PaymentDetails> getPayment(int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return paymentDetailsRepository.findAllPayments(pageable);
    }
}
