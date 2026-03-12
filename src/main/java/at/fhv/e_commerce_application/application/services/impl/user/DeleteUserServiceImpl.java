package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.services.cart.DeleteCartService;
import at.fhv.e_commerce_application.application.services.user.DeleteUserService;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;
    private final DeleteCartService deleteCartService;

    public DeleteUserServiceImpl(UserRepository userRepository, DeleteCartService deleteCartService) {
        this.userRepository = userRepository;
        this.deleteCartService = deleteCartService;
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        existingUser.deactivate();
        userRepository.save(existingUser);

        deleteCartService.deleteCartByUserId(id);
    }
}
