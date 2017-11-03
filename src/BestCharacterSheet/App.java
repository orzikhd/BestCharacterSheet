package BestCharacterSheet;

import javafx.application.Application;
import javafx.stage.Stage;

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

        Adventurer darby = AdventurerIO.loadAdventurer("Darby Breyha", playerHandbook);
        controller.initModel(darby);

        Adventurer lars = new Adventurer.AdventurerBuilder()
                .withName("Lars Clamberlot")
                .withLevel(3)
                .withMaxHealth(8)
                .withAdventurerClass(playerHandbook.getValidClasses().get("Artificer"))
                .withAbilityScores(Arrays.asList(10,10,10,10,10,10))
                .withSkillProficiencies(new HashSet<String>(Arrays.asList("Athletics", "Persuasion")))
                .withInventory(new ArrayList<Item>())
                .build();
        lars.getInventory().add(new Item("apple"));
        lars.getInventory().add(new Item("sword"));
        System.out.println(lars.getSkillModifiers());

        AdventurerIO.writeAdventurer(lars);
        primaryStage.setTitle("Best Character Sheet");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();

        System.out.println(Adventurer.ABILITIES);
        System.out.println(Adventurer.SKILLS);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
