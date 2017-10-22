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

        PlayerHandbook playerHandbook = new PlayerHandbook();

        AdventurerLoader adventurerLoader = new AdventurerLoader();
        Adventurer adventurer = adventurerLoader.loadAdventurer("Darby Breyha", playerHandbook);

        controller.initModel(adventurer);


        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(UI.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
