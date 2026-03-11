package at.fhv.e_commerce_application.domain.model.exception;

public class InvalidOrderDataException extends RuntimeException {

    public InvalidOrderDataException(String message) {
        super(message);
    }
}

