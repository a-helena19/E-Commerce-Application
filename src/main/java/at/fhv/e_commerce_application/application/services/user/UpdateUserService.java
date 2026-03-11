package at.fhv.e_commerce_application.application.services.user;

import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.UpdateUserDTO;

public interface UpdateUserService {
    GetUserDTO updateUser(UpdateUserDTO userDTO);
}
