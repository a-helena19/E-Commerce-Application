package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.GetUserService;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetUserServiceImpl implements GetUserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public GetUserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public GetUserDTO getUser(UUID id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return userDTOMapper.toGetUserDTO(user);
    }

    @Override
    public List<GetUserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userDTOMapper::toGetUserDTO)
                .toList();
    }
}