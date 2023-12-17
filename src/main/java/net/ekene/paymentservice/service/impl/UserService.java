package net.ekene.paymentservice.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.ekene.paymentservice.model.PaymentDetails;
import net.ekene.paymentservice.model.UserDetails;
import net.ekene.paymentservice.repository.PaymentDetailsRepository;
import net.ekene.paymentservice.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsRepository userDetailsRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @PostConstruct
   public void addUsers (){
       UserDetails userDetails = new UserDetails("ekene", "Loy", "ekenevics@gmail.com", "00000");
       UserDetails userDetails1 = new UserDetails("jane", "doe", "jane@gmail.com", "00000");
       UserDetails userDetails2 = new UserDetails("john", "bee", "john@gmail.com", "00000");
       UserDetails userDetails3 = new UserDetails("new", "old", "new@gmail.com", "00000");
       UserDetails userDetails4 = new UserDetails("Mi_REY", "riri", "okonkwovivi25@gmail.com", "00000");
       UserDetails userDetails5 = new UserDetails("Vincent", "snitch", "enwerevincent@gmail.com", "00000");
       List<UserDetails> addAll = Arrays.asList(userDetails, userDetails1, userDetails2, userDetails3, userDetails4, userDetails5);
       userDetailsRepository.saveAll(addAll);

        PaymentDetails paymentDetails = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails);
        PaymentDetails paymentDetails1 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails1);
        PaymentDetails paymentDetails2 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails2);
        PaymentDetails paymentDetails3 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails3);
        PaymentDetails paymentDetails4 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails4);
        PaymentDetails paymentDetails5 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, userDetails5);

        List<PaymentDetails> addAlll = Arrays.asList(paymentDetails, paymentDetails1, paymentDetails2, paymentDetails3, paymentDetails4, paymentDetails5);
        paymentDetailsRepository.saveAll(addAlll);
    }
}
