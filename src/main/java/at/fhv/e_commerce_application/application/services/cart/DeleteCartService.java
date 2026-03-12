package at.fhv.e_commerce_application.application.services.cart;

import java.util.UUID;

public interface DeleteCartService {
    void deleteCart(UUID cartId);
    void deleteCartByUserId(UUID userId);
}
