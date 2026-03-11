package at.fhv.e_commerce_application.rest.dtos.order;

import java.util.UUID;

public class CreateOrderItemDTO {

    private UUID productId;
    private int quantity;
    private double price;

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}
