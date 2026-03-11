package at.fhv.e_commerce_application.rest.dtos.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class CreateOrderItemDTO {

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }


}
