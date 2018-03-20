package breakout.Model;

import breakout.controller.GameState;
import breakout.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    public final static double WIDTH = 1000;
    public final static double HEIGHT = 600;
    public final static double BRICK_HEIGHT = 30;
    public final static double BRICK_WIDTH = 100;
    private final static double START_SPEED = 50;


    private GameState state;
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;


    public void init() {
        bricks = new ArrayList<>();
        ball = new Ball(270, START_SPEED, WIDTH / 2, 100);
        paddle = new Paddle(WIDTH / 5);
        drawBricks();
        state = GameState.GAME_READY;
    }

    private void drawBricks() {
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int color = rand.nextInt(View.BRICK_COLOR_COUNT);
            for (int j = 0; j < 10; j += 2) {
                bricks.add(new Brick(j * BRICK_WIDTH, HEIGHT - (i * BRICK_HEIGHT), color));
            }
        }
    }

    public void update(long timePassed) {

        paddle.update();
        collisions();
        ball.updatePos(timePassed);
        if (bricks.isEmpty()) {
            state = GameState.GAME_OVER;
        }
    }

    public void collisions() {

        if (ball.isLeftOut() || ball.isRightOut()) {
            ball.collide(0);
        }
        if (ball.isUpperOut()) {
            ball.collide(Math.PI * 0.5);
        }
        if (ball.isDownOut()) {
            state = GameState.GAME_OVER;
        }

        brickCollisions();
        paddle.collision(ball);
    }

    private void brickCollisions() {

        Brick b = null;

        for (Brick brick : bricks) {
            if (brick.collision(ball)) {
                b = brick;
                break;
            }
        }
        if (b != null)
            bricks.remove(b);

    }

    /////////////
    // getters //
    /////////////


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Brick> getBricks() {
        return bricks;
    }
}
