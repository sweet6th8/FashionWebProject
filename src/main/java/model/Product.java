package model;

public class Product {
    private int id; // ID sản phẩm
    private String name; // Tên sản phẩm
    private String description; // Mô tả sản phẩm
    private String photo; // URL ảnh sản phẩm (ánh xạ từ cột `photo`)
    private double price; // Giá sản phẩm
    private double discount; // Giảm giá
    private Category category; // Đối tượng Category ánh xạ từ category_id

    public Product() {
    }

    public Product(int id, String name, String description, String photo, double price, double discount, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.discount = discount;
        this.category = category;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
