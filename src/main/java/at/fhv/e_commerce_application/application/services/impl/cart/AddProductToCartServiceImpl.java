package at.fhv.e_commerce_application.application.services.impl.cart;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.cart.CartDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.AddProductToCartService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartItem;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductOutOfStockException;
import at.fhv.e_commerce_application.domain.model.exception.InvalidCartItemDataException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.domain.model.product.ProductStatus;
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
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> CartNotFoundException.byCartId(cartId));
        UUID productId = addCartItemDTO.getProductId();
        int quantity = addCartItemDTO.getQuantity();

        // Verify product exists and check availability
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        if (product.getStatus() != ProductStatus.ACTIVE) {
            throw new InvalidCartItemDataException("productId", productId, "Product is not available");
        }

        // Calculate total quantity (existing + new)
        int existingQuantity = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .mapToInt(CartItem::getQuantity)
                .sum();
        int totalQuantity = existingQuantity + quantity;

        if (product.getStock() < totalQuantity) {
            throw new ProductOutOfStockException(productId, totalQuantity, product.getStock());
        }

        cart.addItem(productId, quantity);

        Cart savedCart = cartRepository.save(cart);
        return cartDTOMapper.toGetCartDTO(savedCart);
    }
}
