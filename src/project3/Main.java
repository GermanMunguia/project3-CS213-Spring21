package project3;

/**
 The main class loads and runs the FXML file and Controller class code.
 The JavaFX library is loaded and is used to load and run the GUI application associated with the View.FXML file.
 @author German Munguia, Sukhjit Singh
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     Loads the View.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 751 pixels wide and 373 pixels long.
     @param primaryStage the stage associated with the View.fxml file
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Company Database Application");
        primaryStage.setScene(new Scene(root, 751, 373));
        primaryStage.show();
    }

    /**
     Calls the launch() method passing in the args string array as a parameter.
     @param args Unused parameter
     */
    public static void main(String[] args) {
        launch(args);
    }
}
