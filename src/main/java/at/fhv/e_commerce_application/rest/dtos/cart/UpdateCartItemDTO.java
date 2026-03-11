package at.fhv.e_commerce_application.rest.dtos.cart;

import java.util.UUID;

public class UpdateCartItemDTO {
    private UUID cartId;
    private UUID cartItemId;
    private int quantity;

    public UpdateCartItemDTO() {
    }

    public UpdateCartItemDTO(UUID cardId, UUID cardItemId, int quantity) {
        this.cartId = cardId;
        this.cartItemId = cardItemId;
        this.quantity = quantity;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(UUID cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
