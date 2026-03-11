package at.fhv.e_commerce_application.application.services.order;

import at.fhv.e_commerce_application.rest.dtos.order.CreateOrderDTO;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;

public interface CreateOrderService {
    GetOrderDTO createOrder(CreateOrderDTO dto);
}
