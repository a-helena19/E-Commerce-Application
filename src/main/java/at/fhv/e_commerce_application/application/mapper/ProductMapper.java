package at.fhv.e_commerce_application.application.mapper;

import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.infrastructure.persistence.model.product.ProductEntity;

public interface ProductMapper {
    Product toDomain(ProductEntity entity);
    ProductEntity toEntity(Product product);
}
