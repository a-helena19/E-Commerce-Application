package at.fhv.e_commerce_application.rest.dtos.order;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetOrderDTO(
        UUID id,
        UUID userId,
        String status,
        List<GetOrderItemDTO> items,
        double totalPrice,
        LocalDateTime orderDate
) {
}
