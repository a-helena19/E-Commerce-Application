package at.fhv.e_commerce_application.rest;

import at.fhv.e_commerce_application.application.services.cart.*;
import at.fhv.e_commerce_application.rest.dtos.cart.AddCartItemDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.GetCartDTO;
import at.fhv.e_commerce_application.rest.dtos.cart.UpdateCartItemDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
public class CartRestController {
    private final GetCartService getCartService;
    private final AddProductToCartService addProductToCartService;
    private final RemoveProductFromCartService removeProductFromCartService;
    private final UpdateCartItemQuantityService updateCartItemQuantityService;
    private final ClearCartService clearCartService;

    public CartRestController(GetCartService getCartService, AddProductToCartService addProductToCartService, RemoveProductFromCartService removeProductFromCartService,
                              UpdateCartItemQuantityService updateCartItemQuantityService, ClearCartService clearCartService) {
        this.getCartService = getCartService;
        this.addProductToCartService = addProductToCartService;
        this.removeProductFromCartService = removeProductFromCartService;
        this.updateCartItemQuantityService = updateCartItemQuantityService;
        this.clearCartService = clearCartService;
    }

    @GetMapping
    public ResponseEntity<List<GetCartDTO>> getAllCarts() {
        List<GetCartDTO> carts = getCartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<GetCartDTO> getCartByCartId(@PathVariable UUID cartId) {
        GetCartDTO cart = getCartService.getCartByCartId(cartId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<GetCartDTO> getCartByUserId(@PathVariable UUID userId) {
        GetCartDTO cart = getCartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<GetCartDTO> addItemToCart(@PathVariable UUID cartId,
                                                    @Valid @RequestBody AddCartItemDTO addCartItemDTO) {
        GetCartDTO updatedCart = addProductToCartService.addItemToCart(cartId, addCartItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<GetCartDTO> removeItemFromCart(@PathVariable UUID cartId,
                                                         @PathVariable UUID cartItemId) {
        GetCartDTO updatedCart = removeProductFromCartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        clearCartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/items")
    public ResponseEntity<GetCartDTO> updateItemQuantity(@Valid @RequestBody UpdateCartItemDTO updateCartItemDTO) {
        GetCartDTO updatedCart = updateCartItemQuantityService.updateItemQuantity(updateCartItemDTO);
        return ResponseEntity.ok(updatedCart);
    }

}
