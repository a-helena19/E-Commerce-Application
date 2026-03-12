package at.fhv.e_commerce_application.domain.model.user;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User findById(UUID id);
    List<User> findAll();
    boolean existsByEmail(String email);
}
