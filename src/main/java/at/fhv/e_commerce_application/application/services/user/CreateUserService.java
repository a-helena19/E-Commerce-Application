package at.fhv.e_commerce_application.application.services.user;

import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import org.springframework.stereotype.Service;

public interface CreateUserService {
    GetUserDTO createUser(String firstName, String lastName, String email);
}
