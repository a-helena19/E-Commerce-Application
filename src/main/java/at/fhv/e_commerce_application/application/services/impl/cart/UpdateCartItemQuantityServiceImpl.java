package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.UpdateCartItemQuantityService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartItemNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductOutOfStockException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.UpdateCartItemDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCartItemQuantityServiceImpl implements UpdateCartItemQuantityService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDTOMapper cartDTOMapper;

    public UpdateCartItemQuantityServiceImpl(CartRepository cartRepository, ProductRepository productRepository,CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    @Override
    public GetCartDTO updateItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        Cart cart = cartRepository.findById(updateCartItemDTO.getCartId()).orElseThrow(() -> CartNotFoundException.byCartId(updateCartItemDTO.getCartId()));
        UUID foundProductId = null;

        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(updateCartItemDTO.getCartItemId())) {
                foundProductId = item.getProductId();
                break;
            }
        }

        if (foundProductId == null) {
            throw new CartItemNotFoundException(updateCartItemDTO.getCartItemId());
        }

        UUID productId = foundProductId;
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        int newQuantity = updateCartItemDTO.getQuantity();
        if (product.getStock() < newQuantity) {
            throw new ProductOutOfStockException(productId, newQuantity, product.getStock());
        }

        cart.updateItemQuantity(updateCartItemDTO.getCartItemId(), newQuantity);
        Cart savedCart = cartRepository.save(cart);
        return cartDTOMapper.toGetCartDTO(savedCart);
    }
}
