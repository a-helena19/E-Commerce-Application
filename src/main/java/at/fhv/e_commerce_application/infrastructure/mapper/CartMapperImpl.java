package at.fhv.e_commerce_application.infrastructure.mapper;

import at.fhv.e_commerce_application.application.mapper.CartMapper;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.infrastructure.persistence.model.cart.CartEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.cart.CartItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper {
    @Override
    public Cart toDomain(CartEntity entity) {
        List<CartItem> items = new ArrayList<>();

        for (CartItemEntity itemEntity : entity.getItems()) {
            items.add(toDomainItem(itemEntity));
        }

        return new Cart(
                entity.getId(),
                entity.getUserId(),
                items
        );
    }

    @Override
    public CartEntity toEntity(Cart cart) {
        List<CartItemEntity> items = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            items.add(toEntityItem(item));
        }

        return new CartEntity(
                cart.getId(),
                cart.getUserId(),
                items
        );
    }

    @Override
    public CartItem toDomainItem(CartItemEntity entity) {
        return new CartItem(
                null,
                entity.getProductId(),
                entity.getQuantity()
        );
    }

    @Override
    public CartItemEntity toEntityItem(CartItem cartItem) {
        return new CartItemEntity(
                null,
                cartItem.getProductId(),
                cartItem.getQuantity()
        );
    }

}
