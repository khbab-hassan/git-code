package conn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StatusController implements Initializable {

    @FXML
    public TextField setNumber;
    @FXML
    public TextField getNumber;
    @FXML
    public TextArea setComment;
    @FXML
    public TextArea getComment;

//    Insert Data Or Record
    public void insertComment(ActionEvent ev) {
        try {

            String setNum = setNumber.getText();
            String setCo = setComment.getText();

            status st = new status();
            st.setComment(setCo);
            st.setNumber(setNum);

            int status = stutDB.insert(st);

            if (status > 0) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record Inserted");
                al.showAndWait();
            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record Dont Inserted");
                al.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("insreComment" + e.getMessage());
        }
    }

    public void getComment(ActionEvent ev) throws SQLException {
        String getn = getNumber.getText();
        status st = stutDB.getStatus(getn);
        getComment.setText(st.getComment());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
