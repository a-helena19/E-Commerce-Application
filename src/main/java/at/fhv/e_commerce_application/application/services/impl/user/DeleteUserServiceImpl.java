package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.services.user.DeleteUserService;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public DeleteUserServiceImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void deleteUser(UUID id) {
        User existingUser = userRepository.getUserById(id);
        if (existingUser == null) {
            throw new UserNotFoundException("Benutzer mit ID " + id + " nicht gefunden");
        }
        cartRepository.deleteByUserId(id);
        userRepository.deleteUser(id);
    }
}
