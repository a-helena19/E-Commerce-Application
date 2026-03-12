package at.fhv.e_commerce_application.application.mapper.dtoMapper.product;

import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDTOMapperImpl implements  ProductDTOMapper {

    @Override
    public GetProductDTO toGetProductDTO(Product product) {
        return new GetProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus().name()
        );
    }

    @Override
    public Product toDomainCreateProductDTO(CreateProductDTO createProductDTO) {
        return Product.create(
                UUID.randomUUID(),
                createProductDTO.getName(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                createProductDTO.getStock()
        );
    }
}
