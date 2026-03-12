package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.GetUserService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.domain.model.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetUserServiceImpl implements GetUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserDTOMapper userDTOMapper;

    public GetUserServiceImpl(UserRepository userRepository, CartRepository cartRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public GetUserDTO getUser(UUID id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        UUID cartId = null;
        try {
            Cart cart = cartRepository.findByUserId(id);
            cartId = cart.getId();
        } catch (Exception e) {
        }

        return userDTOMapper.toGetUserDTO(user, cartId);
    }


        @Override
        public List<GetUserDTO> getUsers() {
            List<User> users = userRepository.findAll();
            List<GetUserDTO> result = new ArrayList<>();

            for (User user : users) {
                UUID cartId = null;
                try {
                    Cart cart = cartRepository.findByUserId(user.getId());
                    cartId = cart.getId();
                } catch (Exception e) {
                }
                result.add(userDTOMapper.toGetUserDTO(user, cartId));
            }

            return result;
        }
}