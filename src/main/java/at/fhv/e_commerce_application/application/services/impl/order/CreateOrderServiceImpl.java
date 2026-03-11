package at.fhv.e_commerce_application.application.services.impl.order;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.order.OrderDTOMapper;
import at.fhv.e_commerce_application.application.services.cart.ClearCartService;
import at.fhv.e_commerce_application.application.services.order.CreateOrderService;
import at.fhv.e_commerce_application.domain.model.exception.InvalidOrderDataException;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductOutOfStockException;
import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderItem;
import at.fhv.e_commerce_application.domain.model.order.OrderRepository;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import at.fhv.e_commerce_application.domain.model.product.ProductStatus;
import at.fhv.e_commerce_application.rest.dtos.order.CreateOrderDTO;
import at.fhv.e_commerce_application.rest.dtos.order.CreateOrderItemDTO;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClearCartService clearCartService;
    private final OrderDTOMapper orderDTOMapper;

    public CreateOrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, ClearCartService clearCartService, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clearCartService = clearCartService;
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    @Transactional
    public GetOrderDTO createOrder(CreateOrderDTO dto) {
        validateOrderDTO(dto);

        // Aggregate quantities per product (in case same product appears multiple times)
        Map<UUID, Integer> aggregatedQuantities = new HashMap<>();
        for (CreateOrderItemDTO itemDTO : dto.getItems()) {
            aggregatedQuantities.merge(itemDTO.getProductId(), itemDTO.getQuantity(), Integer::sum);
        }

        // Validate stock availability for all products
        Map<UUID, Product> products = new HashMap<>();
        for (Map.Entry<UUID, Integer> entry : aggregatedQuantities.entrySet()) {
            UUID productId = entry.getKey();
            int totalQuantity = entry.getValue();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

            if (product.getStatus() != ProductStatus.ACTIVE) {
                throw new InvalidOrderDataException("Product with ID " + productId + " is not available for ordering");
            }

            if (product.getStock() < totalQuantity) {
                throw new ProductOutOfStockException(productId, totalQuantity, product.getStock());
            }

            products.put(productId, product);
        }

        // Create order items and calculate prices
        List<OrderItem> orderItems = new ArrayList<>();
        for (CreateOrderItemDTO itemDTO : dto.getItems()) {
            Product product = products.get(itemDTO.getProductId());
            double itemPrice = product.getPrice() * itemDTO.getQuantity();
            OrderItem orderItem = new OrderItem(null, itemDTO.getProductId(), itemDTO.getQuantity(), itemPrice);
            orderItems.add(orderItem);
        }

        // Create and save order
        Order order = Order.create(dto.getUserId(), orderItems);
        Order savedOrder = orderRepository.save(order);

        // Reduce stock for all products
        for (Map.Entry<UUID, Integer> entry : aggregatedQuantities.entrySet()) {
            Product product = products.get(entry.getKey());
            product.reduceStock(entry.getValue());
            productRepository.save(product);
        }

        // Clear user's cart after successful order
        clearCartService.clearCartByUserId(dto.getUserId());

        return orderDTOMapper.toGetOrderDTO(savedOrder);
    }

    private void validateOrderDTO(CreateOrderDTO dto) {
        if (dto == null) {
            throw new InvalidOrderDataException("Order cannot be null");
        }

        if (dto.getUserId() == null) {
            throw new InvalidOrderDataException("User ID cannot be null");
        }

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new InvalidOrderDataException("Order must contain at least one item");
        }

        validateOrderItems(dto.getItems());
    }

    private void validateOrderItems(List<CreateOrderItemDTO> items) {
        for (CreateOrderItemDTO item : items) {
            if (item.getProductId() == null) {
                throw new InvalidOrderDataException("Product ID cannot be null for order item");
            }

            if (item.getQuantity() <= 0) {
                throw new InvalidOrderDataException("Quantity must be greater than zero");
            }
        }
    }
}
