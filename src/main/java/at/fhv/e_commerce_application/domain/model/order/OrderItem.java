package at.fhv.e_commerce_application.domain.model.order;

import java.util.UUID;

public class OrderItem {
    private UUID id;
    private UUID productId;
    private int quantity;
    private double price;

    public OrderItem(UUID id, UUID productId, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
