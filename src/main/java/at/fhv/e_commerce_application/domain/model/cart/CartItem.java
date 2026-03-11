package at.fhv.e_commerce_application.domain.model.cart;

import java.util.UUID;

public class CartItem {
    private UUID id;
    private UUID productId;
    private int quantity;

    public CartItem(UUID id, UUID productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public UUID getId() {
        return id;
    }
}
