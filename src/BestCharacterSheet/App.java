package BestCharacterSheet;

import javafx.application.Application;
import javafx.stage.Stage;



/**
 * Main application object that kicks everything off
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        UserInterface UI = controller.getUI();

        controller.initModel(new Adventurer());

        PlayerHandbook playerHandbook = new PlayerHandbook();
        AdventurerLoader adventurerLoader = new AdventurerLoader();
        Adventurer darby = adventurerLoader.loadAdventurer("Darby Breyha", playerHandbook);
        System.out.println(darby.getName());
        Adventurer lars = new Adventurer();
        lars.setName("Lars clamberlot");
        lars.setAdventurerClass(playerHandbook.getValidClasses().get("Artificer"));
        AdventurerWriter adventurerWriter = new AdventurerWriter();
        adventurerWriter.writeAdventurer(lars);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
