package at.fhv.e_commerce_application.application.services.impl.product;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.product.ProductDTOMapper;
import at.fhv.e_commerce_application.application.services.product.DeleteProductService;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProductServiceImpl implements DeleteProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public DeleteProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public GetProductDTO deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.delete();
        Product deletedProduct = productRepository.save(product);
        return productDTOMapper.toGetProductDTO(deletedProduct);
    }

}
