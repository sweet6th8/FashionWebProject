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
import java.util.List;

//Chức năng: Hiển thị danh sách sản phẩm theo danh mục (ví dụ: quần áo nam, quần áo nữ).
//Xử lý: Nhận ID danh mục từ yêu cầu, lấy danh sách sản phẩm theo danh mục từ ProductDAO,
//thiết lập thuộc tính cho request và chuyển hướng đến category.jsp
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            String categoryId = request.getParameter("id");


            ProductDAO productDAO = new ProductDAO();
            List<Product> products;

            // If a category ID is provided, fetch products for that category
            // If the category ID is "all", fetch all products
            if ("all".equals(categoryId)) {
                products = productDAO.getAllProducts(); // Implement this method in ProductDAO
            } else if (categoryId != null) {
                // If a category ID is provided, fetch products for that category
                products = productDAO.getProductsByCategoryId(Integer.parseInt(categoryId));
            } else {
                products = null; // No specific category or all products requested
            }
            // Set product list in request attribute
            request.setAttribute("productList", products);
            // Forward the request to category.jsp to display products
            request.getRequestDispatcher("/templates/category.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }



    }
}
