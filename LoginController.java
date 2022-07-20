package conn;

import static conn.adminDB.con;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pass;
    @FXML
    private TextField id;
    @FXML
    private Button checkDB, login;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        try {
            if (!adminDB.getConnection().isClosed()) {
                Alert al = new Alert(AlertType.INFORMATION);
                al.setContentText("Sccuessfuly  connected");
                al.show();
            } else {
                Alert al = new Alert(AlertType.ERROR);
                al.setContentText("filed connected");
                al.show();
            }
        } catch (Exception e) {
            System.out.println("Massege" + e.getMessage());
        }
    }

    @FXML
    public void check(ActionEvent event) throws IOException, SQLException {
        try {
            admin adm = new admin();

            String idd = id.getText();

            String sql = "SELECT * FROM username WHERE `id` = ? and `username` = ? and `password` = ?";
            con = adminDB.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);

            pr.setString(1, id.getText());
            pr.setString(2, username.getText());
            pr.setString(3, pass.getText());

            ResultSet re = pr.executeQuery();

            int cont = 0;
            while (re.next()) {
                adm.setId(re.getString("id"));
                adm.setUsername(re.getString("username"));
                adm.setPassword(re.getString("password"));

                if (id.getText().equals("1")) {
                    Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("IUD.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
                cont++;
            }
            if (username.getText().isEmpty() || pass.getText().isEmpty() || id.getText().isEmpty()) {
                Alert al = new Alert(AlertType.WARNING);
                al.setTitle("Nots");
                al.setHeaderText("Warrong");
                al.setContentText("Filed is Empty");
                al.show();
            }
            if (cont == 1) {

            } else {
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText("Warrong");
                al.setContentText("Username Or Password Incorrect");
                al.show();
            }
        } catch (SQLException sQLException) {
            System.out.println("error" + sQLException.getMessage());
        }

     
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


//
//        Map<String, String> map = new HashMap<>();
//
//        for (admin a : list) {
//            map.put(a.getPassword(), a.getUsername());
//        }
////        check empty Record
//       
//
//        } else if (map.containsKey(username.getText().trim())) {
//
//            String value = map.get(username.getText().trim());
//
//            try {
//                if (value.equals(pass.getText().trim())) {
//                    Parent root = FXMLLoader.load(getClass().getResource("conn_1.fxml"));
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.show();
//
//                } else {
//                    Alert al = new Alert(AlertType.ERROR);
//                    al.setTitle("Nots");
//                    al.setHeaderText("Warrong");
//                    al.setContentText("Record Falied PassWord Not Valied !");
//
//                    al.showAndWait();
//                }
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        } else {
//            Alert al = new Alert(AlertType.ERROR);
//            al.setTitle("Nots");
//            al.setHeaderText("Warrong");
//            al.setContentText("Record Falied UserName Not Valied");
//
//            al.showAndWait();
//        }
//    }
    }

}
