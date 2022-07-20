package conn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IUDController implements Initializable {

    @FXML
    public TextField id;
    @FXML
    public TextField name;
    @FXML
    public TextField email;
    @FXML
    public TextField gpa;
    @FXML
    public TextField rate;
    public TextField ftext;
    @FXML
    public Button out;

//    Insert Data Or Record
    
    
    public void insertdata(ActionEvent ev) throws SQLException {
        employee emp = new employee();

        String idd = id.getText();
        String namee = name.getText();
        String emaill = email.getText();
        String gpaa = gpa.getText();
        String ratee = rate.getText();

        int id = Integer.parseInt(idd);
        int gpa = Integer.parseInt(gpaa);
        int rate = Integer.parseInt(ratee);

        emp.setId(id);
        emp.setName(namee);
        emp.setEmail(emaill);
        emp.setGpa(gpa);
        emp.setRate(rate);
        int status = empDB.insert(emp);
//
        if (status > 0) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("field Inserted");
            al.showAndWait();
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("field Not Inserted");
            al.showAndWait();
        }
    }
        @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //    Search Employee By Id
    public void empById(ActionEvent ev) throws SQLException {
        try {
            int ndF = Integer.parseInt(ftext.getText());

            employee emp = empDB.getemp(ndF);

            id.setText(String.valueOf(emp.getId()));
            name.setText(emp.getName());
//            sallary.setText(String.valueOf(emp.getSallary()));

        } catch (SQLException ex) {
            System.out.println("from id" + ex.getMessage());
        }

    }
//
////    Update Record
    public void updatedata(ActionEvent ev) throws SQLException {
    

            String idd = id.getText();
            String namee = name.getText();
//            String sallaryy = sallary.getText();

            int id = Integer.parseInt(idd);
//            int sallary = Integer.parseInt(sallaryy);
            employee emp = new employee();
            emp.setId(id);
            emp.setName(namee);
//            emp.setSallary(sallary);
//            if (idd.isEmpty()) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("id Fild is Empty");
//                al.showAndWait();
//            } else {
//            }
//            if (name.getText().isEmpty()) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("name Fild is Empty");
//                al.show();
//            } else {
//
//            }
//            if (this.sallary.getText().isEmpty()) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("sallary Fild is Empty");
//                al.showAndWait();
//            } else {
//                ;
//            }
//            if (!name.getText().equals("String")) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("Insert String Data In Name Filed");
//                al.showAndWait();
//            } else {
//                emp.setName(name.toString());
//            }
//            if (this.id.getText() != "Integer") {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("Insert Integer Data In Id Filed");
//                al.showAndWait();
//           }
//            if (this.sallary.getText() != "Integer") {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("Insert Integer Data In Sallary Filed");
//                al.showAndWait();
//            }
//            int status = empDB.update(emp);
//            if (status > 0) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("Record updated");
//                al.showAndWait();
//            } else {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("Record dos'nt updated");
//                al.showAndWait();
//            }
//
    
    }
////   Delete Record
//
    public void deletedata(ActionEvent ev) throws SQLException {
        int status = 0;
        try {
            String idd = id.getText();
            int id = Integer.parseInt(idd);

            status = empDB.delete(id);
            if (status > 0) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record deleted");
                al.showAndWait();
            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record Not deleted");
                al.showAndWait();
            }

        } catch (Exception e) {
            System.out.println("delelelelelelelelele " + e.getMessage());
        }
    }


 
}
