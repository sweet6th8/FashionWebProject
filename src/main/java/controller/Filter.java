package controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebFilter("/secure/*")
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null) {
            res.sendRedirect(req.getContextPath() + "/templates/login.jsp");
        }
        if (!role.equals("guest")) {
            chain.doFilter(request, response);

        }
        res.sendRedirect(req.getContextPath() + "/templates/login.jsp");
    }
}
