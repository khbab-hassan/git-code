package conn;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class viewemp implements Initializable {

    @FXML
    private Button out;
    @FXML
    private TableView<employee> table;
    @FXML
    private TableColumn<employee, Integer> id;
    @FXML
    private TableColumn<employee, String> name;
    @FXML
    private TableColumn<employee, String> email;
    @FXML
    private TableColumn<employee, Float> gpa;
    @FXML
    private TableColumn<employee, Float> rate;

    @FXML
    public TextField id2;
    @FXML
    public TextField name2;
    @FXML
    public TextField email2;
    @FXML
    public TextField gpa2;
    @FXML
    public TextField rate2;

    public TextField ftext;

    public ObservableList<employee> info = FXCollections.observableArrayList();
    ResultSet re;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String sql = "SELECT * FROM `employee` WHERE 1";
            Connection con = empDB.getConnection();
            employee emp = new employee();

            PreparedStatement pr = con.prepareStatement(sql);
            re = pr.executeQuery();
            while (re.next()) {
                info.add(new employee(re.getInt(1), re.getString(2), re.getString(3), re.getFloat(4), re.getFloat(5)));
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            gpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            table.setItems(info);

            table.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        employee emp = (employee) table.getSelectionModel().getSelectedItem();
                        String sql = "SELECT * FROM `employee` WHERE id = ?";
                        Connection con = empDB.getConnection();
                        PreparedStatement pr = con.prepareStatement(sql);
                        pr.setInt(1,emp.getId());
                        ResultSet re = pr.executeQuery();

                        if (re.next()) {

                            id2.setText(String.valueOf(re.getInt(1)));
                            name2.setText(re.getString(2));
                            email2.setText(re.getString(3));
                            gpa2.setText(String.valueOf(re.getFloat(4)));
                            rate2.setText(String.valueOf(re.getFloat(5)));

                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("ID Not Found");
                            alert.show();
                        }
                    } catch (SQLException ex) {
                        System.err.print(ex);
                    }
                }
            });

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insertdata(ActionEvent ev) throws SQLException {
        if (Empty()) {

        } else if (validID() | validName() | validGamil() | validFloat()) {

            employee emp = new employee();

            String idd = id2.getText();
            String namee = name2.getText();
            String emaill = email2.getText();
            String gpaa = gpa2.getText();
            String ratee = rate2.getText();

            int id = Integer.parseInt(idd);
            float gpa = Float.parseFloat(gpaa);
            float rate = Float.parseFloat(ratee);

//            Connection con = empDB.getConnection();
//            PreparedStatement pr = con.prepareStatement("SELECT * FROM `employee`");
//            ResultSet re = pr.executeQuery();
//
//            if (re.isBeforeFirst()) {
//                Alert al = new Alert(Alert.AlertType.INFORMATION);
//                al.setContentText("ID exdts");
//                al.showAndWait();
//            } else {
            emp.setId(id);
            emp.setName(namee);
            emp.setEmail(emaill);
            emp.setGpa(gpa);
            emp.setRate(rate);

            int status = empDB.insert(emp);

            if (status > 0) {

            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Field Not Inserted");
                al.showAndWait();
            }
        }
        refresh();

    }

//    Get Student By ID Number 
    public void empById(ActionEvent ev) throws SQLException {
        try {
            if (Empty() | validID()) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Insert Number Value");
                al.show();

                int ndF = Integer.parseInt(ftext.getText());
                employee emp = empDB.getemp(ndF);

                id2.setText(String.valueOf(emp.getId()));
                name2.setText(emp.getName());
                email2.setText(emp.getEmail());
                gpa2.setText(String.valueOf(emp.getGpa()));
                rate2.setText(String.valueOf(emp.getRate()));
            }

        } catch (SQLException ex) {
            System.out.println("from id" + ex.getMessage());
        }

    }

    //// Update All Filed
    public void updatedata(ActionEvent ev) throws SQLException {
        if (Empty()) {

        } else if (validID() | validName() | validGamil() | validFloat()) {
            employee emp = new employee();
            String idd = id2.getText();
            String namee = name2.getText();
            String emaill = email2.getText();
            String gpaa = gpa2.getText();
            String ratee = rate2.getText();

            int id = Integer.parseInt(idd);
            float gpa = Float.parseFloat(gpaa);
            float rate = Float.parseFloat(ratee);

            Connection con = empDB.getConnection();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM `employee`");
            ResultSet re = pr.executeQuery();

            if (re.isBeforeFirst()) {
                emp.setId(id);
                emp.setName(namee);
                emp.setEmail(emaill);
                emp.setGpa(gpa);
                emp.setRate(rate);
            }
            int status = empDB.update(emp);
            if (status > 0) {

            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record dos'nt updated");
                al.showAndWait();
            }
            refresh();
        }
    }
////   Delete All Filed

    public void deletedata(ActionEvent ev) throws SQLException {
        int status = 0;
        try {
            String idd = id2.getText();
            int id = Integer.parseInt(idd);
            status = empDB.delete(id);

            if (status > 0) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record deleted");
                al.showAndWait();
            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Record Not Found");
                al.showAndWait();
            }
            refresh();

        } catch (Exception e) {
            System.out.println("delelelelelelelelele " + e.getMessage());
        }
    }

    public void insertPage() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("file.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("#####################" + ex.getMessage());
        }

    }

    /**
     * ************** Validation Filed ************
     */
    //    Validation Number 
    public boolean validID() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(id2.getText());
        if (m.find() && m.group().equals(id2.getText())) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Enter Vaild Number ID");
            al.showAndWait();
            return false;
        }
    }

    public boolean validName() {
        Pattern p = Pattern.compile("[aA-zZ]+");
        Matcher m = p.matcher(name2.getText());
        if (m.find() && m.group().equals(name2.getText())) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Enter Vaild String Name");
            al.showAndWait();
            return false;
        }
    }

    public boolean validGamil() {
        Pattern p = Pattern.compile("[a-zA-Z0-9.*@_]+");
        Matcher m = p.matcher(email2.getText());
        if (m.find() && m.group().equals(email2.getText())) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Enter Vaild Gmail Name");
            al.showAndWait();
            return false;
        }
    }

    public boolean validFloat() {
        Pattern p = Pattern.compile("[0-9.]+");
        Matcher m = p.matcher(gpa2.getText());
        if (m.find() && m.group().equals(gpa2.getText())) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Enter Vaild Number GPA Or Rate");
            al.showAndWait();
            return false;
        }
    }

    public boolean Empty() {
        if (id2.getText().isEmpty() | name2.getText().isEmpty() | email2.getText().isEmpty() | gpa2.getText().isEmpty() | rate2.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Fill Empty Filed");
            al.showAndWait();
            return true;
        } else {
            return false;
        }
    }
//    Refresh Table Auto

    public void refresh() {
        info.clear();

        try {
            String sql = "SELECT * FROM `employee`";
            Connection con = empDB.getConnection();

            PreparedStatement pr = con.prepareStatement(sql);
            re = pr.executeQuery();
            while (re.next()) {
                info.add(new employee(re.getInt("id"), re.getString("name"), re.getString("email"), re.getFloat("gpa"), re.getFloat("rate")));
                table.setItems(info);
            }
            re.close();
            con.close();

        } catch (Exception e) {
        }
    }

}
