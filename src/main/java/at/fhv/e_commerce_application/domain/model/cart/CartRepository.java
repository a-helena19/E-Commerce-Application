package at.fhv.e_commerce_application.domain.model.cart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    Cart save(Cart cart);
    Cart findByUserId(UUID userId);
    Optional<Cart> findById(UUID cartId);
    List<Cart> findAll();
    void deleteByUserId(UUID userId);
}
