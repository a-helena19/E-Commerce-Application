package at.fhv.e_commerce_application.rest.dtos.products;

public class CreateProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private String status;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String name, String description, double price, int stock, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
