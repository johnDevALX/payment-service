package net.ekene.paymentservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ekene.paymentservice.config.AppConfig;
import net.ekene.paymentservice.model.UserDetails;
import net.ekene.paymentservice.payload.Customer;
import net.ekene.paymentservice.payload.PaymentRequest;
import net.ekene.paymentservice.repository.UserDetailsRepository;
import net.ekene.paymentservice.response.payment.PaymentReturnResponse;
import net.ekene.paymentservice.service.PaymentDetailsService;
import net.ekene.paymentservice.service.PaymentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDetailsService paymentDetailsService;
    private final UserDetailsRepository userDetailsRepository;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    private final ObjectMapper objectMapper;


    @Override
    public Map<String, Object> initializePayment(PaymentRequest paymentRequest) {
        log.info("PAyload   --- {}", paymentRequest);

        UserDetails user = userDetailsRepository.findByEmailIgnoreCase(paymentRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found!"));
        String url = appConfig.getFlutterConfig().getInitUrl();

        log.info("init url  --- {}", url);

        HttpMethod httpMethod = HttpMethod.POST;
        String txRef = RandomStringUtils.randomAlphabetic(7);
        Map<String, Object> payload = new HashMap<>();
        payload.put("tx_ref", txRef);
        payload.put("amount", paymentRequest.getAmount());
        payload.put("currency", paymentRequest.getCurrency());
        payload.put("redirect_url", appConfig.getFlutterConfig().getRedirectUrl());
        payload.put("customer", new Customer(user.getFirstName(),
                user.getEmail(), "0000001000"));

        log.info("Mapped payload  --- {}", payload);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(appConfig.getFlutterConfig().getSecretKey());
        log.info("Secret key payload  --- {}", appConfig.getFlutterConfig().getSecretKey());


        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<>() {
        };
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> requestEntity = new RequestEntity<>(payload, httpHeaders, httpMethod, uri);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                requestEntity, typeRef);
        System.out.println("Response from payment Service IMPL " + response);
        return response.getBody();
    }

    @Override
    public PaymentReturnResponse verifyTransaction(int transactionId) {
        String url = appConfig.getFlutterConfig().getVerifyUrl() + "/" + transactionId + "/verify";
        HttpMethod httpMethod = HttpMethod.GET;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(appConfig.getFlutterConfig().getSecretKey());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<>() {
        };
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> requestEntity = new RequestEntity<>(httpHeaders, httpMethod, uri);
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, typeRef);

        log.info("REsponse Body after VERIFIFICATION --- {}", getDecodedResponse(Objects.requireNonNull(response.getBody())));

        PaymentReturnResponse returnResponse = getObjectFromJson(getDecodedResponse(Objects.requireNonNull(response.getBody())), PaymentReturnResponse.class);
//        System.out.println("Response from payment Service IMPL " + response);
//        restTemplate.postForObject(uri, response.getBody(), PaymentInitResponse.class);
        paymentDetailsService.savePaymentDetails(returnResponse.getData(), "PAYMENT");
        System.out.println(returnResponse);

        log.info("RETURN RESPONSE ----- {}", returnResponse);
        return returnResponse;
    }


    protected String getDecodedResponse(String response) {
        String decodedResponse = response;
        if (!response.startsWith("{") && !response.startsWith("[")) {
            decodedResponse = new String(Base64.getDecoder().decode(response));
        }
        return decodedResponse;
    }


    private <T> T getObjectFromJson(String json, Class<T> clazz) throws IllegalStateException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
