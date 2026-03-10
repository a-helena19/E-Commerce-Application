package at.fhv.e_commerce_application.infrastructure.mapper;

import at.fhv.e_commerce_application.application.mapper.ProductMapper;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.infrastructure.persistence.model.product.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return Product.reconstruct(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getStatus()
        );
    }

    @Override
    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus()
        );
    }

}
