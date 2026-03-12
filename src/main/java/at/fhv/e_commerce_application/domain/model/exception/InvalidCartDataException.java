package at.fhv.e_commerce_application.domain.model.exception;

public class InvalidCartDataException extends RuntimeException {
    private final String field;
    private final Object invalidValue;

    public InvalidCartDataException(String field, Object invalidValue, String message) {
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
