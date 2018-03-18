package breakout;

import breakout.Model.Model;
import breakout.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private View view;
    private Model model;

    @Override
    public void start(Stage primaryStage){
        model = new Model();
        view = new View(primaryStage, model);

        model.init();
        view.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


