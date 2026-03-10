package at.fhv.e_commerce_application.application.services.impl;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.UpdateUserService;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.UpdateUserDTO;
import at.fhv.e_commerce_application.rest.exception.EmailAlreadyExistsException;
import at.fhv.e_commerce_application.rest.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UpdateUserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public GetUserDTO updateUser(UpdateUserDTO userDTO) {
        User existingUser = userRepository.getUserById(userDTO.getId());
        if (existingUser == null) {
            throw new UserNotFoundException("Benutzer mit ID " + userDTO.getId() + " nicht gefunden");
        }

        if (!existingUser.getEmail().equals(userDTO.getEmail()) &&
            userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + userDTO.getEmail() + "' ist bereits registriert");
        }

        User updatedUser = new User(
            userDTO.getId(),
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getEmail(),
            existingUser.getStatus()
        );

        userRepository.updateUser(updatedUser);

        User reloadedUser = userRepository.getUserById(userDTO.getId());
        return userDTOMapper.toGetUserDTO(reloadedUser);
    }
}
