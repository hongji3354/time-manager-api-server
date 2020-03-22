package kr.healthcare.timemanagerapi.exception;

public class TokenAuthorizationException extends Exception {

    public TokenAuthorizationException() {
        super();
    }

    public TokenAuthorizationException(String message) {
        super(message);
    }
}
