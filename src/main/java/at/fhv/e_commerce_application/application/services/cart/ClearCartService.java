package at.fhv.e_commerce_application.application.services.cart;

import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

import java.util.UUID;

public interface ClearCartService {
    void clearCart(UUID cartId);
}
