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

        Label nameStatic = new Label("Name:");
        Label nameDynamic = new Label("NO NAME LOADED");
        nameDynamic.setId("testName");

        Label adventurerClassStatic = new Label("Class:");
        Label adventurerClassDynamic = new Label("NO CLASS LOADED");
        adventurerClassDynamic.setId("testClass");

        Label classDieStatic = new Label("Class Die:");
        Label classDieDynamic = new Label("NO DIE LOADED:");
        classDieDynamic.setId("testDie");

        Label maxHealthStatic = new Label("Max Health:");
        Label maxHealthDynamic = new Label("NO MAX HEALTH LOADED");
        maxHealthDynamic.setId("testMaxHealth");

        Label currHealthStatic = new Label("Curr Health:");
        Label currHealthDynamic = new Label("NO CURR HEALTH LOADED");
        currHealthDynamic.setId("testCurrHealth");

        Button dmgButton = new Button("Take 1 point damage!");
        dmgButton.setId("testDmgBtn");

        tabGrid.add(nameStatic,0,0);
        tabGrid.add(nameDynamic,1,0);

        tabGrid.add(adventurerClassStatic,0,1);
        tabGrid.add(adventurerClassDynamic,1,1);

        tabGrid.add(classDieStatic,0,2);
        tabGrid.add(classDieDynamic,1,2);

        tabGrid.add(maxHealthStatic, 0, 3);
        tabGrid.add(maxHealthDynamic, 1, 3);

        tabGrid.add(currHealthStatic, 0, 4);
        tabGrid.add(currHealthDynamic, 1, 4);

        tabGrid.add(dmgButton, 0, 5);

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
