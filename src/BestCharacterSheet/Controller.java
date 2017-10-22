package BestCharacterSheet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;


/**
 * Contains functions (read event handlers) to
 * be attached to the GUI.
 */
public class Controller {

    private Adventurer adventurer;
    private UserInterface userInterface;
    private boolean isDirty;

    public Controller() {
        userInterface = new UserInterface();
        Button btn = (Button)id("mainbtn");
        EventHandler<ActionEvent> handler = new TestEventHandler();
        btn.setOnAction(handler);

    }

    public void initModel(Adventurer adventurer) {
        // ensure model is only set once:
        if (this.adventurer != null) {
            throw new IllegalStateException("Adventurer can only be initialized once");
        }
        this.adventurer = adventurer;

        System.out.println("got here!");
        updateTest();

    }

    public void setDirty(boolean set_dirty){
        isDirty = set_dirty;
    }

    public boolean getDirty() {
        return isDirty;
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
    private void updateTest(){
        ((Label)id("testName")).setText(adventurer.getName());
        ((Label)id("testClass")).setText(adventurer.getAdventurerClass().getName());
        System.out.println("got here2!");
        ((Label)id("testDie")).setText(Integer.toString(adventurer.getAdventurerClass().getHitDie()));
        Integer.toString(adventurer.getMaxHealth());
        Integer.toString(adventurer.getCurrHealth());
        ((Label)id("testMaxHealth")).setText(Integer.toString(adventurer.getMaxHealth()));
        ((Label)id("testCurrHealth")).setText(Integer.toString(adventurer.getCurrHealth()));

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
            System.out.println("Controller isDirty = " + Controller.this.getDirty());
            Controller.this.setDirty(true);
        }
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

}
