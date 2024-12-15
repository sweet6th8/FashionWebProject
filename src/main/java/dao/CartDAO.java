package dao;

import model.Cart;
import model.CartItem;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {
    private Connection connection;
    private ProductDAO productDAO;
    public CartDAO(Connection connection) {
        this.connection = connection;
        this.productDAO = new ProductDAO(connection);
    }

    // Lấy giỏ hàng của người dùng theo userId
    public Cart getCartByUserId(int userId) {
        Cart cart = null;
        String queryCart = "SELECT * FROM Cart WHERE id = ?";
        String queryCartItems = "SELECT * FROM CartItem WHERE id = ?";
        try (PreparedStatement stmtCart = connection.prepareStatement(queryCart);
             PreparedStatement stmtCartItems = connection.prepareStatement(queryCartItems)) {

            stmtCart.setString(1, userId+"");
            ResultSet rsCart = stmtCart.executeQuery();
            if (rsCart.next()) {
                cart = new Cart(rsCart.getInt("id"), userId);

                stmtCartItems.setString(1, userId+"");
                ResultSet rsItems = stmtCartItems.executeQuery();
                while (rsItems.next()) {
                    // Nạp từng CartItem vào cart
                    Product product = productDAO.getProductById(rsItems.getInt("id"));
                    CartItem cartItem = new CartItem(product, rsItems.getInt("quantity"));
                    cart.addItem(product, cartItem.getQuantity());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }



    // Tạo giỏ hàng mới cho người dùng
    public void createCart(int userId) {
        String query = "INSERT INTO Cart (user_id) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();

            // Lấy cartId (id của giỏ hàng vừa tạo) từ database
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int cartId = rs.getInt(1);  // Lấy giá trị cartId được sinh tự động
                    // Tạo giỏ hàng mới với cartId
                    Cart cart = new Cart(cartId, userId); // Giả sử bạn đã thêm constructor phù hợp trong Cart
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
