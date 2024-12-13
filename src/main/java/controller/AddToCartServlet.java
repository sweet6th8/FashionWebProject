package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.DBConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            // Logging bước đầu
            System.out.println("Processing addToCart request");

            // Xử lý session
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/templates/login.jsp");
                return;
            }

            int userId = (int) session.getAttribute("userId");
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Logging dữ liệu đầu vào
            System.out.println("User ID: " + userId + ", Product ID: " + productId + ", Quantity: " + quantity);

            // Kiểm tra giỏ hàng
            CartDAO cartDAO = new CartDAO(connection);
            Cart cart = cartDAO.getCartByUserId(userId);
            if (cart == null) {
                System.out.println("No cart found for user, creating a new cart...");
                cartDAO.createCart(userId);
                cart = cartDAO.getCartByUserId(userId);
            }

            // Kiểm tra xem cart có giá trị hợp lệ không
            if (cart == null) {
                throw new ServletException("Failed to create or retrieve cart for user ID: " + userId);
            }
            System.out.println("Cart ID: " + cart.getCartId());  // In cart ID để kiểm tra

            // Lấy thông tin sản phẩm
            Product product = getProductById(connection, productId);
            if (product == null) {
                throw new ServletException("Product not found: ID = " + productId);
            }

            // Thêm vào giỏ hàng
            CartItemDAO cartItemDAO = new CartItemDAO(connection);
            cart.addItem(product, quantity);
            cartItemDAO.addCartItem(cart.getCartId() , productId, quantity, product.getPrice()); // Truyền cart.getId()

            response.sendRedirect("cart");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error connecting to the database", e);
        }
    }

    private Product getProductById(Connection connection, int productId) throws SQLException {
        String query = "SELECT p.id, p.name, p.description, p.photo, p.price, p.discount, " +
                "c.id AS category_id, c.title AS category_title, c.description AS category_description " +
                "FROM Product p " +
                "LEFT JOIN Category c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Category category = null;
                int categoryId = rs.getInt("category_id");
                if (!rs.wasNull()) {
                    category = new Category(categoryId, rs.getString("category_title"), rs.getString("category_description"));
                }

                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("photo"),
                        rs.getDouble("price"),
                        rs.getDouble("discount"),
                        category
                );
            }
        }
        return null;
    }
}
