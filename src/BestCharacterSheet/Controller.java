package BestCharacterSheet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * Contains functions (read event handlers) to
 * be attached to the GUI.
 *
 * Changes the model based off of notifications from the GUI m
 */
public class Controller {

    private Adventurer adventurer;
    private UserInterface userInterface;
    private PlayerHandbook playerHandBook;

    private AdventurerLoader loader;
    private AdventurerWriter writer;


    public Controller() {
        // init readers and writers
        loader = new AdventurerLoader();
        writer = new AdventurerWriter();

        // create UI
        userInterface = new UserInterface();

        /*
        Button btn = (Button)getByClass("mainbtn");
        EventHandler<ActionEvent> handler = new TestEventHandler();
        btn.setOnAction(handler);
        */

        // test dmg button
        Button btn = (Button)getByClass("DamageButton");
        EventHandler<ActionEvent> handler = new DamageEventHandler();
        btn.setOnAction(handler);

        // test heal button
        btn = (Button)getByClass("HealButton");
        handler = new HealEventHandler();
        btn.setOnAction(handler);

    }

    public void initModel(Adventurer adventurer) {
        this.adventurer = adventurer;

        System.out.println("got here!");
        updateUI();

    }

    /**
     * @return The UI object.
     */
    public UserInterface getUI() {
        return userInterface;
    }

    /**
     * Updates the test tab with new information
     */
    private void updateUI(){
        // Test
        System.out.println("Updating test and summary");
        System.out.println(getAll("AdventurerName"));

        ((Label)getByClass("AdventurerName")).setText(adventurer.getName());
        ((Label)getByClass("ClassName")).setText(adventurer.getAdventurerClass().getName());
        ((Label)getByClass("HitDie")).setText(Integer.toString(adventurer.getAdventurerClass().getHitDie()));
        ((Label)getByClass("MaxHealthText")).setText(Integer.toString(adventurer.getMaxHealth()));
        ((Label)getByClass("CurrHealthText")).setText(Integer.toString(adventurer.getCurrHealth()));

        // init items from inventory
        TableView table = (TableView)id("itemtable");
        final ObservableList<UserInterface.ViewItem> data = table.getItems();
        data.clear();
        List<Item> inventory = adventurer.getInventory();
        for (Item item : inventory) {
            data.add(new UserInterface.ViewItem(item.getDescription()));
        }

        Button inventoryButton = (Button)id("inventoryButton");
        final TextField addItem = (TextField)id("inventoryAddItem");
        inventoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String description = addItem.getText();
                if (description.trim().length() >= 1) {
                    data.add(new UserInterface.ViewItem(description));
                    adventurer.addItem(new Item(description));
                    writeToFile();
                    addItem.clear();
                }
            }
        });

        // clear healthbar
        StackPane healthBar = ((StackPane)getByClass("HealthBar"));
        while(!healthBar.getChildren().isEmpty()) {
            healthBar.getChildren().remove(0);
        }
        healthBar.getChildren().add(userInterface.createBar(adventurer.getCurrHealth(),adventurer.getMaxHealth()));


    }

    private Node id(String id) {
        try {
            Scene scene = userInterface.getScene();
            Node node = scene.lookup("#" + id);
            if (node == null) {
                throw new Exception();
            }
            return node;
        } catch(Exception e) {
            System.out.println("COULD NOT GET NODE: " + id);
            return null;
        }
    }

    /**
     * Gets a node by the styleClass argument
     */
    private Node getByClass(String styleClass) {
        try {
            Scene scene = userInterface.getScene();
            Node node = scene.lookup("." + styleClass);
            if (node == null) {
                throw new Exception();
            }
            return node;
        } catch(Exception e) {
            System.out.println("COULD NOT GET NODE: " + styleClass);
            return null;
        }
    }

    /**
     * Gets all nodes by the styleClass argument
     */
    private Set<Node> getAll(String styleClass) {
        try {
            Scene scene = userInterface.getScene();
            Set<Node> nodeSet = scene.lookup(".root").lookupAll("." + styleClass);
            if (nodeSet == null) {
                throw new Exception();
            }
            return nodeSet;
        } catch(Exception e) {
            System.out.println("COULD NOT GET NODE: " + styleClass);
            return null;
        }
    }
    /**
     * Writes the current adventurer to file
     */
    private void writeToFile() {
        if(adventurer != null) {
            System.out.println("Writing " + this.adventurer.getName() + " to file!");
            writer.writeAdventurer(this.adventurer);
        }
    }

    // EVENT HANDLERS
    /**
     * Test event handler that tells the controller to
     * mark the model dirty based in a handler click
     */
    private class TestEventHandler implements EventHandler<ActionEvent> {

        public TestEventHandler() {
            // THANKS JAVA
        }

        /**
         * What happens when test event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            System.out.println("Hello world!");
        }
    }

    /**
     * Damage event handler that tells the controller
     * to reduce the health of the adventurer on a click
     */
    private class DamageEventHandler implements EventHandler<ActionEvent> {
        public DamageEventHandler() {
            // THANKS JAVA
        }

        /**
         * What happens when test event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            if(adventurer.getCurrHealth() > 0) {
                adventurer.setCurrHealth(adventurer.getCurrHealth() -1);
            }
            writeToFile();
            updateUI();
        }

    }


    /**
     * Damage event handler that tells the controller
     * to increase the health of the adventurer on a click
     */
    private class HealEventHandler implements EventHandler<ActionEvent> {
        public HealEventHandler() {
            // THANKS JAVA
        }

        /**
         * What happens when test event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            System.out.println("firing!");
            if(adventurer.getCurrHealth() < adventurer.getMaxHealth()) {
                adventurer.setCurrHealth(adventurer.getCurrHealth() + 1);
            }
            writeToFile();
            updateUI();
        }

    }
}
