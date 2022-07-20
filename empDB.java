package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class empDB {

    static Connection con = null;

    public static Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/db";
            con = DriverManager.getConnection(url, "khbab", "khbab");
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        return con;
    }
////Insert Record

    public static int insert(employee emp) throws SQLException {
        int status = 0;

        try {
            Connection con = empDB.getConnection();
            PreparedStatement pr = con.prepareStatement("INSERT INTO `employee`(`id`, `name`, `email`, `gpa`, `rate`)VALUES (?,?,?,?,?)");

            pr.setInt(1, emp.getId());
            pr.setString(2, emp.getName());
            pr.setString(3, emp.getEmail());
            pr.setFloat(4, emp.getGpa());
            pr.setFloat(5, emp.getRate());

            status = pr.executeUpdate();

            con.close();
        } catch (Exception ex) {
            System.out.println("Insert Input Data " + ex.getMessage());
        }
        return status;
    }
    //Update Record

    public static int update(employee emp) throws SQLException {
        int status = 0;
        try {
            con = empDB.getConnection();
            String sql = "UPDATE `employee` SET `id`=?,`name` = ?,`email`=?,`gpa`=?,`rate`=?";
            PreparedStatement pr = con.prepareStatement(sql);

            pr.setInt(1, emp.getId());
            pr.setString(2, emp.getName());
            pr.setString(3, emp.getEmail());
            pr.setFloat(4, emp.getGpa());
            pr.setFloat(5, emp.getRate());

            status = pr.executeUpdate();
            con.close();
        } catch (Exception ex) {
            System.out.println("From Update " + ex.getMessage());
        }

        return status;
    }
////Delete All Record

    public static int delete(int id) throws SQLException {
        int status = 0;
        String sql = "DELETE FROM `employee` WHERE `id` = ?";
        con = empDB.getConnection();
        PreparedStatement pr = con.prepareStatement(sql);
        pr.setInt(1, id);
        status = pr.executeUpdate();

        return status;
    }
////Search About Record By Id

    public static employee getemp(int id) throws SQLException {
        employee emp = new employee();
        try {
            String sql = "SELECT * FROM `employee` WHERE id = ?";
            con = empDB.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);

            ResultSet re = pr.executeQuery();

            if (re.next()) {
                emp.setId(re.getInt(1));
                emp.setName(re.getString(2));
                emp.setEmail(re.getString(3));
                emp.setGpa(re.getFloat(4));
                emp.setRate(re.getFloat(5));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Id Not Found");
                alert.show();
            }
        } catch (SQLException ex) {
            System.out.println("From Find By Id " + ex.getMessage());
        }
        con.close();
        return emp;
    }
}
