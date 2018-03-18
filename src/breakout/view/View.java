package breakout.view;

import breakout.Model.Ball;
import breakout.Model.Model;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class View {

    private static double PLAYFIELD_WIDTH = 1000;
    private static double PLAYFIELD_HEIGHT = 600;


    //    Colors
    private Color BALL_COLOR = Color.RED;
    private Color BACKGROUND_COLOR = Color.BLACK;


    private Stage stage;
    private Model model;
    private Canvas canvas;
    private Scene scene;
    private Pane controlPane;

    public View(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;

    }

    public void init() {
        stage.setTitle("Toll und so");
        canvas = new Canvas();
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        update();
        controlPane = new Pane();
        createControlPane(controlPane);
        root.setBottom(controlPane);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        addSizeListeners();
        stage.show();


    }

    private void createControlPane(Pane controlPane) {
        controlPane.setPadding(new Insets(10));
        Button btn = new Button();
        btn.setText("test");
        btn.setPadding(new Insets(10));
        controlPane.getChildren().add(btn);
    }

    /**
     * resize the canvas if window's size is changed
     */
    private void addSizeListeners() {
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            PLAYFIELD_HEIGHT = (double) newValue - controlPane.getHeight();
            update();
        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            PLAYFIELD_WIDTH = (double) newValue;
            update();
        });
    }

    private void update() {
        canvas.setWidth(PLAYFIELD_WIDTH);
        canvas.setHeight(PLAYFIELD_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Ball ball = model.getBall();
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT);


        gc.setFill(BALL_COLOR);
        System.out.println(xToView(ball.getX() - ball.getRadius() / 2));
        System.out.println(yToView(ball.getY() - ball.getRadius() / 2));
        System.out.println(xToView(yPosToView(ball.getY() - ball.getRadius() / 2)));
        System.out.println(xToView(xToView(ball.getRadius())));
        gc.fillOval(xToView(ball.getX() - ball.getRadius() / 2), yPosToView(ball.getY() - ball.getRadius() / 2), xToView(ball.getRadius()),xToView(ball.getRadius()));
    }

    /////////////
    // Helpers //
    /////////////

    public double xToView(double x) {
        return (x / Model.WIDTH) * PLAYFIELD_WIDTH;
    }

    public double yToView(double y) {
        return (y / Model.HEIGHT) * PLAYFIELD_HEIGHT;
    }

    public double yPosToView(double y) {
        return PLAYFIELD_HEIGHT - yToView(y);
    }
}
