package BestCharacterSheet;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import javafx.beans.value.ChangeListener;
import java.util.List;
import java.util.Optional;
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
        Button btn;
        EventHandler<ActionEvent> handler;
        ChangeListener<String> listener;

        // set max health text box
        TextField maxHealthDynamic = (TextField)id("max_health_text");
        listener = new SetMaxHealthListener();
        maxHealthDynamic.textProperty().addListener(listener);

        // set health text box
        TextField currHealthDynamic = (TextField)id("current_health_text");
        listener = new SetHealthListener();
        currHealthDynamic.textProperty().addListener(listener);

        // INVENTORY
        // add inventory item btn
        btn = (Button)id("inventory_button_add");
        handler = new AddItemEventHandler();
        btn.setOnAction(handler);

        // remove inventory item btn
        btn = (Button)id("inventory_button_remove");
        handler = new RemoveItemEventHandler();
        btn.setOnAction(handler);

        // save notes btn
        btn = (Button)id("notes_button");
        handler = new SaveNotesEventHandler();
        btn.setOnAction(handler);

    }

    public void initModel(Adventurer adventurer) {
        this.adventurer = adventurer;

        // Set initial health parameters
        TextField maxHealthDynamic = (TextField)id("max_health_text");
        maxHealthDynamic.setText(adventurer.getMaxHealth().toString());
        // set health text box
        TextField currHealthDynamic = (TextField)id("current_health_text");
        currHealthDynamic.setText(adventurer.getCurrHealth().toString());
        // set notes text box
        TextArea notesText = (TextArea)id("notes_text");
        notesText.setText(adventurer.getNotes());

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
            AdventurerIO.writeAdventurer(this.adventurer);
        }
    }

    // EVENT HANDLERS / LISTENERS
    /**
     * Test event handler that tells the controller to
     * mark the model dirty based in a handler click
     */
    private class TestEventHandler implements EventHandler<ActionEvent> {
        /**
         * What happens when event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            System.out.println("Hello world!");
        }
    }

    /**
     * Tells controller to add item to Adventurer's inventory
     */
    private class AddItemEventHandler implements EventHandler<ActionEvent> {
        /**
         * What happens when event handler is fired
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

    /**
     * Tells controller to add item to Adventurer's inventory
     */
    private class RemoveItemEventHandler implements EventHandler<ActionEvent> {

        /**
         * What happens when event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {

            TableView table = (TableView)id("item_table");
            UserInterface.ViewItem selected = (UserInterface.ViewItem)table.getSelectionModel().getSelectedItem();

            if(selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove Item Confirmation");
                alert.setHeaderText("Remove: \"" + selected.getDescription() + "\"?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    // Remove from view
                    table.getItems().remove(selected);

                    // Remove from underlying adventurer object
                    adventurer.removeItemByDescription(selected.getDescription());
                    writeToFile();
                }
            }
        }
    }

    /**
     * Tells controller to save notes to adventurer
     */
    private class SaveNotesEventHandler implements EventHandler<ActionEvent> {
        /**
         * What happens when event handler is fired
         * @param e: The event
         */
        @Override
        public void handle(ActionEvent e) {
            System.out.println("SaveNotesHandler!");
            final TextArea notesText = (TextArea)id("notes_text");

            String notes = notesText.getText();
            adventurer.setNotes(notes);

            writeToFile();
            updateUI();
        }
    }

    /**
     * Set max health event handler that tells the controller
     * to set the health of the adventurer to the specified amount
     */
    private class SetMaxHealthListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

            TextField setHealthField = (TextField) id("max_health_text");
            if(newValue == null || newValue.equals("")) {
                return;
            }

            if(newValue.length() > 8 ) {
                setHealthField.setText(oldValue);
            }

            // Make sure the value in the health field is an integer
            // and within range of integers
            if (!newValue.matches("\\d*")) {
                setHealthField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                int healthSet = Integer.parseInt(setHealthField.getText());

                if (healthSet < 1) {
                    adventurer.setMaxHealth(1);
                } else {
                    adventurer.setMaxHealth(healthSet);
                }

                writeToFile();
                updateUI();
            }
        }
    }

     /**
     * Set health event handler that tells the controller
     * to set the health of the adventurer to the specified amount
     */
    private class SetHealthListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observable,
                           String oldValue, String newValue) {

            TextField setHealthField = (TextField)id("current_health_text");

            if(newValue == null || newValue.equals("")) {
                return;
            }

            if(newValue.length() > 8 ) {
                setHealthField.setText(oldValue);
                return;
            }

            // Make sure the value in the health field is an integer
            if (!newValue.matches("\\d*")) {
                setHealthField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
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
    }
}
