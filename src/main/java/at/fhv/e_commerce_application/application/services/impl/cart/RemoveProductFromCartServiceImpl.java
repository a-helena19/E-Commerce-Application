package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.RemoveProductFromCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveProductFromCartServiceImpl implements RemoveProductFromCartService {
    private final CartRepository cartRepository;
    private final CartDTOMapper cartDTOMapper;

    public RemoveProductFromCartServiceImpl(CartRepository cartRepository, CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    @Override
    public GetCartDTO removeItemFromCart(UUID cartId, UUID cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> CartNotFoundException.byCartId(cartId));
        cart.removeItem(cartItemId);
        Cart savedCart = cartRepository.save(cart);
        return cartDTOMapper.toGetCartDTO(savedCart);
    }
}
