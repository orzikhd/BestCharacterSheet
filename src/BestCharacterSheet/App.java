package BestCharacterSheet;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


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
        System.out.println(darby.getAbilityModifiers());

        Adventurer lars = new Adventurer();
        lars.setName("Lars clamberlot");
        lars.setAdventurerClass(playerHandbook.getValidClasses().get("Artificer"));
        lars.setMaxHealth(8);
        lars.setCurrHealth(7);
        Integer[] abilityscores = {10, 10, 10, 10, 10, 10};
        lars.setAbilityScores(new ArrayList<Integer>(Arrays.asList(abilityscores)));

        AdventurerWriter adventurerWriter = new AdventurerWriter();
        adventurerWriter.writeAdventurer(lars);

        controller.initModel(darby);

        primaryStage.setTitle("Best Character Sheets");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
