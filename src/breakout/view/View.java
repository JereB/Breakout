package breakout.view;

import breakout.Model.Model;
import breakout.controller.ButtonHandler;
import breakout.controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class View {

    public static final int  BRICK_COLOR_COUNT = 5;

    // dimension of the view
    static double PLAYFIELD_WIDTH = 1000;
    static double PLAYFIELD_HEIGHT = 600;


    // View objects
    private Stage stage;
    private Model model;
    private Playfield playfield;
    private Scene scene;
    private Group root;
    private  Menu menu;


//    flag to remove pauseScreen
    private boolean pauseScreen = false;

    public View(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;
        menu = new Menu(new ButtonHandler(model));

    }


    public void init() {
        stage.setTitle("Toll und so");
        playfield = new Playfield();
        root = new Group();
        root.getChildren().add(playfield);
        scene = new Scene(root);
        stage.setScene(scene);

        update();

    }

    public void pauseScreen(){
//        playfield.pauseScreen();
          menu = new Menu(new ButtonHandler(model));
          root.getChildren().add(menu);
          pauseScreen = true;
    }


    public void setController(Controller controller) {
        scene.setOnKeyPressed(controller);
        scene.setOnKeyReleased(controller);
    }



    public void update() {
        if (pauseScreen) {
            root.getChildren().remove(menu);
        }
        playfield.update(model);
    }

    /////////////
    // Helpers //
    /////////////

    public static double xToView(double x) {
        return (x / Model.WIDTH) * PLAYFIELD_WIDTH;
    }

    public static double yToView(double y) {
        return (y / Model.HEIGHT) * PLAYFIELD_HEIGHT;
    }

    public static double yPosToView(double y) {
        return PLAYFIELD_HEIGHT - yToView(y);
    }
}
