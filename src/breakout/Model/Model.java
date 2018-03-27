package breakout.Model;

import breakout.controller.GameState;

import java.util.List;

public class Model {

    public final static double WIDTH = 1000;
    public final static double HEIGHT = 600;
    public final static double BRICK_HEIGHT = 30;
    public final static double BRICK_WIDTH = 100;

    //    starting information for the ball
    private final static double START_SPEED = 50;
    private final static double BALL_X = WIDTH / 2;
    private final static double BALL_y = 100;
    private final static double ANGLE = 270;


    private final static int START_LIVES = 3;

    private final static String LEVEL_PATH = "data/levels/";

    private GameState state;
    private Ball ball;
    private Paddle paddle;
    private Level level;
    private LevelManager lvlManager = new LevelManager(LEVEL_PATH);
    private int levelCounter = 0;
    private int lives = START_LIVES;


    /**
     * Method to start a new Level or game
     */
    public void init() {
//        load a new level
        level = lvlManager.getLevel(levelCounter);
        ball = new Ball(ANGLE, START_SPEED, BALL_X, BALL_y);
        paddle = new Paddle(WIDTH / 5);
        state = GameState.GAME_READY;
    }


    /**
     * Update the ball's position and detect collisions, that happened.
     *
     * @param timePassed the passed time since the last update
     */
    public void update(long timePassed) {

        paddle.update();
//        ball update is only executed, if the ball has not hit the bottom border
        if (collisions()) {
            ball.updatePos(timePassed);
            if (level.isWon()) {
                state = GameState.GAME_OVER;
                levelCounter++;
                lives++;
            }
        }
    }

    /**
     * detect for collision with the walls and bricks
     *
     * @return return {@code false} if the ball is lost aka the bottom border is hit
     */
    private boolean collisions() {

//        hat the ball hit left or right wall?
        if (ball.isLeftOut() || ball.isRightOut()) {
            ball.collide(0);
        }
//        has the ball hit the ceiling
        if (ball.isUpperOut()) {
            ball.collide(Math.PI * 0.5);
        }
//        is the ball lost and a live is lost
        if (ball.isDownOut()) {
            lives--;
//            no lives left
            if (lives == 0) {
                state = GameState.GAME_OVER;
                levelCounter = 0;
                lives = START_LIVES;
            } else {
                ball.setX(BALL_X);
                ball.setY(BALL_y);
                ball.setAngle(ANGLE);
                ball.setSpeed(START_SPEED);
                paddle.setX(WIDTH / 2);
                paddle.setMove(PaddleMovement.CENTER);
                state = GameState.GAME_READY;
            }
            return false;
        }

//        is a brick hit
        level.brickCollisions(ball);
        paddle.collision(ball);

        return true;
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

    public int getLives() {
        return lives;
    }
}
