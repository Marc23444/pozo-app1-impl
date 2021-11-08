/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Marcelino Pozo
 */

package baseline;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListControllerTest {


    @Test
    void clearList() {
        ListController testController = new ListController();
        ObservableList<Item> testItems = FXCollections.observableArrayList();


        testItems.clear();
        testController.clearList();

        Assertions.assertEquals(testController.items,testItems);
    }

    @Test
    void showComplete() {
        ListController testController = new ListController();

        ObservableList<Item> testItems = FXCollections.observableArrayList();

        testItems.add(new Item("testDescription1",false));
        testItems.add(new Item("testDescription2",true));
        testItems.add(new Item("testDescription3",true));

        testController.items = testItems;
        testItems.remove(0);

        testController.showComplete();

        Assertions.assertEquals(testController.displayedItems,testItems);


    }

    @Test
    void showIncomplete() {

        ListController testController = new ListController();

        ObservableList<Item> testItems = FXCollections.observableArrayList();

        testItems.add(new Item("testDescription1",false));
        testItems.add(new Item("testDescription2",true));
        testItems.add(new Item("testDescription3",true));

        testController.items = testItems;
        testItems.remove(1);
       testItems.remove(1);

        testController.showIncomplete();

        Assertions.assertEquals(testController.displayedItems,testItems);


    }

    @Test
    void showAll() {
        ListController testController = new ListController();

        ObservableList<Item> testItems = FXCollections.observableArrayList();

        testItems.add(new Item("testDescription1",false));
        testItems.add(new Item("testDescription2",true));
        testItems.add(new Item("testDescription3",true));

        testController.items = testItems;

        testController.showAll();

        Assertions.assertEquals(testController.items,testItems);

        testItems.clear();

        Assertions.assertEquals(testController.displayedItems,testItems);



    }

}