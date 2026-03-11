package at.fhv.e_commerce_application.application.services.cart;

import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

import java.util.List;
import java.util.UUID;

public interface GetCartService {
    GetCartDTO getCartByUserId(UUID userId);
    GetCartDTO getCartByCartId(UUID cartId);
    List<GetCartDTO> getAllCarts();
}
