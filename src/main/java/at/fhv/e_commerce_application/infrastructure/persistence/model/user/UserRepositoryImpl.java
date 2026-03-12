package at.fhv.e_commerce_application.infrastructure.persistence.model.user;

import at.fhv.e_commerce_application.application.mapper.UserMapper;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
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
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userJPARepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public User findById(UUID id) {
        return userJPARepository.findById(id)
                .map(userMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userJPARepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJPARepository.existsByEmail(email);
    }
}

