package at.fhv.e_commerce_application.application.services.order;

import java.util.UUID;

public interface DeleteOrderService {

    void deleteOrderById(UUID orderId);
}
