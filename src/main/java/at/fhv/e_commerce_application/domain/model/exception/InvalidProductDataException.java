package at.fhv.e_commerce_application.domain.model.exception;

public class InvalidProductDataException extends RuntimeException {
    private final String field;
    private final Object invalidValue;

    public InvalidProductDataException(String field, Object invalidValue, String message) {
        super(message);
        this.field = field;
        this.invalidValue = invalidValue;
    }

    public String getField() {
        return field;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }
}