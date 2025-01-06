package controller;

import dao.DBConnectionPool;
import dao.ProductDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/secure/user/history"})
public class history extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
     int userID = (int) session.getAttribute("userId");
        System.out.println(userID);
        ProductDAO pd = new ProductDAO();
        List<Product> products = pd.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/templates/historyProduct.jsp").include(req, resp);

    }
}
