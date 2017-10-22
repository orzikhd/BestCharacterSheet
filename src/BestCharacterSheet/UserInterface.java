package BestCharacterSheet;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
        VBox tabBox = new VBox();

        Label name = new Label("Name:");
        name.setId("testName");

        Label adventurerClass = new Label("Class:");
        adventurerClass.setId("testClass");

        Label classDescription = new Label("Class Description:");
        classDescription.setId("testClassDesc");

        tabBox.getChildren().add(name);
        tabBox.getChildren().add(adventurerClass);
        tabBox.getChildren().add(classDescription);
        tab.setContent(tabBox);
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
