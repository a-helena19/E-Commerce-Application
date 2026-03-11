package at.fhv.e_commerce_application.application.mapper.dtoMapper.cart;

import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDTOMapperImpl implements CartDTOMapper {
    @Override
    public GetCartDTO toGetCartDTO(Cart cart) {
        List<GetCartDTO.GetCartItemDTO> items = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            items.add(new GetCartDTO.GetCartItemDTO(item.getId(), item.getProductId(), item.getQuantity()));
        }

        return new GetCartDTO(
                cart.getId(),
                cart.getUserId(),
                items
        );
    }

    @Override
    public Cart toDomainFromGetCartDTO(GetCartDTO getCartDTO) {
        List<CartItem> items = new ArrayList<>();

        for (GetCartDTO.GetCartItemDTO itemDTO : getCartDTO.items()) {
            items.add(CartItem.reconstruct(
                    null,
                    itemDTO.productId(),
                    itemDTO.quantity()
            ));
        }

        return Cart.reconstruct(
                getCartDTO.cartId(),
                getCartDTO.userId(),
                items
        );
    }
}
