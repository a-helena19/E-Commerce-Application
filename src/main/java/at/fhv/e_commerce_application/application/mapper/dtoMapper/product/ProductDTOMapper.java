package at.fhv.e_commerce_application.application.mapper.dtoMapper.product;

import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;

public interface ProductDTOMapper {
    GetProductDTO toGetProductDTO(Product product);
    Product toDomainCreateProductDTO(CreateProductDTO createProductDTO);
}
