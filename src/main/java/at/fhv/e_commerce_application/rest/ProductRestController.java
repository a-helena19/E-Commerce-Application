package at.fhv.e_commerce_application.rest;

import at.fhv.e_commerce_application.application.services.product.CreateProductService;
import at.fhv.e_commerce_application.application.services.product.DeleteProductService;
import at.fhv.e_commerce_application.application.services.product.GetProductService;
import at.fhv.e_commerce_application.application.services.product.UpdateProductService;
import at.fhv.e_commerce_application.rest.dtos.products.CreateProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.GetProductDTO;
import at.fhv.e_commerce_application.rest.dtos.products.UpdateProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;

    public ProductRestController(CreateProductService createProductService, GetProductService getProductService, UpdateProductService updateProductService, DeleteProductService deleteProductService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<GetProductDTO> createProduct(@RequestBody CreateProductDTO dto) {
        GetProductDTO createdProduct = createProductService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<List<GetProductDTO>> getAllProducts() {
        List<GetProductDTO> products = getProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GetProductDTO> getProductById(@PathVariable UUID id) {
        GetProductDTO product = getProductService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GetProductDTO> updateProduct(@PathVariable UUID id, @RequestBody UpdateProductDTO dto) {
        GetProductDTO updatedProduct = updateProductService.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<GetProductDTO> deleteProduct(@PathVariable UUID id) {
        GetProductDTO deletedProduct = deleteProductService.deleteProduct(id);
        return ResponseEntity.ok(deletedProduct);
    }
}
