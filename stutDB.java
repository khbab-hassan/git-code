package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class stutDB {
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
    //Insert Record
    public static int insert(status st) {
        int status = 0;
        try {
            String sql = "INSERT INTO `comment`(`comment`, `number`)VALUES (?,?)";
            Connection con = stutDB.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, st.getComment());
            pr.setString(2,st.getNumber());
         
            status = pr.executeUpdate();
            con.close();

        } catch (Exception ex) {
            System.out.println("inset stu" + ex.getMessage());
        }
        return status;

    }
//Update Record
       public static status getStatus(String number) throws SQLException {
        status st = new status();
        try {
            String sql = "SELECT `comment` FROM `comment` WHERE `number`=?";
            con = stutDB.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1,number);
            ResultSet re = pr.executeQuery();

            if (re.next()) {
                st.setComment(re.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("From Find By Id sty  " + ex.getMessage());
        }
        con.close();
        return st;
    }

}
