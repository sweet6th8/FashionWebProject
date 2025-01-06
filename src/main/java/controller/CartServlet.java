package controller;

import dao.CartDAO;
import dao.DBConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

import java.io.IOException;
import java.sql.Connection;


@WebServlet("/secure/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/templates/login.jsp"); // Redirect to login page
                return;
            }
            int userId = (int) session.getAttribute("userId");  // Giả định rằng userId đã được lưu trong session

            // Lấy giỏ hàng từ cơ sở dữ liệu
            CartDAO cartDAO = new CartDAO(connection);
            Cart cart = cartDAO.getCartByUserId(userId);
            if (cart != null) {
                request.setAttribute("cart", cart);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/cart.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/emptyCart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error fetching cart", e);
        }
    }
}
