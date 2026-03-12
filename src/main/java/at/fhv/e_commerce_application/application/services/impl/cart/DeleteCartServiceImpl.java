package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.services.cart.DeleteCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCartServiceImpl implements DeleteCartService {
    private final CartRepository cartRepository;

    public DeleteCartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void deleteCart(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> CartNotFoundException.byCartId(cartId));
        cart.delete();
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartByUserId(UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw CartNotFoundException.byUserId(userId);
        }
        cart.delete();
        cartRepository.save(cart);
    }
}
