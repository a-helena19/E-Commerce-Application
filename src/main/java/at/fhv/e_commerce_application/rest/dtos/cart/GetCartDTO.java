package at.fhv.e_commerce_application.rest.dtos.cart;

import java.util.List;
import java.util.UUID;

public record GetCartDTO(
        UUID id,
        UUID userId,
        List<GetCartDTO.GetCartItemDTO> items
) {

    public record GetCartItemDTO(
            UUID productId,
            int quantity
    ) {}

}
