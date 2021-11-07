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
            -A date, also a string, will need to built and converted from integer input.
                -Optional input
                -Displayed as YYYY-MM-DD
            -The items status, will be a simple boolean, false by default.
        -This class will contain getter and setter functions for:
            -The description
            -The date
            -The state
        -Finally this class will have 2 parameterized constructors.
            -The first one will create an item with no date
            -The second one will create an item with a date

 */
package baseline;

public class Item {

    private String description;
    private String date;
    private boolean state;

    //Used when the user doesn't enter a date
    public Item(String description)
    {
        this.description = description;
    }
    //Used when the user does enter a date
    public Item(String description, String date)
    {
        this.description = description;
        this.date = date;
    }

    //Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    //Getters
    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public boolean isState() {
        return state;
    }


}
