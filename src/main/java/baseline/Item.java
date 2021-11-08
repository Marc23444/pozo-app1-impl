/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Marcelino Pozo
 */
/*
    PSUEDOCODE
        -The objects created in this class will be the basis of the application
        -This class will contain fields for:
            -A description, which will be a string.
                -Max of 256 chars but not less than 1
            -A date of type DatePicker, a javaFX type
                -Optional input
                -Displayed as YYYY-MM-DD
            -The items status, will be a BooleanProperty, false by default.
        -This class will contain getter functions for:
            -The description
            -The date
            -The state
        -There are no setter functions because sonarLint told me they were never used.
        -Finally this class will have 1 parameterized constructor and 1 un-parameterized constructor.
            -The parameterized constructor will create an item with a date, state and description.
            -The un-parameterized constructor will create a null item
                -However if no description is entered nothing will be added to the list

 */
package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.DatePicker;

public class Item {

    //Fields
    private final String description;
    private final DatePicker date;
    private final BooleanProperty state;


    //Un-parameterized constructor
    public Item()
    {
        this.description = null;
        this.date = null;
        this.state = null;
    }

    public Item(String description, boolean state)
    {
        this.description = description;
        this.state = new SimpleBooleanProperty(state);
        this.date = null;
    }

    //Used when loading data from a file
    public Item(String description, DatePicker date, boolean state)
    {
        this.description = description;
        this.state = new SimpleBooleanProperty(state);
        this.date = date;
    }


    //Getters
    public DatePicker getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public BooleanProperty isState() { return state; }


}
