package at.fhv.e_commerce_application.rest.dtos.products;

import java.math.BigDecimal;
import java.util.UUID;


public record GetProductDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String status
) {}
