package at.fhv.e_commerce_application.application.mapper;

import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.infrastructure.persistence.model.user.UserEntity;

public interface UserMapper {

    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}
