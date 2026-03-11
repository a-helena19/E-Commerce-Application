package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.AddProductToCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.rest.dtos.cart.AddCartItemDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProductToCartServiceImpl implements AddProductToCartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDTOMapper cartDTOMapper;

    public AddProductToCartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    @Override
    public GetCartDTO addItemToCart(UUID cartId, AddCartItemDTO addCartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        UUID productId = addCartItemDTO.getProductId();
        int quantity = addCartItemDTO.getQuantity();

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        product.reduceStock(quantity);
        cart.addItem(productId, quantity);
        productRepository.save(product);

        Cart savedCart = cartRepository.save(cart);
        return cartDTOMapper.toGetCartDTO(savedCart);
    }
}
