package controller;

import Util.DataService;
import Util.validation;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/secure/user/edit"})
public class EditProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("user_id"));
        UserDAO dao = new UserDAO();
        User user = dao.getUser(id);
        req.setAttribute("user", user);
        req.setAttribute("cities", DataService.cities());
        req.getRequestDispatcher("/templates/edit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Fname = request.getParameter("FirstName");
        String Lname = request.getParameter("LastName");
        System.out.println(Fname +" " + Lname);
        String email = request.getParameter("Email");
        String password = request.getParameter("password");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String checkPassword = request.getParameter("checkPassword");
        String phone = request.getParameter("phone");

        UserDAO userDAO = new UserDAO();
    }
}
