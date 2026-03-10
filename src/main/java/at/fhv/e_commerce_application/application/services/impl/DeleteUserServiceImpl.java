package at.fhv.e_commerce_application.application.services.impl;

import at.fhv.e_commerce_application.application.services.user.DeleteUserService;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;

    public DeleteUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(UUID id) {
        User existingUser = userRepository.getUserById(id);
        if (existingUser == null) {
            throw new UserNotFoundException("Benutzer mit ID " + id + " nicht gefunden");
        }

        userRepository.deleteUser(id);
    }
}
