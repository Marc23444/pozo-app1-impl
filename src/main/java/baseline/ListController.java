/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Marcelino Pozo
 */
/*
    PSUEDOCODE
        -This class will be used to control the main fxml file: todo-list-gui.
        -This class will include fields for
            -The description text field
            -The date text field
            -The table
        -Methods in this class will include:
            -addItem
 */
package baseline;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ListController implements Initializable{

    ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,String> col1;

    @FXML
    private TableColumn<Item,String>  col2;

    @FXML
    private TableColumn<Item,String>  col3;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField dateTextField;

    public void initialize(URL url, ResourceBundle rb)
    {
        //Setup the columns in the table
        col1.setCellValueFactory(new PropertyValueFactory<Item,String>("Status"));
        col2.setCellValueFactory(new PropertyValueFactory<Item,String>("Description"));
        col3.setCellValueFactory(new PropertyValueFactory<Item,String>("Date"));
        table.setItems(getItems());
    }

    public ObservableList<Item> getItems()
    {



        return items;
    }

    public void setItems(String description, String date)
    {
        items.add(new Item(description,date));
    }

    //When the add button is pressed a new Item object will be created with descriptionTextField and dateTextField passed through.
    //This item will then be added to the table
    @FXML
    public void addItem(ActionEvent event)
    {
        try{

            setItems(descriptionTextField.getText(),dateTextField.getText());
        }
        catch(Exception e)
        {

        }
    }

}