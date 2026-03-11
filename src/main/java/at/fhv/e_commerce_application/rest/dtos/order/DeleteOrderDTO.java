package at.fhv.e_commerce_application.rest.dtos.order;

import java.util.UUID;

public class DeleteOrderDTO {
    private UUID orderId;

    public UUID getOrderId() {
        return orderId;
    }
}
