package at.fhv.e_commerce_application.application.mapper.dtoMapper.user;

import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserStatus;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public GetUserDTO toGetUserDTO(User user) {
        return new GetUserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus().name()
        );
    }

    @Override
    public User toDomainFromGetDTO(GetUserDTO getUserDTO) {
        return User.reconstitute(
                getUserDTO.id(),
                getUserDTO.firstName(),
                getUserDTO.lastName(),
                getUserDTO.email(),
                UserStatus.valueOf(getUserDTO.status())
        );
    }
}
