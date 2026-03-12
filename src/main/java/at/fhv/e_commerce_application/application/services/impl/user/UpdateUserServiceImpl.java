package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.UpdateUserService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.exception.CartNotFoundException;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.UpdateUserDTO;
import at.fhv.e_commerce_application.domain.model.exception.EmailAlreadyExistsException;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserDTOMapper userDTOMapper;

    public UpdateUserServiceImpl(UserRepository userRepository, CartRepository cartRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public GetUserDTO updateUser(UpdateUserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId());
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + userDTO.getId() + " not found");
        }

        if (!existingUser.getEmail().equalsIgnoreCase(userDTO.getEmail().trim()) &&
            userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + userDTO.getEmail() + "' is already registered");
        }

        existingUser.update(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());

        User updatedUser = userRepository.save(existingUser);
        UUID cartId = null;
        try {
            Cart cart = cartRepository.findByUserId(updatedUser.getId());
            cartId = cart.getId();
        } catch (CartNotFoundException e) {
        }

        return userDTOMapper.toGetUserDTO(updatedUser, cartId);
    }
}
