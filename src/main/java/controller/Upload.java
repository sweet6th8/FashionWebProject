package controller;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/secure/user/upload"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Upload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        try {
            String img = dao.getUserImg(id);
            request.setAttribute("img", img);
            request.getRequestDispatcher("/templates/avatar.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String root ="C:/Users/nhuta/Downloads/FashionWebProject/src/main/webapp";
        String path = "/static/images/avatars/" + fileName;
        for (Part part : request.getParts()) {
            part.write(root +path );
        }
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        UserDAO dao = new UserDAO();
        dao.saveImg(path, id);
        doGet(request, response);
    }
}
