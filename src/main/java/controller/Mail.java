package controller;

import Util.EmailUtility;
import dao.UserDAO;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Properties;
import Service.*;

@WebServlet(name = "Mail", value = "/Mail")
public class Mail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String email = request.getParameter("email");
        UserDAO dao = new UserDAO();
        System.out.println(dao.checkEmailExist(email));
        if (!dao.checkEmailExist(email)) {
            request.setAttribute("message" , "email chưa tồn tại !");
            request.getRequestDispatcher("/templates/forgot.jsp").forward(request, response);
        }
        else {

            try {
                String otp = EmailUtility.generateOTP();
                HttpSession session1 = request.getSession();
                session1.setAttribute("otp", otp);
                session1.setAttribute("email", email);

                SendMail service = new SendMail();
                Transport.send(service.sendMail(email, otp));

                request.getRequestDispatcher("/templates/ConfirmOTP.jsp").forward(request, response);
            } catch (MessagingException e) {
                e.printStackTrace();
                response.getWriter().write("Có lỗi xảy ra khi gửi email: " + e.getMessage());
            }
        }

    }
}
