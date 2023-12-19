package net.ekene.paymentservice.service.impl;

import lombok.RequiredArgsConstructor;
import net.ekene.paymentservice.exception.UserNotFoundException;
import net.ekene.paymentservice.model.PaymentDetails;
import net.ekene.paymentservice.model.UserDetails;
import net.ekene.paymentservice.payload.SizePager;
import net.ekene.paymentservice.repository.PaymentDetailsRepository;
import net.ekene.paymentservice.repository.UserDetailsRepository;
import net.ekene.paymentservice.response.payment.PaymentData;
import net.ekene.paymentservice.service.PaymentDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public void savePaymentDetails(PaymentData paymentData) {
        UserDetails userDetails = userDetailsRepository.findByEmailIgnoreCase(paymentData.getCustomer().getEmail()).orElseThrow(() -> new UserNotFoundException("No user found with this email " + paymentData.getCustomer().getEmail()));
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .amount_settled(paymentData.getAmount_settled())
                .payment_type(paymentData.getPayment_type())
                .charged_amount(paymentData.getCharged_amount())
                .processor_response(paymentData.getProcessor_response())
                .userDetails(userDetails)
                .build();
        paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public Page<PaymentDetails> getPayment(SizePager sizePager) {
        return paymentDetailsRepository
                .findAllPayments(PageRequest.of(sizePager.getPage(), sizePager.getSize()));
    }
}
