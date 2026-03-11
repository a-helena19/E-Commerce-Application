package at.fhv.e_commerce_application.application.services.cart;

import java.util.UUID;

public interface ClearCartService {
    void clearCart(UUID cartId);
    void clearCartByUserId(UUID userId);
}
