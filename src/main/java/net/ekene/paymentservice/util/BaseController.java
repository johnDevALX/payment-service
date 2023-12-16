package net.ekene.paymentservice.util;

import net.ekene.paymentservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BaseController {

    public ResponseEntity<?> getResponse(HttpStatus status, String message, Object data){
        ApiResponse<Object> apiResponse = new ApiResponse<>(message, LocalDateTime.now(), data);
        return new ResponseEntity<>(apiResponse, status);
    }
}
