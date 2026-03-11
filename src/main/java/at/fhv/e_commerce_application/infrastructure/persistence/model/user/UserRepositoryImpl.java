package at.fhv.e_commerce_application.infrastructure.persistence.model.user;

import at.fhv.e_commerce_application.application.mapper.UserMapper;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserJPARepository userJPARepository, UserMapper userMapper) {
        this.userJPARepository = userJPARepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userJPARepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public User getUserById(UUID id) {
        UserEntity userEntity = userJPARepository.findById(id).orElse(null);
        return userMapper.toDomain(userEntity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = userJPARepository.findById(user.getId()).orElse(null);
        if (userEntity != null) {
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setStatus(UserStatusEntity.valueOf(user.getStatus().name()));
            userJPARepository.save(userEntity);
        }
    }

    @Override
    public void deleteUser(UUID id) {
        UserEntity userEntity = userJPARepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setStatus(UserStatusEntity.INACTIVE);
            userJPARepository.save(userEntity);
        }
    }

    @Override
    public List<User> getAllUsers() {
        var userEntities = userJPARepository.findAll();
        return userEntities.stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJPARepository.existsByEmail(email);
    }
}

