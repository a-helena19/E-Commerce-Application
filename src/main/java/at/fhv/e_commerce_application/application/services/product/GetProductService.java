package at.fhv.e_commerce_application.application.services.product;

import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;

import java.util.List;
import java.util.UUID;

public interface GetProductService {
    GetProductDTO getProductById(UUID id);
    List<GetProductDTO> getAllProducts();
}
