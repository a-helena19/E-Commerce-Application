package at.fhv.e_commerce_application.application.mapper;

import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderItem;
import at.fhv.e_commerce_application.infrastructure.persistence.model.order.OrderEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.order.OrderItemEntity;

public interface OrderMapper {
    Order toDomain(OrderEntity orderEntity);
    OrderEntity toEntity(Order order);
    OrderItem toDomainItem(OrderItemEntity orderItemEntity);
    OrderItemEntity toEntityItem(OrderItem orderItem);
}
