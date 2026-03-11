package at.fhv.e_commerce_application.application.mapper.dtoMapper.order;

import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.rest.dtos.order.CreateOrderDTO;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;

public interface OrderDTOMapper {
    GetOrderDTO toGetOrderDTO(Order order);
    Order toDomainFromGetDTO(CreateOrderDTO dto);
}
