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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;
import java.util.List;

public class ListController {


    private ObservableList<Item> items;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,Boolean> col1;

    @FXML
    private TableColumn<Item,String>  col2;

    @FXML
    private TableColumn<Item,String>  col3;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    //When the add button is pressed a new Item object will be created with descriptionTextField and dateTextField passed through.
    //This item will then be added to the table





    public void addItem(ActionEvent event)
    {
        try{


            items.add(new Item(descriptionTextField.getText(),dateTextField.getText()));
            table.getItems().setAll(items);


        }
        catch(Exception e)
        {

        }
    }




}