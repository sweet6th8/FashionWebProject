package controller;


import dao.CategoryDAO;
import dao.DBConnectionPool;
import dao.ProductDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


//Chức năng: Hiển thị trang chính của cửa hàng (home.jsp) với danh sách sản phẩm nổi bật.
////Xử lý: Lấy danh sách sản phẩm từ ProductDAO, thiết lập thuộc tính cho request và chuyển hướng đến home.jsp.
@WebServlet(urlPatterns = "")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) { // Lấy connection từ pool
            String lang = request.getParameter("lang");
            Locale locale;

            if (lang == null) {
                locale = new Locale("en", "US");
            }
            else {
                // Đặt locale dựa trên tham số
                if ("vi".equals(lang)) {
                    locale = new Locale("vi", "VN");
                } else {
                    locale = new Locale("en", "US");
                }
            }
            ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

            // Lưu các giá trị vào request attribute
            request.setAttribute("welcome", bundle.getString("welcome"));
            request.setAttribute("chooseLanguage", bundle.getString("chooseLanguage"));
            ProductDAO productDAO = new ProductDAO(connection);
            List<Product> productList = productDAO.getAllProducts();
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            List<Category> categoryList = categoryDAO.getAllCategories();

            if (productList == null || productList.isEmpty()) {
                System.out.println("No products found!");
            } else {
                System.out.println("Number of products: " + productList.size());
            }
            ServletContext context = getServletContext();
            request.setAttribute("productList", productList);
            context.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/language").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }

    }

}
