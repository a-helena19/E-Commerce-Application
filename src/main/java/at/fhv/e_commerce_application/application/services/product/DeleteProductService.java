package at.fhv.e_commerce_application.application.services.product;

import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;

import java.util.UUID;

public interface DeleteProductService {
    GetProductDTO deleteProduct(UUID id);
}
