package at.fhv.e_commerce_application.infrastructure.persistence.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJPARepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
