package at.fhv.e_commerce_application.application.mapper.dtoMapper.order;

import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderItem;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDTOMapperImpl implements  OrderDTOMapper {
    @Override
    public GetOrderDTO toGetOrderDTO(Order order) {
        List<GetOrderItemDTO> items = order.getOrderItems()
                .stream()
                .map(this::toGetOrderItemDTO)
                .toList();

        return new GetOrderDTO(
                order.getId(),
                order.getUserId(),
                order.getStatus().name(),
                items,
                order.getTotalPrice(),
                order.getOrderDate()
        );
    }


    private GetOrderItemDTO toGetOrderItemDTO(OrderItem item) {
        return new GetOrderItemDTO(
                item.getId(),
                item.getProductId(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
