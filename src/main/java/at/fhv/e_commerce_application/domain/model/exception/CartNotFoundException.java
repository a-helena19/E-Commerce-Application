package at.fhv.e_commerce_application.domain.model.exception;

import java.util.UUID;

public class CartNotFoundException extends RuntimeException {
    private final UUID cartId;

    public CartNotFoundException(UUID cartId) {
        super("Cart with id " + cartId + " was not found.");
        this.cartId = cartId;
    }

    public UUID getCartId() {
        return cartId;
    }
}
