package at.fhv.e_commerce_application.application.services.product;

import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;

public interface CreateProductService {
    GetProductDTO createProduct(CreateProductDTO dto);
}
