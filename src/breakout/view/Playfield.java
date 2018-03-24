package breakout.view;

import breakout.Model.Ball;
import breakout.Model.Brick;
import breakout.Model.Model;
import breakout.Model.Paddle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

import static breakout.view.View.PLAYFIELD_HEIGHT;
import static breakout.view.View.PLAYFIELD_WIDTH;
import static breakout.view.View.xToView;

public class Playfield extends Canvas {

    //    Colors
    private Color BALL_COLOR = Color.RED;
    private Color BACKGROUND_COLOR = Color.BLACK;
    private Color PADDLE_COLOR = Color.BLUE;
    private Color[] BRICK_COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.GHOSTWHITE, Color.CORAL};


    public void update(Model model) {
        setWidth(PLAYFIELD_WIDTH);
        setHeight(PLAYFIELD_HEIGHT);

        drawBall(getGraphicsContext2D(), model);
        drawPaddle(getGraphicsContext2D(), model);
        drawBricks(getGraphicsContext2D(), model);
    }



    ///////////////////////////
    // Draw stuff for update //
    ///////////////////////////

    private void drawBricks(GraphicsContext gc, Model model) {
        List<Brick> bricks = model.getBricks();
        for (Brick brick : bricks) {
            gc.setFill(BRICK_COLORS[brick.getColor()]);
            gc.fillRect(xToView(brick.getX()), View.yPosToView(brick.getY()), xToView(Model.BRICK_WIDTH), Model.BRICK_HEIGHT);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(xToView(brick.getX()), View.yPosToView(brick.getY()), xToView(Model.BRICK_WIDTH), Model.BRICK_HEIGHT);

        }
    }

    private void drawPaddle(GraphicsContext gc, Model model) {
        Paddle p = model.getPaddle();
        gc.setFill(PADDLE_COLOR);
        gc.fillRoundRect(xToView(p.getX()), View.yPosToView(p.getY()), xToView(p.getWidth()), View.yToView(p.getHeight()), xToView(p.getHeight()), View.yToView(p.getHeight()));

    }

    private void drawBall(GraphicsContext gc, Model model) {
        Ball b = model.getBall();
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT);
        gc.setFill(BALL_COLOR);
        gc.fillOval(xToView(b.getX() - b.getRadius()), View.yPosToView(b.getY() + b.getRadius()), xToView(b.getRadius() * 2), xToView(b.getRadius() * 2));
    }


    public void pauseScreen() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.GOLD);
        gc.setFont(new Font(80));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.strokeText("Paused\nPress Space To Continue\nPress Q To Quit", PLAYFIELD_WIDTH / 2, PLAYFIELD_HEIGHT / 2);
    }


}
