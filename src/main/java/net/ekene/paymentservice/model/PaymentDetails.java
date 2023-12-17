package net.ekene.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import net.ekene.paymentservice.util.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDetails extends BaseEntity {
    private double charged_amount;
    private String processor_response;
    private String payment_type;
    private double amount_settled;
    @ManyToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;
}
