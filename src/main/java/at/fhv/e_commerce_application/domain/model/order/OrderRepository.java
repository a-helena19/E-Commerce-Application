package at.fhv.e_commerce_application.domain.model.order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Order getOrderById(UUID orderId);
    List<Order> getOrdersByUserId(UUID userId);
    List<Order> getAllOrders();
}
