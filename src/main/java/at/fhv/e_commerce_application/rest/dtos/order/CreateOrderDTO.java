package at.fhv.e_commerce_application.rest.dtos.order;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {

    private UUID userId;
    private List<CreateOrderItemDTO> items;

    public UUID getUserId() {
        return userId;
    }

    public List<CreateOrderItemDTO> getItems() {
        return items;
    }
}
