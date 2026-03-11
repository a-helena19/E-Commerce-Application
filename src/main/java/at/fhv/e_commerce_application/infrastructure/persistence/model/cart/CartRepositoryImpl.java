package at.fhv.e_commerce_application.infrastructure.persistence.model.cart;

import at.fhv.e_commerce_application.application.mapper.CartMapper;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private final CartMapper cartMapper;
    private final CartJpaRepository jpaRepository;

    public CartRepositoryImpl(CartMapper cartMapper, CartJpaRepository jpaRepository) {
        this.cartMapper = cartMapper;
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Cart save (Cart cart) {
        var entity = cartMapper.toEntity(cart);
        var saved = jpaRepository.save(entity);
        return cartMapper.toDomain(saved);
    }

    @Override
    public Cart findByUserId (UUID userId) {
        return jpaRepository.findByUserId(userId)
                .map(cartMapper::toDomain)
                .orElseThrow(() -> new CartNotFoundException(userId));
    }
}
