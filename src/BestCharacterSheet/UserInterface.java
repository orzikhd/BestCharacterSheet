package BestCharacterSheet;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;

/**
 * The GUI for the character sheet.
 * Contains all the static information.
 * Event handlers are set in the controller.
 */
public class UserInterface {

    private static int HEIGHT = 850;
    private static int WIDTH = 1100;

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
        btn.getStyleClass().add("mainbtn");
        btn.setText("Say 'Hello World'");

        // The tabs that make up the main menu
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("new tab1");
        tabPane.getTabs().add(tab1);
        tab1.setContent(btn);

        Tab tab2 = testTab();
        tabPane.getTabs().add(tab2);

        Tab tab3 = summaryTab();
        tabPane.getTabs().add(tab3);

        Tab tab4 = notesTab();
        tabPane.getTabs().add(tab4);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.getChildren().add(tabPane);


        scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("stylesheet.css");
    }

    /**
     * @return the main scene object
     * which can be used to look up elements via ID
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Returns a bar that is proportionally filled with default color black.
     */
    public StackPane createBar(Integer num, Integer denom) {
        return createBar(num,denom,javafx.scene.paint.Color.RED);
    }

    /**
     * Returns a bar that is proportionally filled with given color.
     */
    public StackPane createBar(Integer num, Integer denom, Color color) {
        StackPane barPane = new StackPane();
        Rectangle r = new Rectangle();
        r.setHeight(50);
        r.setWidth(200 * num/(double)denom);
        r.setFill(color);

        Rectangle border = new Rectangle();
        border.setHeight(50);
        border.setWidth(200);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(2);
        border.setFill(Color.TRANSPARENT);
        barPane.getChildren().add(r);
        barPane.getChildren().add(border);

        return barPane;
    }

    /**
     * @return constructed static elements of testTab on init
     */
    private Tab testTab() {
        System.out.println("Initializing Test Tab");
        Tab tab = new Tab("Test Tab");
        GridPane tabGrid = new GridPane();

        Label name = new Label("Hello world:");
        tabGrid.add(name,0,0);

        tab.setContent(tabGrid);
        tab.setId("testTab");
        return tab;
    }

    /**
     * @return constructed static elements of testTab on init
     */
    private Tab summaryTab() {
        Tab tab = new Tab("Summary Tab");
        GridPane tabGrid = new GridPane();

        Label nameStatic = new Label("Name:");
        Label nameDynamic = new Label("NO NAME LOADED");
        nameDynamic.getStyleClass().add("AdventurerName");

        Label adventurerClassStatic = new Label("Class:");
        Label adventurerClassDynamic = new Label("NO CLASS LOADED");
        adventurerClassDynamic.getStyleClass().add("ClassName");

        Label classDieStatic = new Label("Class Die:");
        Label classDieDynamic = new Label("NO DIE LOADED:");
        classDieDynamic.getStyleClass().add("HitDie");

        Label maxHealthStatic = new Label("Max Health:");
        Label maxHealthDynamic = new Label("NO MAX HEALTH LOADED");
        maxHealthDynamic.getStyleClass().add("MaxHealthText");

        Label currHealthStatic = new Label("Curr Health:");
        Label currHealthDynamic = new Label("NO CURR HEALTH LOADED");
        currHealthDynamic.getStyleClass().add("CurrHealthText");

        StackPane healthBar = new StackPane();
        healthBar.getStyleClass().add("HealthBar");

        Button dmgButton = new Button("Take 1 point damage!");
        dmgButton.getStyleClass().add("DamageButton");

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

        tabGrid.add(healthBar,0,5);

        tabGrid.add(dmgButton, 0, 6);

        tab.setContent(tabGrid);
        tab.setId("summaryTab");
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
        Tab tab = new Tab("Notes Tab");
        GridPane tabGrid = new GridPane();
        tabGrid.setMinSize(300,300);

        Label notes = new Label("Notes:");

        TextArea textField = new TextArea ();
        HBox hb = new HBox();
        hb.setId("NotesField");
        hb.setSpacing(10);
        hb.setPrefSize(300,300);

        tabGrid.add(notes,0,0);
        tabGrid.add(textField,0,1);

        tab.setContent(tabGrid);

        return tab;
    }








}
