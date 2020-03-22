package kr.healthcare.timemanagerapi.exception;

public class TokenEmptyException extends Exception {

    public TokenEmptyException() {
        super();
    }

    public TokenEmptyException(String message) {
        super(message);
    }
}
