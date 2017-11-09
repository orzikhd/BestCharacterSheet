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

    public Controller() {
        // create UI
        userInterface = new UserInterface();

        // Add event handlers to interactive portions of the UI
        EventHandler<ActionEvent> handler;
        Button btn;

        // set health btn
        //btn = (Button)id("set_health_button");
        //handler = new SetHealthEventHandler();
        //btn.setOnAction(handler);

        // add inventory item btn
        // btn = (Button)id("inventory_add");
        // handler = new AddItemEventHandler();
        // btn.setOnAction(handler);


    }

    public void initModel(Adventurer adventurer) {
        this.adventurer = adventurer;
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

        ((Label)id("adventurer_name")).setText(adventurer.getName());
        ((Label)id("class_name")).setText(adventurer.getAdventurerClass().getName());
        ((Label)id("hit_die")).setText(Integer.toString(adventurer.getAdventurerClass().getHitDie()));
        ((Label)id("level_text")).setText(Integer.toString(adventurer.getLevel()));

        // init items from inventory
        TableView table = (TableView)id("item_table");
        final ObservableList<UserInterface.ViewItem> data = table.getItems();
        data.clear();
        List<Item> inventory = adventurer.getInventory();
        for (Item item : inventory) {
            data.add(new UserInterface.ViewItem(item.getDescription()));
        }

        // clear healthbar
        StackPane healthBar = ((StackPane)id("health_bar"));
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
            System.out.println("COULD NOT FIND NODE WITH ID: " + id);
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
            System.out.println("COULD NOT FIND NODE WITH CLASS: " + styleClass);
            return null;
        }
    }
    /**
     * Writes the current adventurer to file
     */
    private void writeToFile() {
        if(adventurer != null) {
            System.out.println("Writing " + this.adventurer.getName() + " to file!");
            AdventurerIO.writeAdventurer(this.adventurer);
        }
    }

    // EVENT HANDLERS
    /**
     * Test event handler that tells the controller to
     * mark the model dirty based in a handler click
     */
    private class TestEventHandler implements EventHandler<ActionEvent> {
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
     * Set health event handler that tells the controller
     * to set the health of the adventurer to the specified amount
     */
    private class SetHealthEventHandler implements EventHandler<ActionEvent> {
        /**
         * What happens when test event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            TextField setHealthField = (TextField)id("set_health_field");
            int healthSet = Integer.parseInt(setHealthField.getText());

            if(healthSet > adventurer.getMaxHealth()) {
                adventurer.setCurrHealth(adventurer.getMaxHealth());
            } else if (healthSet < 0) {
                adventurer.setCurrHealth(0);
            } else {
                adventurer.setCurrHealth(healthSet);
            }

            writeToFile();
            updateUI();
        }
    }

    /**
     * Damage event handler that tells the controller
     * to increase the health of the adventurer on a click
     */
    private class AddItemEventHandler implements EventHandler<ActionEvent> {
        /**
         * What happens when test event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {

            final TextField addItem = (TextField)id("inventory_add");
            String description = addItem.getText();

            TableView table = (TableView)id("item_table");
            final ObservableList<UserInterface.ViewItem> data = table.getItems();

            if (description.trim().length() >= 1) {
                data.add(new UserInterface.ViewItem(description));
                adventurer.addItem(new Item(description));
                writeToFile();
                addItem.clear();
            }
        }
    }
}
