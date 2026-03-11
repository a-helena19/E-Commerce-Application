package at.fhv.e_commerce_application.rest.dtos.user;

import java.util.UUID;

public class DeleteUserDTO {
        private UUID id;

        public DeleteUserDTO() {
        }

        public DeleteUserDTO(UUID id) {
            this.id = id;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }
}
