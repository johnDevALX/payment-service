package net.ekene.paymentservice.exception;

public class UserNotVerifiedException extends RuntimeException {

    public UserNotVerifiedException(String message) {
        super(message);
    }
}
