package at.fhv.e_commerce_application.rest.dtos.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<CreateOrderItemDTO> items;

    public UUID getUserId() {
        return userId;
    }

    public List<CreateOrderItemDTO> getItems() {
        return items;
    }
}
