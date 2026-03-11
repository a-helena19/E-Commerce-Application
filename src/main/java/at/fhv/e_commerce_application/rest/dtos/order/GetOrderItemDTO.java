package at.fhv.e_commerce_application.rest.dtos.order;


import java.util.UUID;

public record GetOrderItemDTO(
        UUID id,
        UUID productId,
        int quantity,
        double price
){}
