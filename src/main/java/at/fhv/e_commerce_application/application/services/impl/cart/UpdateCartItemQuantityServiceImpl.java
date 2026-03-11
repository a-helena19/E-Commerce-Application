package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.UpdateCartItemQuantityService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.UpdateCartItemDTO;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartItemQuantityServiceImpl implements UpdateCartItemQuantityService {
    private final CartRepository cartRepository;
    private final CartDTOMapper cartDTOMapper;

    public UpdateCartItemQuantityServiceImpl(CartRepository cartRepository, CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    @Override
    public GetCartDTO updateItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        Cart cart = cartRepository.findById(updateCartItemDTO.getCartId()).orElseThrow(() -> new CartNotFoundException(updateCartItemDTO.getCartId()));
        cart.updateItemQuantity(updateCartItemDTO.getCartItemId(), updateCartItemDTO.getQuantity());
        Cart savedCart = cartRepository.save(cart);
        return cartDTOMapper.toGetCartDTO(savedCart);
    }
}
