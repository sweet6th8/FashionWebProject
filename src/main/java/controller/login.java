package controller;

import dao.DBConnectionPool;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "DangNhap", urlPatterns = {"/templates/login"})
public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) { // Lấy connection từ pool

            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            String message = "Sai thông tin tài khoản mật khẩu ";
            UserDAO udao = new UserDAO(connection);
            HttpSession session = req.getSession();
            try {
                User user = udao.getLogin(email, pass);
                System.out.println(user);
                int userId = user.getId();
                if (user != null) {

                    session.setAttribute("userId", userId);

                    session.setAttribute("user", user);
                    // fix here, tam thoi dung sendRedirect nen luu user trong session
                    resp.sendRedirect(req.getContextPath());
                } else {
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("/templates/login.jsp").forward(req,resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }


    }
}
