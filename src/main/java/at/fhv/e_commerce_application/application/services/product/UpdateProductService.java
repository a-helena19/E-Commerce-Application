package at.fhv.e_commerce_application.application.services.product;

import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.UpdateProductDTO;

import java.util.UUID;

public interface UpdateProductService {
    GetProductDTO updateProduct(UUID id, UpdateProductDTO dto);
}
