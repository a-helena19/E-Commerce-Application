package at.fhv.e_commerce_application.application.services.impl.user;

import at.fhv.e_commerce_application.application.mapper.dtoMapper.user.UserDTOMapper;
import at.fhv.e_commerce_application.application.services.user.CreateUserService;
import at.fhv.e_commerce_application.domain.model.cart.Cart;
import at.fhv.e_commerce_application.domain.model.cart.CartRepository;
import at.fhv.e_commerce_application.domain.model.user.User;
import at.fhv.e_commerce_application.domain.model.user.UserRepository;
import at.fhv.e_commerce_application.domain.model.user.UserStatus;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserDTOMapper userDTOMapper;

    public CreateUserServiceImpl(UserRepository userRepository, CartRepository cartRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public GetUserDTO createUser(String firstName, String lastName, String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email '" + email + "' ist bereits registriert");
        }

        User user = new User(null, firstName, lastName, email, UserStatus.ACTIVE);
        User created = userRepository.createUser(user);

        Cart newCart = Cart.create(created.getId());
        cartRepository.save(newCart);

        return userDTOMapper.toGetUserDTO(created);
    }

}
