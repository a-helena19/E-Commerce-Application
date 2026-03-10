package at.fhv.e_commerce_application.application.services.impl.product;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.product.ProductDTOMapper;
import at.fhv.e_commerce_application.application.services.product.GetProductService;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.domain.model.product.ProductStatus;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetProductServiceImpl implements GetProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public GetProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public GetProductDTO getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productDTOMapper.toGetProductDTO(product);
    }

    @Override
    public List<GetProductDTO> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<GetProductDTO> activeProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getStatus() == ProductStatus.ACTIVE) {
                activeProducts.add(productDTOMapper.toGetProductDTO(product));
            }
        }

        return activeProducts;
    }
}
