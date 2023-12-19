package net.ekene.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ekene.paymentservice.payload.PaymentRequest;
import net.ekene.paymentservice.payload.SizePager;
import net.ekene.paymentservice.service.PaymentDetailsService;
import net.ekene.paymentservice.service.PaymentService;
import net.ekene.paymentservice.util.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/")
public class PaymentController extends BaseController {
    private final PaymentService paymentService;
    private final PaymentDetailsService paymentDetailsService;

    @PostMapping("make")
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequest paymentRequest){
        return getResponse(HttpStatus.CREATED, "Success!", paymentService.initializePayment(paymentRequest));
    }

    @GetMapping("verify")
    public ResponseEntity<?> verifyPayment(@RequestParam int transactionId){
        return getResponse(HttpStatus.OK, "Verified!", paymentService.verifyTransaction(transactionId));
    }

    @GetMapping("get-payment")
    public ResponseEntity<?> getPayments(SizePager sizePager){
        return getResponse(HttpStatus.OK, "Retrieved!", paymentDetailsService.getPayment(sizePager));
    }

    // Redirect URL after making payment from flutterwave!
    @GetMapping("make-payment")
    public String verifyPayment(@RequestParam String status, String tx_ref, int transaction_id){
        return "test the transaction id on postman";
    }
}
