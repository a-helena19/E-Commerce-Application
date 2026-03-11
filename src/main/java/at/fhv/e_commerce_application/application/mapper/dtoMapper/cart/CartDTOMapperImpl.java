package at.fhv.e_commerce_application.application.mapper.dtoMapper.cart;

import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CartDTOMapperImpl implements CartDTOMapper {
    @Override
    public GetCartDTO toGetCartDTO(Cart cart) {
        return new GetCartDTO(
                cart.getId(),
                cart.getUserId(),
                cart.getItems().stream()
                        .map(item -> new GetCartDTO.GetCartItemDTO(
                                item.getProductId(),
                                item.getQuantity()
                        ))
                        .toList()
        );
    }

    @Override
    public Cart toDomainFromGetCartDTO(GetCartDTO getCartDTO) {
        List<CartItem> items = new ArrayList<>();

        for (GetCartDTO.GetCartItemDTO itemDTO : getCartDTO.items()) {
            items.add(new CartItem(
                    null,
                    itemDTO.productId(),
                    itemDTO.quantity()
            ));
        }

        return new Cart(
                getCartDTO.id(),
                getCartDTO.userId(),
                items
        );
    }
}
