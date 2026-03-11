package at.fhv.e_commerce_application.rest.dtos.cart;

public class UpdateCartItemDTO {
    private int quantity;

    public UpdateCartItemDTO() {
    }

    public UpdateCartItemDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
