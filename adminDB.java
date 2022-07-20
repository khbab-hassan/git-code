package conn;

import static conn.adminDB.con;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class adminDB {

    static Connection con = null;

    public static Connection getConnection() throws SQLException {

        try {
            String url = "jdbc:mysql://localhost/db";
            con = DriverManager.getConnection(url, "khbab","khbab");
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        return con;
    }

//    public static List<admin> getadmin() {
//        List<admin> list = new ArrayList<>();
//        admin adm = new admin();
//        try {
//            String sql = "SELECT * FROM `username`";
//            con = adminDB.getConnection();
//            PreparedStatement pr = con.prepareStatement(sql);
//            ResultSet re = pr.executeQuery();
//
//            while (re.next()) {
//                adm.setPassword(re.getString(1));
//                adm.setUsername(re.getString(2));
//
//                list.add(adm);
//            }
//            con.close();
//        } catch (Exception e) {
//        }
//
//        return list;
//    }

}
