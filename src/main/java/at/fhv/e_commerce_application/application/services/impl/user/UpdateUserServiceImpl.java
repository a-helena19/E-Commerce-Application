package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.UpdateUserService;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.UpdateUserDTO;
import at.fhv.e_commerce_application.domain.model.exception.EmailAlreadyExistsException;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
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

        if (!existingUser.getEmail().equalsIgnoreCase(userDTO.getEmail().trim()) &&
            userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + userDTO.getEmail() + "' is already registered");
        }

        existingUser.update(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());

        userRepository.updateUser(existingUser);

        return userDTOMapper.toGetUserDTO(existingUser);
    }
}
