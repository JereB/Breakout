package breakout.Model;

import breakout.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    public final static double WIDTH = 1000;
    public final static double HEIGHT = 600;
    public final static double START_SPEED = 50;
    public final static double BRICK_HEIGHT = 30;
    public final static double BRICK_WIDTH = 100;



    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks = new ArrayList<>();


    public void init() {
        ball = new Ball(273, START_SPEED, WIDTH / 2, 200);
        paddle = new Paddle(WIDTH/5);
        drawBricks();

    }

    private void drawBricks() {
        Random rand = new Random();
        for(int i = 0; i< 4; i++){
            int color = rand.nextInt(View.BRICK_COLOR_COUNT);
            for(int j = 0; j < 10; j++){
                bricks.add(new Brick(j* BRICK_WIDTH, HEIGHT -(i * BRICK_HEIGHT), color));
            }
        }
    }

    public void update(long timePassed) {
        paddle.update();
        collisions();
        ball.updatePos(timePassed);
    }

    public void collisions(){

        if (ball.isLeftOut() || ball.isRightOut()) {
            ball.collide(0);
        }
        if (ball.isDownOut() || ball.isUpperOut()) {
            ball.collide(Math.PI * 0.5);
        }


        paddle.collision(ball);
    }

    /////////////
    // getters //
    /////////////

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
