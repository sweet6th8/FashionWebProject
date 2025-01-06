// CategoriesDAO.java
package dao;

import model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public CategoryDAO() {

    }
    public List<Category> getAllCategories() {
        List<Category> categoriesList = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setTitle(rs.getString("title"));
                category.setDescription(rs.getString("description"));
                categoriesList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    // Thêm các phương thức khác như addCategory(), updateCategory(), deleteCategory() nếu cần
}
