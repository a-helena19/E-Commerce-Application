package at.fhv.e_commerce_application.rest;

import at.fhv.e_commerce_application.application.services.order.CreateOrderService;
import at.fhv.e_commerce_application.application.services.order.DeleteOrderService;
import at.fhv.e_commerce_application.application.services.order.GetOrderService;
import at.fhv.e_commerce_application.rest.dtos.order.CreateOrderDTO;
import at.fhv.e_commerce_application.rest.dtos.order.GetOrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private final CreateOrderService createOrderService;
    private final DeleteOrderService deleteOrderService;
    private final GetOrderService getOrderService;

    public OrderRestController(CreateOrderService createOrderService, DeleteOrderService deleteOrderService, GetOrderService getOrderService) {
        this.createOrderService = createOrderService;
        this.deleteOrderService = deleteOrderService;
        this.getOrderService = getOrderService;
    }

    @GetMapping
    public ResponseEntity<List<GetOrderDTO>> getAllOrders() {
        List<GetOrderDTO> orders = getOrderService.getAllOrders();
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderDTO> getOrder(@PathVariable UUID id) {
        GetOrderDTO order = getOrderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GetOrderDTO>> getOrdersByUserId(@PathVariable UUID userId) {
        List<GetOrderDTO> orders = getOrderService.getOrdersByUserId(userId);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<GetOrderDTO> createOrder(@Valid @RequestBody CreateOrderDTO dto) {
        GetOrderDTO createdOrder = createOrderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        GetOrderDTO order = getOrderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        deleteOrderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();

    }

}
