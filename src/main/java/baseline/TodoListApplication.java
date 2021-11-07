/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Marcelino Pozo
 */
/*
    PSUEDOCODE
        -This class is primarily used to load and run the fxml file
        -The start function loads the specific fxml file and attaches it to a new scene
        -After this it sets the stages title and scene to the loaded scene
        -Finally it shows the scene

 */
package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TodoListApplication extends Application {



    @Override

    //Loads and opens the fxml file
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(TodoListApplication.class.getResource("todo-list-gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Todo List");

        stage.setScene(scene);

        stage.show();
    }

    //launch() just calls start
    public static void main(String[] args) { launch(); }
}
