package at.fhv.e_commerce_application.domain.model.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    private final UUID orderId;

    public OrderNotFoundException(UUID orderId) {
        super("Order with id " + orderId + " was not found.");
        this.orderId = orderId;
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.orderId = null;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
