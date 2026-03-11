package at.fhv.e_commerce_application.application.services.impl.order;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.order.OrderDTOMapper;
import at.fhv.e_commerce_application.application.services.order.GetOrderService;
import at.fhv.e_commerce_application.domain.model.exception.InvalidOrderDataException;
import at.fhv.e_commerce_application.domain.model.exception.OrderNotFoundException;
import at.fhv.e_commerce_application.domain.model.order.Order;
import at.fhv.e_commerce_application.domain.model.order.OrderRepository;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetOrderServiceImpl implements GetOrderService {

    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;

    public GetOrderServiceImpl(OrderRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public GetOrderDTO getOrderById(UUID orderId) {
        validateOrderId(orderId);

        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        return orderDTOMapper.toGetOrderDTO(order);
    }

    @Override
    public List<GetOrderDTO> getOrdersByUserId(UUID userId) {
        validateUserId(userId);

        return orderRepository.getOrdersByUserId(userId)
                .stream()
                .map(orderDTOMapper::toGetOrderDTO)
                .toList();
    }

    @Override
    public List<GetOrderDTO> getAllOrders() {
        return orderRepository.getAllOrders()
                .stream()
                .map(orderDTOMapper::toGetOrderDTO)
                .toList();
    }

    private void validateOrderId(UUID orderId) {
        if (orderId == null) {
            throw new InvalidOrderDataException("Order ID cannot be null");
        }
    }

    private void validateUserId(UUID userId) {
        if (userId == null) {
            throw new InvalidOrderDataException("User ID cannot be null");
        }
    }
}
