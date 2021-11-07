package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ListControllerTest {

    @Test
    void setItems()
    {






    }


    @Test
    void saveListTest()
    {
        ListController testController = new ListController();
        ObservableList<Item> testItems = FXCollections.observableArrayList();
        LocalDate testDate;
        testDate = LocalDate.parse("2021-12-10");

        testController.createItem("entry1",testDate,false);

        testItems = testController.items;

        te



    }

    @Test
    void loadListTest()
    {

    }
}