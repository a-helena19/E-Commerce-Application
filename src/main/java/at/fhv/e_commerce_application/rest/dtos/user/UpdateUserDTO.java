package at.fhv.e_commerce_application.rest.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UpdateUserDTO {
    @NotNull(message = "User ID ist erforderlich")
    private UUID id;

    @NotBlank(message = "Vorname ist erforderlich")
    @Size(min = 2, max = 100, message = "Vorname muss zwischen 2 und 100 Zeichen lang sein")
    private String firstName;

    @NotBlank(message = "Nachname ist erforderlich")
    @Size(min = 2, max = 100, message = "Nachname muss zwischen 2 und 100 Zeichen lang sein")
    private String lastName;

    @NotBlank(message = "Email ist erforderlich")
    @Email(message = "Email muss ein gültiges Format haben")
    @Size(max = 255, message = "Email darf maximal 255 Zeichen lang sein")
    private String email;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(UUID id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
