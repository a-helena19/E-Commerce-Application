package at.fhv.e_commerce_application.application.services.cart;

import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

import java.util.UUID;

public interface RemoveProductFromCartService {
    GetCartDTO removeItemFromCart(UUID cartId, UUID cartItemId);
}
