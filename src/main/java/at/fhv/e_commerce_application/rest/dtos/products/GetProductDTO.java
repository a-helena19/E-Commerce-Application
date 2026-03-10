package at.fhv.e_commerce_application.rest.dtos.products;

import java.util.UUID;


public record GetProductDTO(
        UUID id,
        String name,
        String description,
        double price,
        int stock
) {}
