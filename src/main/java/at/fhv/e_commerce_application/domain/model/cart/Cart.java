package at.fhv.e_commerce_application.domain.model.cart;

import at.fhv.e_commerce_application.domain.model.exception.CartItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private UUID userId;
    private List<CartItem> items;

    private Cart(UUID id, UUID userId, List<CartItem> items) {
        this.id = id;
        this.userId = userId;
        this.items = new ArrayList<>(items);
    }

    public static Cart create(UUID userId) {
        return new Cart(UUID.randomUUID(), userId, new ArrayList<>());
    }

    public static Cart reconstruct(UUID id, UUID userId, List<CartItem> items) {
        return new Cart(id, userId, items);
    }

    public void addItem(UUID productId, int quantity) {
        for (CartItem existingItem : items) {
            if (existingItem.getProductId().equals(productId)) {
                existingItem.increaseQuantity(quantity);
                return;
            }
        }

        CartItem newItem = CartItem.create(productId, quantity);
        items.add(newItem);
    }

    public void removeItem(UUID cartItemId) {
        CartItem itemToRemove = findItemById(cartItemId);
        items.remove(itemToRemove);
    }

    public void updateItemQuantity(UUID cartItemId, int newQuantity) {
        CartItem item = findItemById(cartItemId);
        item.setQuantity(newQuantity);
    }

    public void clear() {
        items.clear();
    }

    private CartItem findItemById(UUID cartItemId) {
        for (CartItem item : items) {
            if (item.getId().equals(cartItemId)) {
                return item;
            }
        }
        throw new CartItemNotFoundException(cartItemId);
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
