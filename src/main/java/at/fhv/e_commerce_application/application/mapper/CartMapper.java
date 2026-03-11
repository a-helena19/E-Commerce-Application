package at.fhv.e_commerce_application.application.mapper;

import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.infrastructure.persistence.model.cart.CartEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.cart.CartItemEntity;

public interface CartMapper {
    Cart toDomain(CartEntity entity);
    CartEntity toEntity(Cart cart);
    CartItem toDomainItem(CartItemEntity entity);
    CartItemEntity toEntityItem(CartItem item);
}
