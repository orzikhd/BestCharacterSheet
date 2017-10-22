package BestCharacterSheet;

import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * Main application object that kicks everything off
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        UserInterface UI = controller.getUI();

        PlayerHandbook playerHandbook = new PlayerHandbook();

        AdventurerLoader adventurerLoader = new AdventurerLoader();
        Adventurer darby = adventurerLoader.loadAdventurer("Darby Breyha", playerHandbook);

        Adventurer lars = new Adventurer();
        lars.setName("Lars clamberlot");
        lars.setAdventurerClass(playerHandbook.getValidClasses().get("Artificer"));
        lars.setMaxHealth(8);
        lars.setCurrHealth(7);
        lars.setLevel(3);

        Integer[] abilityscores = {10, 10, 10, 10, 10, 10};
        lars.setSkillProficiencies(new HashSet<String>(Arrays.asList("Athletics", "Persuasion")));
        lars.setAbilityScores(new ArrayList<Integer>(Arrays.asList(abilityscores)));
        List<Item> inventory = new ArrayList<Item>();
        inventory.add(new Item("apple"));
        inventory.add(new Item("sword"));
        lars.setInventory(inventory);

        AdventurerWriter adventurerWriter = new AdventurerWriter();
        adventurerWriter.writeAdventurer(lars);

        controller.initModel(darby);

        primaryStage.setTitle("Best Character Sheet");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
