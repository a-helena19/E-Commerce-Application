package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.services.cart.ClearCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClearCartServiceImpl implements ClearCartService {
    private final CartRepository cartRepository;

    public ClearCartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void clearCart(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        cart.clear();
        cartRepository.save(cart);
    }
}
