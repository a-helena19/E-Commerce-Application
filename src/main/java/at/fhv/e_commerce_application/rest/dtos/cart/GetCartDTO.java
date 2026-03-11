package at.fhv.e_commerce_application.rest.dtos.cart;

import java.util.List;
import java.util.UUID;

public record GetCartDTO(
        UUID cartId,
        UUID userId,
        List<GetCartDTO.GetCartItemDTO> items
) {

    public record GetCartItemDTO(
            UUID cartItemId,
            UUID productId,
            int quantity
    ) {}

}
