package at.fhv.e_commerce_application.domain.model.user;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User createUser(User user);
    User getUserById(UUID id);
    void updateUser(User user);
    void deleteUser(UUID id);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
}
