package at.fhv.e_commerce_application.application.mapper.dtoMapper.user;

import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;

import java.util.UUID;

public interface UserDTOMapper {
    GetUserDTO toGetUserDTO(User user, UUID cartId);
    User toDomainFromGetDTO(GetUserDTO getUserDTO);
}
