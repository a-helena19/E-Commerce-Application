package at.fhv.e_commerce_application.infrastructure.mapper;

import at.fhv.e_commerce_application.application.mapper.OrderMapper;
import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderItem;
import at.fhv.e_commerce_application.domain.model.order.OrderStatus;
import at.fhv.e_commerce_application.infrastructure.persistence.model.order.OrderEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.order.OrderItemEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.order.OrderStatusEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toDomain(OrderEntity orderEntity) {
        List<OrderItem> items = orderEntity.getOrderItems()
                .stream()
                .map(this::toDomainItem)
                .toList();

        return new Order(
                orderEntity.getId(),
                orderEntity.getUserId(),
                OrderStatus.valueOf(orderEntity.getStatus().name()),
                items,
                orderEntity.getTotalPrice(),
                orderEntity.getOrderDate()
        );
    }

    @Override
    public OrderEntity toEntity(Order order) {
            List<OrderItemEntity> itemEntities = order.getOrderItems()
                    .stream()
                    .map(this::toEntityItem)
                    .toList();

            return new OrderEntity(
                    order.getId(),
                    order.getUserId(),
                    OrderStatusEntity.valueOf(order.getStatus().name()),
                    itemEntities,
                    order.getTotalPrice(),
                    order.getOrderDate()
            );
    }

    @Override
    public OrderItem toDomainItem(OrderItemEntity orderItemEntity) {
        return new OrderItem(

                orderItemEntity.getId(),
                orderItemEntity.getProductId(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getPrice()
        );
    }

    @Override
    public OrderItemEntity toEntityItem(OrderItem orderItem) {
        return new OrderItemEntity(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
