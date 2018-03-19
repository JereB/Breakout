package breakout.view;

import breakout.Model.Ball;
import breakout.Model.Brick;
import breakout.Model.Model;
import breakout.Model.Paddle;
import breakout.controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;


public class View {

    public static final int  BRICK_COLOR_COUNT = 5;

    // dimension of the view
    private static double PLAYFIELD_WIDTH = 1000;
    private static double PLAYFIELD_HEIGHT = 600;



    //    Colors
    private Color BALL_COLOR = Color.RED;
    private Color BACKGROUND_COLOR = Color.BLACK;
    private Color PADDLE_COLOR = Color.BLUE;
    private Color[] BRICK_COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.GHOSTWHITE, Color.CORAL};


    // View objects
    private Stage stage;
    private Model model;
    private Canvas canvas;
    private Scene scene;


    public View(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;

    }


    public void init() {
        stage.setTitle("Toll und so");
        canvas = new Canvas();
        Group root = new Group();
        root.getChildren().add(canvas);
        scene = new Scene(root);
        stage.setScene(scene);

        update();
//        stage.setResizable(false);

    }

    public void setController(Controller controller) {
        scene.setOnKeyPressed(controller);
        scene.setOnKeyReleased(controller);
    }

    public void update() {
        canvas.setWidth(PLAYFIELD_WIDTH);
        canvas.setHeight(PLAYFIELD_HEIGHT);
        drawBall(canvas.getGraphicsContext2D());
        drawPaddle(canvas.getGraphicsContext2D());
        drawBricks(canvas.getGraphicsContext2D());
    }

    private void drawBricks(GraphicsContext gc) {
        List<Brick> bricks = model.getBricks();
        for (Brick brick : bricks){
            gc.setFill(BRICK_COLORS[brick.getColor()]);
            gc.fillRect(xToView(brick.getX()), yPosToView(brick.getY()), xToView(Model.BRICK_WIDTH), Model.BRICK_HEIGHT);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(xToView(brick.getX()), yPosToView(brick.getY()), xToView(Model.BRICK_WIDTH), Model.BRICK_HEIGHT);

        }
    }

    private void drawPaddle(GraphicsContext gc) {
        Paddle p = model.getPaddle();
        gc.setFill(PADDLE_COLOR);
        gc.fillRoundRect(xToView(p.getX()), yPosToView(p.getY()), xToView(p.getWidth()), yToView(p.getHeight()), xToView(p.getHeight() ), yToView(p.getHeight() ));

    }

    private void drawBall(GraphicsContext gc) {
        Ball b = model.getBall();
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT);
        gc.setFill(BALL_COLOR);
        gc.fillOval(xToView(b.getX() - b.getRadius()), yPosToView(b.getY() + b.getRadius()), xToView(b.getRadius() * 2), xToView(b.getRadius() * 2));
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
