package at.fhv.e_commerce_application.infrastructure.mapper;

import at.fhv.e_commerce_application.application.mapper.UserMapper;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserStatus;
import at.fhv.e_commerce_application.infrastructure.persistence.model.user.UserEntity;
import at.fhv.e_commerce_application.infrastructure.persistence.model.user.UserStatusEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
            userEntity.getId(),
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail(),
            UserStatus.valueOf(userEntity.getStatus().name())
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        UserStatusEntity statusEntity = UserStatusEntity.valueOf(user.getStatus().name());
        return new UserEntity(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            statusEntity
        );
    }
}
