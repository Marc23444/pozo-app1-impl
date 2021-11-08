/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Marcelino Pozo
 */
/*
    PSUEDOCODE
        -This class will be used to control the main fxml file: todo-list-gui
        -This class will include fields for
            -The description text field
            -The date text field
            -The table
                -All 3 columns in the table
        -Methods in this class will include:
            -initialize, which overrides a method in Initializable
            -addItem
            -clearList
            -showComplete
            -showIncomplete
            -showAll
            -deleteItem
            -saveList
            -loadList
            -helpButton
       -All of these methods excluding initialize and createItem are used by FXML
       -FXML uses these functions to provide functionality to the layout built in sceneBuilder.
       -A more detailed explanation of these methods can be found in the comments associated with them.
 */
package baseline;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class ListController implements Initializable{

    //Full List of Items
    ObservableList<Item> items = FXCollections.observableArrayList();
    //List used to display the completed or incomplete items
    ObservableList<Item> displayedItems = FXCollections.observableArrayList();

    //The main table and its 3 columns
    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,Boolean> col1;

    @FXML
    private TableColumn<Item,String>  col2;

    @FXML
    private TableColumn<Item, String>  col3;

    //The descriptionTextField which takes in user input as a String
    @FXML
    private TextField descriptionTextField;

    //The datePicker implements a gui that allows the user to easily select a date.
    @FXML
    private DatePicker datePicker;


    //This method runs first and setups the data in the table
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        //Setups the data types for each table
        col1.setCellValueFactory(param -> param.getValue().isState());
        col2.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Date"));

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
    private void createItem(String description, LocalDate date, boolean state)
    {
        //Creates a new DatePicker and sets it to the local date chosen by the user

        DatePicker temp = new DatePicker();


        String format = "yyyy-MM-dd";

        //Overrides both the toString and fromString methods of DatePicker
        temp.setConverter(new StringConverter<>() {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            //When the date is displayed it will be in format YYYY-MM-DD
            @Override
            public String toString(LocalDate date)
            {
                //If the date of the datePicker is not null
                if (date != null)
                {
                    //Returns the date in the predefined format.
                    return formatter.format(date);
                }
                else
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
                }
                else
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
    @FXML
    public void addItem()
    {
        //Calls createItem()
        if(descriptionTextField.getText().length() >= 1 && descriptionTextField.getText().length() <=256)
        {
            createItem(descriptionTextField.getText(),datePicker.getValue(),false);
        }
    }

    //Deletes the selected row
    @FXML
    public void deleteItem()
    {
        //Only works if items has elements
        if(!items.isEmpty())
        {
            ObservableList<Item> rows;
            items = table.getItems();

            rows = table.getSelectionModel().getSelectedItems();

            //This if else block prevents an out-of-bounds exception from occurring
            if(items.size() > 1)
            {
                for (Item item: rows)
                {
                    items.remove(item);
                }
            }
            else
            {
                items.clear();
            }


        }
    }

    //Clears the list items
    @FXML
    public void clearList() {

        items.clear();
    }


    //Shows items which return true when isState() is called
    @FXML
    public void showComplete()
    {
        //Clears the list to prevent duplicates
        displayedItems.clear();
        //Uses an advanced for loop to traverse through the elements in items
        for(Item item : items)
        {
            if(item.isState().get())
            {
                displayedItems.add(item);
            }
        }

        if(table != null)
        {
            table.setItems(displayedItems);

        }

    }

    //Shows items which return false when isState() is called
    @FXML
    public void showIncomplete()
    {
        //Clears the list to prevent duplicates
        displayedItems.clear();
        //Uses an advanced for loop to traverse through the elements in items
        for(Item item : items)
        {
            if(!item.isState().get())
            {
                displayedItems.add(item);
            }
        }

        if(table != null)
        {
            table.setItems(displayedItems);

        }

    }

    //Shows all items in the list
    @FXML
    public void showAll()
    {
        displayedItems.clear();

        if(table != null)
        {
            table.setItems(items);

        }

    }


    @FXML
    public void saveList()
    {
        //Creates a new stage where the user can select the file they wish to save to.
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save TO-DO List");

        File input = fileChooser.showOpenDialog(stage);

        //A fileWriter is used inside a try catch block to write to a file
        try(FileWriter myWriter = new FileWriter(input))
        {
            for (Item temp : items)
            {
                //The file is written to line by line
                // This is done because this is the simplest format to load back into the application
                myWriter.write(temp.isState().get()+"\n"+temp.getDescription()+"\n"+temp.getDate().getValue().toString()+"\n");
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void loadList()
    {
        //Create a fileChooser to select the file to load from
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load TO-DO List");


        File input = fileChooser.showOpenDialog(stage);

        //Clear out the current items in the list
        items.clear();

        //Try catch block for the file
        try
        {
            //Scanner is used to read in input from the file
            Scanner in = new Scanner(input);
            String description;
            LocalDate date;
            boolean state;

            //Every set of 3 represents an item object
            while(in.hasNextLine())
            {
                //The data is read in this specific order because this is the order it is saved in.
                state = Boolean.parseBoolean(in.nextLine());
                description = in.nextLine();
                date = LocalDate.parse(in.nextLine());

                createItem(description,date,state);
            }
            //Closing the scanner...
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Creates a new stage and populates it with the text from the user_guide.txt file.
    @FXML
    public void helpButton()
    {
        //Creating and naming the stage
        Stage instructions = new Stage();
        instructions.setTitle("User Guide");

        //Grabbing the User_Guide file, I opted to use a file because putting a 15 line text block would look awful.
        File userGuide = new File("User_Guide.txt");
        try (Scanner input = new Scanner(userGuide)) {

            //Creating a dialogBox and adding all the text
            VBox dialogVbox = new VBox(5);
            while (input.hasNextLine()) {
                dialogVbox.getChildren().add(new Text(input.nextLine()));
            }
            //Creating and displaying the scene
            Scene dialogScene = new Scene(dialogVbox, 850, 320);
            instructions.setScene(dialogScene);
            instructions.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}