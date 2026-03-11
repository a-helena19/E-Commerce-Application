package at.fhv.e_commerce_application.application.services.order;

import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;

import java.util.List;
import java.util.UUID;

public interface GetOrderService {
    GetOrderDTO getOrderById(UUID orderId);

    List<GetOrderDTO> getOrdersByUserId(UUID userId);

    List<GetOrderDTO> getAllOrders();
}
