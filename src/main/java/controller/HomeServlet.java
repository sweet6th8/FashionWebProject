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
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


//Chức năng: Hiển thị trang chính của cửa hàng (home.jsp) với danh sách sản phẩm nổi bật.

/// /Xử lý: Lấy danh sách sản phẩm từ ProductDAO, thiết lập thuộc tính cho request và chuyển hướng đến home.jsp.
@WebServlet(urlPatterns = "")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lang = request.getParameter("lang");
        Locale locale;

        if (lang == null) locale = new Locale("en", "US");
        else {
            locale = (lang.equals("vi")) ? new Locale("vi", "VN") : new Locale("en", "US");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        request.setAttribute("welcome", bundle.getString("welcome"));

        List<Product> productList = new ProductDAO().getAllProducts();
        List<Category> categoryList = new CategoryDAO().getAllCategories();

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null) role = "guest";
        session.setAttribute("role", role);
        request.setAttribute("productList", productList);
        ServletContext context = getServletContext();
        context.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);


    }

}
