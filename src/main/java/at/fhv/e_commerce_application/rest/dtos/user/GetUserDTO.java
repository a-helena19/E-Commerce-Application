package at.fhv.e_commerce_application.rest.dtos.user;

import java.util.UUID;

public record GetUserDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String status,
        UUID cartId
) {
}
