package at.fhv.e_commerce_application.infrastructure.persistence.model.cart;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items = new ArrayList<>();

    public CartEntity() {}

    public CartEntity(UUID id, UUID userId, List<CartItemEntity> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }


    public UUID getUserId() {
        return userId;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }
}
