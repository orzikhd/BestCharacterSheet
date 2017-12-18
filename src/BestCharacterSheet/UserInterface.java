package BestCharacterSheet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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

        // The tabs that make up the main menu
        TabPane tabPane = new TabPane();

        Tab tab3 = summaryTab();
        tabPane.getTabs().add(tab3);

        Tab tab4 = inventoryTab();
        tabPane.getTabs().add(tab4);

        Tab tab5 = notesTab();
        tabPane.getTabs().add(tab5);

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
     * Note if the numerator is greater than the denominator the bar
     * will be fully filled.
     */
    public StackPane createBar(Integer num, Integer denom, Color color) {
        final int HBHEIGHT = 50;
        final int HBWIDTH = 200;
        final int HBSTROKEWIDTH = 2;

        if(num > denom) {
            num = denom;
        }

        StackPane barPane = new StackPane();

        // outline
        Rectangle border = new Rectangle();
        border.setHeight(HBHEIGHT);
        border.setWidth(HBWIDTH);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(HBSTROKEWIDTH);
        border.setFill(Color.TRANSPARENT);

        // filled portion
        Rectangle r = new Rectangle();
        r.setHeight(HBHEIGHT);
        r.setWidth(HBWIDTH * num/(double)denom);
        r.setFill(color);

        barPane.getChildren().add(r);
        barPane.setAlignment(r, Pos.TOP_LEFT);
        barPane.getChildren().add(border);


        return barPane;
    }

    /**
     * @return constructed static elements of testTab on init
     */
    private Tab testTab() {
        Tab tab = new Tab("Test Tab");
        GridPane tabGrid = new GridPane();

        Label name = new Label("Hello world:");
        tabGrid.add(name,0,0);

        tab.setContent(tabGrid);
        tab.setId("test_tab");
        return tab;
    }

    /**
     * @return constructed static elements of testTab on init
     */
    private Tab summaryTab() {
        Tab tab = new Tab("Summary Tab");

        // load class image
        Image image = new Image("images/classes/artificer.jpg");

        // Resize the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(300);
        iv.setFitHeight(300);
        iv.setSmooth(true);
        iv.setCache(true);

        GridPane tabGrid = new GridPane();


        Label nameStatic = new Label("Name:");
        Label nameDynamic = new Label("NO NAME LOADED");
        nameDynamic.setId("adventurer_name");

        Label adventurerClassStatic = new Label("Class:");
        Label adventurerClassDynamic = new Label("NO CLASS LOADED");
        adventurerClassDynamic.setId("class_name");

        Label classDieStatic = new Label("Class Die:");
        Label classDieDynamic = new Label("NO DIE LOADED:");
        classDieDynamic.setId("hit_die");

        Label levelStatic = new Label("Level: ");
        Label levelDynamic = new Label("NO LEVEL LOADED");
        levelDynamic.setId("level_text");

        StackPane healthBar = new StackPane();
        healthBar.setId("health_bar");

        Label currentHealthStatic = new Label("Current HP: ");
        TextField currentHealthDynamic = new TextField("NO HP LOADED");
        currentHealthDynamic.setId("current_health_text");

        Label maxHealthStatic = new Label("Max HP: ");
        TextField maxHealthDynamic = new TextField("NO HP LOADED");
        maxHealthDynamic.setId("max_health_text");

        tabGrid.add(iv,0,0);

        tabGrid.add(nameStatic,0,1);
        tabGrid.add(nameDynamic,1,1);

        tabGrid.add(adventurerClassStatic,0,2);
        tabGrid.add(adventurerClassDynamic,1,2);

        tabGrid.add(classDieStatic,0,3);
        tabGrid.add(classDieDynamic,1,3);

        tabGrid.add(levelStatic, 0, 4);
        tabGrid.add(levelDynamic, 1, 4);

        // Health and wellness
        tabGrid.add(healthBar,0,5);
        tabGrid.add(currentHealthStatic,0,6);
        tabGrid.add(currentHealthDynamic,1,6);
        tabGrid.add(maxHealthStatic,0,7);
        tabGrid.add(maxHealthDynamic,1,7);

        FlowPane flow = new FlowPane(Orientation.VERTICAL);
        flow.setAlignment(Pos.TOP_LEFT);

        flow.getChildren().addAll(iv,tabGrid);
        flow.setMargin(iv,new Insets(30));
        flow.setMargin(tabGrid,new Insets(30));

        tab.setContent(flow);
        tab.setId("summary_tab");
        return tab;
    }

    /**
     * @return constructed static elements of inventoryTab on init
     */
    private Tab inventoryTab() {
        Tab tab = new Tab("Inventory");

        TableView table = new TableView();
        table.setMaxWidth(802);
        table.setMinHeight(600);
        table.setEditable(true);
        TableColumn itemColumn = new TableColumn("Item Descriptions");
        itemColumn.setMinWidth(800);
        itemColumn.setCellValueFactory(new PropertyValueFactory<ViewItem, String>("description"));

        final ObservableList<ViewItem> data =
                FXCollections.observableArrayList();

        /*
                        new ViewItem("Jar of Pixie Dust")
                        , new ViewItem("Longsword")
                        , new ViewItem("Alchemist Satchel")
                        , new ViewItem("Potion Kit")
                        , new ViewItem("50ft of rope")
                        , new ViewItem("Vial of oil (3)")
                        , new ViewItem("Bulls-eye Lantern")
                        , new ViewItem("Set of noblesman clothes")
                        , new ViewItem("Sending Stones")
                );
        */

        table.setItems(data);
        table.setId("item_table");
        table.getColumns().addAll(itemColumn);

        // ability to add a row
        final TextField addItem = new TextField();
        addItem.setId("inventory_add");
        addItem.setPromptText("Add an item");
        addItem.setMaxWidth(300);

        final Button addButton = new Button("Add");
        addButton.setId("inventory_button");

        final HBox hb = new HBox();
        hb.getChildren().addAll(addItem, addButton);
        hb.setSpacing(5);

        final VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(20, 0, 0, 100));
        vBox.getChildren().addAll(table, hb);

        tab.setContent(vBox);

        return tab;
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

        final Label notes = new Label("Notes:");

        final TextArea textField = new TextArea();
        textField.setId("notes_text");

        final Button saveNotes = new Button("Save");
        saveNotes.setId("notes_button");

        tabGrid.add(notes,0,0);
        tabGrid.add(textField,0,1);
        tabGrid.add(saveNotes,0,2);

        tab.setContent(tabGrid);

        return tab;
    }

    public static class ViewItem {
        private final SimpleStringProperty description;

        public ViewItem(String description) {
            this.description = new SimpleStringProperty(description);
        }

        public String getDescription() {
            return description.get();
        }

        public void setDescription(String description) {
            this.description.set(description);
        }
    }
}
