package at.fhv.e_commerce_application.application.services.impl.order;

import at.fhv.e_commerce_application.application.services.order.DeleteOrderService;
import at.fhv.e_commerce_application.domain.model.exception.InvalidOrderDataException;
import at.fhv.e_commerce_application.domain.model.exception.OrderCannotBeCancelledException;
import at.fhv.e_commerce_application.domain.model.exception.OrderNotFoundException;
import at.fhv.e_commerce_application.domain.model.exception.ProductNotFoundException;
import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderItem;
import at.fhv.e_commerce_application.domain.model.order.OrderRepository;
import at.fhv.e_commerce_application.domain.model.order.OrderStatus;
import at.fhv.e_commerce_application.domain.model.product.Product;
import at.fhv.e_commerce_application.domain.model.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DeleteOrderServiceImpl implements DeleteOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public DeleteOrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void deleteOrderById(UUID orderId) {

        validateOrderId(orderId);

        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new OrderCannotBeCancelledException(orderId,
                "Order is in status " + order.getStatus() + " and cannot be cancelled");
        }

        // Aggregate quantities per product for stock restoration
        Map<UUID, Integer> aggregatedQuantities = new HashMap<>();
        for (OrderItem item : order.getOrderItems()) {
            aggregatedQuantities.merge(item.getProductId(), item.getQuantity(), Integer::sum);
        }

        // Restore stock for all products
        for (Map.Entry<UUID, Integer> entry : aggregatedQuantities.entrySet()) {
            Product product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new ProductNotFoundException(entry.getKey()));
            product.increaseStock(entry.getValue());
            productRepository.save(product);
        }

        order.cancel();
        orderRepository.save(order);
    }

    private void validateOrderId(UUID orderId) {
        if (orderId == null) {
            throw new InvalidOrderDataException("Order ID cannot be null");
        }
    }
}
