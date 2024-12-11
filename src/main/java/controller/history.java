package controller;
import dao.DBConnectionPool;
import dao.ProductDAO;
import jakarta.servlet.ServletContext;
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
import java.util.List;
@WebServlet(urlPatterns = {"/secure/history"})
public class history extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            String message = "You are not logged in";
            req.setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/templates/login.jsp");

        } else {
            ProductDAO pd = null;
            try {
                pd = new ProductDAO(DBConnectionPool.getDataSource().getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            List<Product> products = pd.getAllProducts();
            ServletContext context = req.getServletContext();
            context.setAttribute("products", products);
            resp.sendRedirect(req.getContextPath() + "/templates/historyProduct.jsp");
        }
    }
}
