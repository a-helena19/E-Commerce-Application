package at.fhv.e_commerce_application.domain.model.product;

public interface ProductRepository {
    void save(Product product);
    Product findById(String id);
    void delete(Product product);
}
