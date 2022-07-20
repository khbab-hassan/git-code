package conn;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class FileController {

    @FXML
    private TextArea text;
    @FXML
    private TextField path;
    @FXML
    private Button btn, file;

    public FileInputStream ins;
    public File fil;
    public Desktop des;

    @FXML
    private void fileinsert(ActionEvent event) throws SQLException, FileNotFoundException {

        FileChooser filecho = new FileChooser();
        filecho.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.pdf"));
        File file = filecho.showOpenDialog(null);

        ins = new FileInputStream(file);

        if (ins != null) {
            file = new File("File:" + file.getAbsolutePath());
            text.setText(String.valueOf(file));
            path.setText(String.valueOf(file.getAbsolutePath()));
//
        }
    }

    @FXML
    private void filesend(ActionEvent event) throws SQLException, FileNotFoundException {
        try {
            String url = "jdbc:mysql://localhost/db";
            Connection con = DriverManager.getConnection(url, "khbab", "khbab");

            folder fo = new folder();
            PreparedStatement pr = con.prepareStatement("INSERT INTO `file`(`file`) VALUES (?)");

            pr.setBinaryStream(1, ins);
            pr.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.err.print(ex);
        }

    }

    @FXML
    private void getfile(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        try {
            ResultSet re = null;
            String url = "jdbc:mysql://localhost/db";
            Connection con = DriverManager.getConnection(url, "khbab", "khbab");

            folder fo = new folder();
            PreparedStatement pr = con.prepareStatement("SELECT `file` FROM `file`");
                re = pr.executeQuery();
            while (re.next()) {
     
                  
                text.setText(re.getString(1));
            }
            re.close();
            con.close();
        } catch (SQLException ex) {
            System.err.print(ex);
        }

    }
}
