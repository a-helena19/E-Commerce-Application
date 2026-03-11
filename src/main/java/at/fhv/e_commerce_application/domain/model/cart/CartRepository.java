package at.fhv.e_commerce_application.domain.model.cart;

import java.util.List;
import java.util.UUID;

public interface CartRepository {
    Cart save (Cart cart);
    Cart findByUserId (UUID userId);
}
