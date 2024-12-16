package controller;

import dao.DBConnectionPool;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

import java.io.IOException;
import java.sql.Connection;

//Chức năng: Hiển thị thông tin chi tiết của một sản phẩm cụ thể.
//Xử lý: Nhận ID sản phẩm từ yêu cầu, lấy thông tin sản phẩm từ ProductDAO,
//thiết lập thuộc tính cho request và chuyển hướng đến product-detail.jsp.
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) { // Lấy connection từ pool

            // Nhận ID sản phẩm từ yêu cầu
            String productId = request.getParameter("id");

            if (productId != null) {
                // Lấy thông tin sản phẩm từ ProductDAO
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProductById(Integer.parseInt(productId));

                // Kiểm tra nếu sản phẩm tồn tại
                if (product != null) {
                    // Thiết lập thuộc tính cho request
                    request.setAttribute("product", product);
                    // Chuyển hướng đến product-detail.jsp
                    request.getRequestDispatcher("/templates/product-detail.jsp").forward(request, response);
                } else {
                    // Xử lý khi không tìm thấy sản phẩm
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                }
            } else {
                // Xử lý khi không có ID sản phẩm
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
            }
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }

    }
}
