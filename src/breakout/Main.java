package breakout;

import breakout.Model.Model;
import breakout.controller.Controller;
import breakout.controller.GameState;
import breakout.controller.Mainloop;
import breakout.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private View view;
    private Model model;
    private GameState state = GameState.GAME_READY;

    @Override
    public void start(Stage primaryStage) {

        model = new Model();
        view = new View(primaryStage, model);

        model.init();
        view.init();


        Controller controller = new Controller(state, model);
        Mainloop loop = new Mainloop(model, view);
        view.setController(controller);
        loop.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


