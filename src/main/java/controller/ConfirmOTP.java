package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ConfirmOTP", value = "/ConfirmOTP")
public class ConfirmOTP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String otp = request.getParameter("otp");
    HttpSession session = request.getSession();
    String sessionOTP = session.getAttribute("otp").toString();
    if (otp.equalsIgnoreCase(sessionOTP)) {
        request.getRequestDispatcher("/templates/NewPassword.jsp").forward(request, response);
    }
    else {
        request.getRequestDispatcher("/templates/forgot.jsp").forward(request, response);
    }
    }
}
