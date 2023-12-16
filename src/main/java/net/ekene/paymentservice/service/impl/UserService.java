package net.ekene.paymentservice.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.ekene.paymentservice.model.PaymentDetails;
import net.ekene.paymentservice.model.UserDetails;
import net.ekene.paymentservice.repository.PaymentDetailsRepository;
import net.ekene.paymentservice.repository.UserDetailsRepository;
import net.ekene.paymentservice.service.PaymentService;
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
       UserDetails userDetails = new UserDetails("ekene", "Loy", "ekenevics@gmail.com");
       UserDetails userDetails1 = new UserDetails("jane", "doe", "jane@gmail.com");
       UserDetails userDetails2 = new UserDetails("john", "dee", "john@gmail.com");
       List<UserDetails> addAll = Arrays.asList(userDetails, userDetails1, userDetails2);
       userDetailsRepository.saveAll(addAll);

        PaymentDetails paymentDetails = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "FOOD", "get@mail.com", "ben");
        PaymentDetails paymentDetails1 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "BEER", "john@mail.com", "john");
        PaymentDetails paymentDetails2 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "LAPTOP", "ogae@mail.com", "oge");
        PaymentDetails paymentDetails3 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "TV", "paul@mail.com", "paul");
        PaymentDetails paymentDetails4 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "PHONE", "peter@mail.com", "peter");
        PaymentDetails paymentDetails5 = new PaymentDetails(12.0, "Successful", "CARD", 11.5, "TES", "loy@mail.com", "loy");

        List<PaymentDetails> addAlll = Arrays.asList(paymentDetails, paymentDetails1, paymentDetails2, paymentDetails3, paymentDetails4, paymentDetails5);
        paymentDetailsRepository.saveAll(addAlll);
    }


//    private Boolean validatePayment(int transactionId){
//        return paymentService.verifyTransaction(transactionId)
//                .getData().getProcessor_response().equalsIgnoreCase("successful");
//    }

}
