package model;

public class CartItem {
    private Product product;  // Sản phẩm trong giỏ hàng
    private int quantity;     // Số lượng của sản phẩm trong giỏ hàng

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Tính tổng giá trị của sản phẩm (giá sau khi giảm giá * số lượng)
    public double getTotalPrice() {
        return product.getPrice() * quantity * (1 - product.getDiscount());
    }

    // Lấy giá sau giảm giá của sản phẩm
    public double getDiscountedPrice() {
        return product.getPrice() * (1 - product.getDiscount());
    }
}
