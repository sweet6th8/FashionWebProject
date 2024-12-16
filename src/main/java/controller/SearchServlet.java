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

//Tìm kiếm theo tên product
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) { // Lấy connection từ pool
            resp.setContentType("text/html; charset=UTF-8");
            req.setCharacterEncoding("UTF-8");// tìm tên tiếng việt
            String txtSearch = req.getParameter("txt");
            ProductDAO dao = new ProductDAO();
            List<Product> products = dao.searchProductByName(txtSearch);


            req.setAttribute("productList", products);
            req.setAttribute("txtS", txtSearch);
            resp.sendRedirect(getServletContext().getContextPath() + "/templates/category.jsp");
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }


    }
}
