package net.ekene.paymentservice.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    private FlutterConfig flutterConfig;

    @Data
    public static class FlutterConfig{
        private String redirectUrl;
        private String initUrl;
        private String verifyUrl;
        private String publicKey;
        private String secretKey;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
