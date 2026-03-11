package at.fhv.e_commerce_application.application.services.user;

import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;

import java.util.List;
import java.util.UUID;

public interface GetUserService {
    GetUserDTO getUser(UUID uuid);
    List<GetUserDTO> getUsers();
}
