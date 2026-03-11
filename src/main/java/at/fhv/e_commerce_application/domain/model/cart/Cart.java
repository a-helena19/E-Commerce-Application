package at.fhv.e_commerce_application.domain.model.cart;

import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private UUID userId;
    private List<CartItem> items;

    public Cart(UUID id, UUID userId, List<CartItem> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
