package net.ekene.paymentservice.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.ekene.paymentservice.payload.Customer;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentData implements Serializable {
    private String id;
    private double charged_amount;
    private String processor_response;
    private String payment_type;
    private double amount_settled;
    private Customer customer;
}
