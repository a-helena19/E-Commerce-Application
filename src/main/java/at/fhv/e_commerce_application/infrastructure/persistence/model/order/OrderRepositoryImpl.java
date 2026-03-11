package at.fhv.e_commerce_application.infrastructure.persistence.model.order;

import at.fhv.e_commerce_application.application.mapper.OrderMapper;
import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJPARepository orderJPARepository;
    private final OrderMapper orderMapper;

    public OrderRepositoryImpl(OrderJPARepository orderJPARepository, OrderMapper orderMapper) {
        this.orderJPARepository = orderJPARepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        OrderEntity savedOrderEntity = orderJPARepository.save(orderEntity);
        return orderMapper.toDomain(savedOrderEntity);
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return orderJPARepository.findById(orderId)
                .map(orderMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByUserId(UUID userId) {
        return orderJPARepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toDomain)
                .toList();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderJPARepository.findAll()
                .stream()
                .map(orderMapper::toDomain)
                .toList();
    }
}
