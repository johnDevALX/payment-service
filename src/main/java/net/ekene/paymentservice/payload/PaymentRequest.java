package net.ekene.paymentservice.payload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.Currency;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    @Expose
    private String email;
    @Expose
    private Currency currency;
    @Expose
    private Double amount;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
