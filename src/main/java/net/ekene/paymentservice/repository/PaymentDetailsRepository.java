package net.ekene.paymentservice.repository;

import net.ekene.paymentservice.model.PaymentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    @Query("select pd from PaymentDetails pd")
    Page<PaymentDetails> findAllPayments(Pageable pageable);
}
