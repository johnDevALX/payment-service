package net.ekene.paymentservice.model;

import jakarta.persistence.Entity;
import lombok.*;
import net.ekene.paymentservice.util.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDetails extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
}
