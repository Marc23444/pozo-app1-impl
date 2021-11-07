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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;


public class ListController implements Initializable{

    ObservableList<Item> items = FXCollections.observableArrayList();
    ObservableList<Item> completedItems = FXCollections.observableArrayList();
    ObservableList<Item> incompleteItems = FXCollections.observableArrayList();



    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,Boolean> col1;

    @FXML
    private TableColumn<Item,String>  col2;

    @FXML
    private TableColumn<Item, String>  col3;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private  MenuItem save;

    //This method runs first and setups the data in the table
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Setups the data types for each table
        col1.setCellValueFactory(param -> param.getValue().isState());
        col2.setCellValueFactory(new PropertyValueFactory<Item,String>("Description"));
        col3.setCellValueFactory(new PropertyValueFactory<Item, String>("Date"));

        //Allows table to be editable
        table.setEditable(true);

        //Sets the CellFactory types for column 1 and 2.
        // Column 3 does not need one because the DatePicker is stored inside Item.
        col1.setCellFactory(CheckBoxTableCell.forTableColumn(col1));
        col2.setCellFactory(TextFieldTableCell.forTableColumn());

        //Sets the items of the table
        table.setItems(items);
    }

    //Creates a new item and adds it to the list
    public void createItem(String description, LocalDate date, boolean state)
    {
        //Creates a new DatePicker and sets it to the local date chosen by the user
        DatePicker temp = new DatePicker();


        String format = "yyyy-MM-dd";

        //Overrides both the toString and fromString methods of DatePicker
        temp.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            //When the date is displayed it will be in format YYYY-MM-DD
            @Override
            public String toString(LocalDate date)
            {
                //If the date of the datePicker is not null
                if (date != null)
                {
                    //Returns the date in the predefined format.
                    return formatter.format(date);
                } else
                {
                    return "";
                }
            }
            //When a new date is taken in it will be in format YYYY-MM-DD
            @Override
            public LocalDate fromString(String string)
            {
                //String exists and is not empty
                if (string != null && !string.isEmpty())
                {
                    //Returns the string using the formatter
                    return LocalDate.parse(string, formatter);
                } else
                {
                    return null;
                }
            }
        });

        temp.setValue(date);
        //Adds the new item to the list
        items.add(new Item(description,temp,state));
    }

    //When the add button is pressed a new Item object will be created with descriptionTextField and dateTextField passed through.
    //This item will then be added to the table
    @FXML
    public void addItem(ActionEvent event)
    {
        if(descriptionTextField.getText().length() >= 1 && descriptionTextField.getText().length() <=256)
        {
            createItem(descriptionTextField.getText(),datePicker.getValue(),false);
        }
    }

    //Will clear the list of items
    @FXML
    public void clearList(ActionEvent event) {

        items.clear();
        incompleteItems.clear();
        completedItems.clear();

    }

    @FXML
    public void showCompleted(ActionEvent event)
    {
        table.setItems(completedItems);
    }

    @FXML
    public void showIncomplete(ActionEvent event)
    {
        table.setItems(incompleteItems);
    }

    @FXML
    public void showAll(ActionEvent event)
    {
        table.setItems(items);
    }


    //Attached to the context menu
    //Fires On Action of the delete button
    //Deletes the selected row
    @FXML
    public void deleteItem(ActionEvent event)
    {
        if(items.size() > 0)
        {
            ObservableList<Item> rows;
            items = table.getItems();

            rows = table.getSelectionModel().getSelectedItems();

            for (Item item: rows)
            {
                items.remove(item);
            }

        }
    }

    @FXML
    public void saveList(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Opening a dialog box
                fileChooser.showSaveDialog(stage);
            }
        });

        try(FileWriter myWriter = new FileWriter("output.txt"))
        {
            for (Item temp : items) {
                myWriter.write(temp.isState().get()+" "+temp.getDescription()+" "+temp.getDate().getValue().toString()+"\n");
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void loadList(ActionEvent event)
    {
        File input;
        input = new File("output.txt");
        items.clear();

        //Try catch block for the file
        try
        {
            Scanner in = new Scanner(input);
            String description = "";
            LocalDate date;
            boolean state;

            while(in.hasNextLine())
            {

                state = Boolean.parseBoolean(in.next());
                description = in.next();
                date = LocalDate.parse(in.next());

                createItem(description,date,state);
            }

            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}