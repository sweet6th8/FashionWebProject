package dao;
import model.User;
import java.sql.*;
public class UserDAO {
    public UserDAO() {
    }
    public User getUserRs (ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setFullName(rs.getString("fullName"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getString("phone"));
        user.setGender(rs.getBoolean("gender"));
        user.setImg(rs.getString("img"));
        return user;
    }
    public int editProfile(User user) {

        return -1;
    }
    public void saveImg (String path  , int id ) {
        String sql = "UPDATE ListUser set img = ? where user_id = ? ";
        try (Connection con = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, path);
            ps.setString(2, id+"");
           ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getUserImg (int id ) throws SQLException {
        String sql = "SELECT img FROM ListUser WHERE user_id = ?";
        try (Connection con = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id+"");
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               return rs.getString(1);
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
    public User getUser(int id) {
        String sql = "select * from ListUser where user_id=?";
        try (Connection con = DBConnectionPool.getDataSource().getConnection();){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id+"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return getUserRs(rs);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updatePassword (String email , String password) throws SQLException {
        String query = "UPDATE  listUser SET password=? WHERE email=?";
        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        }
    }
    public boolean registerUser(User user) throws SQLException {
        int gender = (user.isGender()) ? 1 : 0;
        String sql = "insert into ListUser  (username, password, email, fullName, address, phone,gender) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DBConnectionPool.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getPhone());
            ps.setInt(7, gender);
            ps.executeUpdate();
            System.out.println("thêm user vào db thành công !");
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return true;
    }
    public boolean checkEmailExist(String email) {
        String sql = "select email from ListUser where email=?";
        try (   Connection connection = DBConnectionPool.getDataSource().getConnection();) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return false;
    }
    public User getLogin(String email, String password) throws SQLException {
        Connection connection = DBConnectionPool.getDataSource().getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from ListUser where email = ? and password = ?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return getUserRs(rs);
        }
        return null;
    }
}