package breakout.Model;

import breakout.controller.GameState;

import java.util.List;

public class Model {

    public final static double WIDTH = 1000;
    public final static double HEIGHT = 600;
    public final static double BRICK_HEIGHT = 30;
    public final static double BRICK_WIDTH = 100;
    private final static double START_SPEED = 50;

    private final static String LEVEL_PATH = "data/levels/";

    private GameState state;
    private Ball ball;
    private Paddle paddle;
    private Level level;
    private LevelManager lvlManager = new LevelManager(LEVEL_PATH);


    public void init() {
//       TODO  getLevel
        level = lvlManager.getLevel();
        ball = new Ball(270, START_SPEED, WIDTH / 2, 100);
        paddle = new Paddle(WIDTH / 5);
        state = GameState.GAME_READY;
    }


    public void update(long timePassed) {

        paddle.update();
        collisions();
        ball.updatePos(timePassed);
        if (level.isWon()) {
            state = GameState.GAME_OVER;
        }
    }

    void collisions() {

        if (ball.isLeftOut() || ball.isRightOut()) {
            ball.collide(0);
        }
        if (ball.isUpperOut()) {
            ball.collide(Math.PI * 0.5);
        }
        if (ball.isDownOut()) {
            state = GameState.GAME_OVER;
        }

        level.brickCollisions(ball);
        paddle.collision(ball);
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
        return level.getBricks();
    }
}
