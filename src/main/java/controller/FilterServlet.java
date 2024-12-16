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

@WebServlet("/Filter")
public class FilterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {

            String minPrice = req.getParameter("minPrice");
            String maxPrice = req.getParameter("maxPrice");
            System.out.println("minPrice: " + minPrice);  // Debugging line
            System.out.println("maxPrice: " + maxPrice);  // Debugging line

            ProductDAO dao = new ProductDAO();
            List<Product> products = dao.filteringProductByPrice(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
            req.setAttribute("productList", products);

            req.getRequestDispatcher("/templates/category.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }


    }
}
