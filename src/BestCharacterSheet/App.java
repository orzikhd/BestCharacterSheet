package BestCharacterSheet;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


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
        System.out.println(darby.getName());
        System.out.println(darby.getAbilityScores());
        System.out.println(darby.getAbilityModifiers());
        System.out.println(darby.getSkillProficiencies());
        System.out.println(darby.getProficiencyBonus());
        System.out.println(darby.getSkillModifiers());

        Adventurer lars = new Adventurer();
        lars.setName("Lars clamberlot");
        lars.setAdventurerClass(playerHandbook.getValidClasses().get("Artificer"));
        lars.setMaxHealth(8);
        lars.setCurrHealth(7);
        lars.setLevel(3);
        System.out.println(lars.getProficiencyBonus());
        Integer[] abilityscores = {10, 10, 10, 10, 10, 10};
        lars.setSkillProficiencies(new HashSet<String>(Arrays.asList("Athletics", "Persuasion")));
        lars.setAbilityScores(new ArrayList<Integer>(Arrays.asList(abilityscores)));
        System.out.println(lars.getSkillModifiers());

        AdventurerWriter adventurerWriter = new AdventurerWriter();
        adventurerWriter.writeAdventurer(lars);

        controller.initModel(darby);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
