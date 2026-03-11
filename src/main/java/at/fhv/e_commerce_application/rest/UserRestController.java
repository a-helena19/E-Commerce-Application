package at.fhv.e_commerce_application.rest;

import at.fhv.e_commerce_application.application.services.user.CreateUserService;
import at.fhv.e_commerce_application.application.services.user.DeleteUserService;
import at.fhv.e_commerce_application.application.services.user.GetUserService;
import at.fhv.e_commerce_application.application.services.user.UpdateUserService;
import at.fhv.e_commerce_application.rest.dtos.user.CreateUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.GetUserDTO;
import at.fhv.e_commerce_application.rest.dtos.user.UpdateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final GetUserService getUserService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;

    public UserRestController(GetUserService getUserService, CreateUserService createUserService,
                              UpdateUserService updateUserService, DeleteUserService deleteUserService) {
        this.getUserService = getUserService;
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
    }

    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        return ResponseEntity.ok(getUserService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getUser(@PathVariable UUID id) {
        GetUserDTO user = getUserService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<GetUserDTO> createUser(@Valid @RequestBody CreateUserDTO user) {
        GetUserDTO createdUser = createUserService.createUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDTO userDTO) {
        GetUserDTO existingUser = getUserService.getUser(id);
        if (existingUser != null) {
            GetUserDTO updatedUser = updateUserService.updateUser(userDTO);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
