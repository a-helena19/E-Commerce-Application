package at.fhv.e_commerce_application.application.services.cart;

import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.UpdateCartItemDTO;

public interface UpdateCartItemQuantityService {
    GetCartDTO updateItemQuantity(UpdateCartItemDTO updateCartItemDTO);
}
