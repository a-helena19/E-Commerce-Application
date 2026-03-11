package at.fhv.e_commerce_application.rest.dtos.cart;

import java.util.UUID;

public class AddCartItemDTO {
    private UUID productId;
    private int quantity;

    public AddCartItemDTO() {}

    public AddCartItemDTO(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
