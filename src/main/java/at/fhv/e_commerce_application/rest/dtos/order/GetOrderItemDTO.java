package at.fhv.e_commerce_application.rest.dtos.order;


import java.math.BigDecimal;
import java.util.UUID;

public record GetOrderItemDTO(
        UUID id,
        UUID productId,
        int quantity,
        BigDecimal price
){}
