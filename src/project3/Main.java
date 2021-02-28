package project3;

/**

 @author German Munguia, Sukhjit Singh
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //hi

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Company Database Application");
        primaryStage.setScene(new Scene(root, 751, 373));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
