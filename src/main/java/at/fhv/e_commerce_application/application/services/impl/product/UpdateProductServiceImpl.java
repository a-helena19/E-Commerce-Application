package at.fhv.e_commerce_application.application.services.impl.product;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.product.ProductDTOMapper;
import at.fhv.e_commerce_application.application.services.product.UpdateProductService;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.UpdateProductDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public UpdateProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public GetProductDTO updateProduct(UUID id, UpdateProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.update(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStock());
        Product updatedProduct = productRepository.save(product);
        return productDTOMapper.toGetProductDTO(updatedProduct);
    }
}
