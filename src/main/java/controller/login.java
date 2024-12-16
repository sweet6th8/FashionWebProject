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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check if session is existed
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            resp.sendRedirect(getServletContext().getContextPath() + "/");
        }
        else {
            req.getRequestDispatcher(req.getContextPath()).forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) { // Lấy connection từ pool

            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            String message = "Sai thông tin tài khoản mật khẩu ";
            UserDAO udao = new UserDAO();
            String role = email.equals("admin@gmail.com") ? "admin" : "user";
            HttpSession session = req.getSession();
            try {
                User user = udao.getLogin(email, pass);
                System.out.println(user);
                int userId = user.getId();
                session.setAttribute("userId", userId);
                session.setAttribute("role", role);
                session.setAttribute("user", user);
                // fix here, tam thoi dung sendRedirect nen luu user trong session
                resp.sendRedirect(req.getContextPath());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new ServletException("Error connecting to the database", e);
        }


    }
}
