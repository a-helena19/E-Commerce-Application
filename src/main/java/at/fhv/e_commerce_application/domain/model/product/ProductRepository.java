package at.fhv.e_commerce_application.domain.model.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    boolean existsById(UUID id);
}
