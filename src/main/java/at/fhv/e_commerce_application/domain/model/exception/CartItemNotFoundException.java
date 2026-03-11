package at.fhv.e_commerce_application.domain.model.exception;

import java.util.UUID;

public class CartItemNotFoundException extends RuntimeException {
    private final UUID cartItemId;

    public CartItemNotFoundException(UUID cartItemId) {
        super("CartItem with id " + cartItemId + " was not found.");
        this.cartItemId = cartItemId;
    }

    public UUID getCartItemId() {
        return cartItemId;
    }
}
