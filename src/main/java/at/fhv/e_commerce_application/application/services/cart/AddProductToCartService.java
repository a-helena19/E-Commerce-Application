package at.fhv.e_commerce_application.application.services.cart;

import at.fhv.e_commerce_application.rest.dtos.cart.AddCartItemDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

import java.util.UUID;

public interface AddProductToCartService {
    GetCartDTO addItemToCart(UUID cartId, AddCartItemDTO addCartItemDTO);
}
