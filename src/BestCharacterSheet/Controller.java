package BestCharacterSheet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

        Button btn = (Button)id("mainbtn");
        EventHandler<ActionEvent> handler = new TestEventHandler();
        btn.setOnAction(handler);

        // test dmg button
        btn = (Button)id("testDmgBtn");
        handler = new DamageEventHandler();
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
        System.out.println("Updating test");
        ((Label)id("testName")).setText(adventurer.getName());
        ((Label)id("testClass")).setText(adventurer.getAdventurerClass().getName());
        ((Label)id("testDie")).setText(Integer.toString(adventurer.getAdventurerClass().getHitDie()));
        Integer.toString(adventurer.getMaxHealth());
        Integer.toString(adventurer.getCurrHealth());
        ((Label)id("testMaxHealth")).setText(Integer.toString(adventurer.getMaxHealth()));
        ((Label)id("testCurrHealth")).setText(Integer.toString(adventurer.getCurrHealth()));

        // ((Label)id("testHealth")).setText(Integer.toString(adventurer.().getHitDie()));
    }

    private Node id(String id) {
        try {
            Scene scene = userInterface.getScene();
            if (scene == null) {
                throw new Exception("id: The node returned is null!");
            }
            return scene.lookup("#" + id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
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
            adventurer.setCurrHealth(adventurer.getCurrHealth() -1);
            writeToFile();
            updateUI();
        }

    }
}
