package BestCharacterSheet;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;

/**
 * The GUI for the character sheet.
 * Contains all the static information.
 * Event handlers are set in the controller.
 */
public class UserInterface {

    /**
     * The main scene for the GUI
     */
    private Scene scene;

    /**
     * Builds the initial user interface
     */
    public UserInterface() {

        StackPane root = new StackPane();

        Button btn = new Button();
        btn.setId("mainbtn");
        btn.setText("Say 'Hello World'");

        // The tabs that make up the main menu
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("new tab1");
        tabPane.getTabs().add(tab1);
        tab1.setContent(btn);

        Tab tab2 = testTab();
        tabPane.getTabs().add(tab2);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.getChildren().add(tabPane);

        scene = new Scene(root, 300, 250);
    }

    /**
     * @return constructed static elements of testTab on init
     */
    private Tab testTab() {
        Tab tab = new Tab("Test Tab");
        GridPane tabGrid = new GridPane();

        Label name1 = new Label("Name:");
        Label name2 = new Label("NO NAME LOADED");
        name2.setId("testName");

        Label adventurerClass1 = new Label("Class:");
        Label adventurerClass2 = new Label("NO CLASS LOADED");
        adventurerClass2.setId("testClass");

        Label classDie1 = new Label("Class Die:");
        Label classDie2 = new Label("NO DIE LOADED:");
        classDie2.setId("testDie");

        tabGrid.add(name1,0,0);
        tabGrid.add(name2,1,0);

        tabGrid.add(adventurerClass1,0,1);
        tabGrid.add(adventurerClass2,1,1);

        tabGrid.add(classDie1,0,2);
        tabGrid.add(classDie2,1,2);

        tab.setContent(tabGrid);
        tab.setId("testTab");
        return tab;
    }

    /**
     * @return constructed static elements of inventoryTab on init
     */
    private Tab inventoryTab() {
        //TODO
        return new Tab("Inventory");
    }

    /**
     * @return constructed static elements of spellsTab on init
     */
    private Tab spellsTab() {
        //TODO
        return new Tab("Spells");
    }

    /**
     * @return constructed static elements of notesTab on init
     */
    private Tab notesTab() {
        //TODO
        return new Tab("");
    }

    /**
     * @return the main scene object
     * which can be used to look up elements via ID
     */
    public Scene getScene() {
        return scene;
    }

}
