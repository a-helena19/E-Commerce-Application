package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.GetCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetCardServiceImpl implements GetCartService {
    private final CartRepository cartRepository;
    private final CartDTOMapper cartDTOMapper;

    public GetCardServiceImpl(CartRepository cartRepository, CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    @Override
    public GetCartDTO getCartByUserId(UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return cartDTOMapper.toGetCartDTO(cart);
    }

    @Override
    public GetCartDTO getCartByCartId(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> CartNotFoundException.byCartId(cartId));
        return cartDTOMapper.toGetCartDTO(cart);
    }

    @Override
    public List<GetCartDTO> getAllCarts() {
        List<Cart> allCarts = cartRepository.findAll();
        List<GetCartDTO> result = new ArrayList<>();

        for (Cart cart : allCarts) {
            result.add(cartDTOMapper.toGetCartDTO(cart));
        }
        return result;
    }


}
