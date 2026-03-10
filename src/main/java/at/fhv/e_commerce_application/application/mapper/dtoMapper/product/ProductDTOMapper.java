package at.fhv.e_commerce_application.application.mapper.dtoMapper.product;

import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;

public class ProductDTOMapper {
    public GetProductDTO getProductDTO(Product product) {
        return new GetProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    public Product toDomainGetProductDTO(GetProductDTO getProductDTO) {
        return new Product(
                getProductDTO.id(),
                getProductDTO.name(),
                getProductDTO.description(),
                getProductDTO.price(),
                getProductDTO.stock()
        );
    }
}
