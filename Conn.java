package conn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Conn extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Main Page");
        stage.setScene(scene);
        stage.show();
    }

//    public static void logout(ActionEvent event,String oldfxml,String fxmlfile) throws IOException {
//        Parent root = FXMLLoader.load(Conn.class.getResource(fxmlfile));
//      
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        launch(args);
    }

}
