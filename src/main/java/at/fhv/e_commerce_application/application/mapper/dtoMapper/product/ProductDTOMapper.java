package at.fhv.e_commerce_application.application.mapper.dtoMapper.product;

import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.DeleteProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.UpdateProductDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDTOMapper {
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

    public Product toDomainCreateProductDTO(CreateProductDTO createProductDTO) {
        return Product.create(
                UUID.randomUUID(),
                createProductDTO.getName(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                createProductDTO.getStock()
        );
    }

    public Product toDomainUpdateProductDTO(UpdateProductDTO updateProductDTO) {
        return Product.create(
                updateProductDTO.getId(),
                updateProductDTO.getName(),
                updateProductDTO.getDescription(),
                updateProductDTO.getPrice(),
                updateProductDTO.getStock()
        );
    }

    public Product toDomainDeleteProductDTO(DeleteProductDTO deleteProductDTO) {
        return Product.create(
                deleteProductDTO.getId(),
                deleteProductDTO.getName(),
                deleteProductDTO.getDescription(),
                deleteProductDTO.getPrice(),
                deleteProductDTO.getStock()
        );
    }
}
