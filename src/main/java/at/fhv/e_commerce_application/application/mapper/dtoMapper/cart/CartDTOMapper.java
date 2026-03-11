package at.fhv.e_commerce_application.application.mapper.dtoMapper.cart;

import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

public interface CartDTOMapper {
    GetCartDTO toGetCartDTO(Cart cart);
    Cart toDomainFromGetCartDTO(GetCartDTO getCartDTO);
}
