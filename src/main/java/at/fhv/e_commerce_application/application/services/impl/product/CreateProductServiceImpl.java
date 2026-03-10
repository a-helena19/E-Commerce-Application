package at.fhv.e_commerce_application.application.services.impl.product;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.product.ProductDTOMapper;
import at.fhv.e_commerce_application.application.services.product.CreateProductService;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateProductServiceImpl implements CreateProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public CreateProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public GetProductDTO createProduct(CreateProductDTO dto) {
        Product product = productDTOMapper.toDomainCreateProductDTO(dto);
        Product savedProduct = productRepository.save(product);
        return productDTOMapper.toGetProductDTO(savedProduct);
    }
}
