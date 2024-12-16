package dao;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {



    // Constructor chứa tham số Connection
    public ProductDAO() {

    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, photo, price, discount, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getPhoto());
            statement.setDouble(4, product.getPrice());
            statement.setDouble(5, product.getDiscount());
            statement.setInt(6, product.getCategory().getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPhoto(resultSet.getString("photo"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDiscount(resultSet.getDouble("discount"));

                int categoryId = resultSet.getInt("category_id");
                Category category = getCategoryById(categoryId); // Helper method to fetch Category
                product.setCategory(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()){

                PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery() ;

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPhoto(resultSet.getString("photo"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDiscount(resultSet.getDouble("discount"));

                int categoryId = resultSet.getInt("category_id");
                Category category = getCategoryById(categoryId);
                product.setCategory(category);

                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;}

        // Cập nhật thông tin sản phẩm
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, photo = ?, price = ?, discount = ?, category_id = ? WHERE id = ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getPhoto());
            statement.setDouble(4, product.getPrice());
            statement.setDouble(5, product.getDiscount());
            statement.setInt(6, product.getCategory().getId());
            statement.setInt(7, product.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa sản phẩm
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method để lấy thông tin Category theo ID
    private Category getCategoryById(int categoryId) {
        Category category = null;
        String sql = "SELECT * FROM Category WHERE id = ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setTitle(resultSet.getString("title"));
                category.setDescription(resultSet.getString("description"));
                // Set other Category fields if necessary
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    // Lấy sản phẩm theo Category ID
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE category_id = ?";

        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setPhoto(rs.getString("photo"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchProductByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE name like ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPhoto(resultSet.getString("photo"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDiscount(resultSet.getDouble("discount"));

                int categoryId = resultSet.getInt("category_id");
                Category category = getCategoryById(categoryId);
                product.setCategory(category);

                list.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lọc sản phẩm theo giá
    public List<Product> filteringProductByPrice(double minPrice, double maxPrice) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPhoto(resultSet.getString("photo"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDiscount(resultSet.getDouble("discount"));

                int categoryId = resultSet.getInt("category_id");
                Category category = getCategoryById(categoryId);
                product.setCategory(category);

                list.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
