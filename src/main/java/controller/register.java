package controller;

import dao.UserDAO;
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
import java.util.ArrayList;

@WebServlet (urlPatterns = {"/templates/register"})
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String FirstName = req.getParameter("FirstName");
            String LastName = req.getParameter("LastName");
        String Email = req.getParameter("Email");
        boolean gender = req.getParameter("gender").equalsIgnoreCase("1");
        String password = req.getParameter("password");
        String checkPassword = req.getParameter("CheckPassword");
        String city = req.getParameter("city");
        String phone  = req.getParameter("phone");

        User user = new User();
        user.setFullName(FirstName.concat(" " +LastName));
        user.setGender(gender);
        user.setEmail(Email);
        user.setId(1);
        user.setPhone(phone);
        user.setUsername(FirstName);
        user.setAddress(city);
        user.setFavoriteProducts(new ArrayList<Product>());
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.registerUsser(user)) {
                resp.sendRedirect(req.getContextPath()+"/templates/login.jsp");

                return ;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        resp.sendRedirect(req.getContextPath()+"/templates/register.jsp");
    }
}
