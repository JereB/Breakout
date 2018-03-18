package breakout.Model;

import breakout.Model.Ball;

public class Model {

    public static double WIDTH = 100;
    public static double HEIGHT = 60;
    private Ball ball;

    public void init(){
        ball = new Ball(Math.toRadians(90),10,50,20);
    }

    public void update(long timePassed){
        ball.updatePos(timePassed);
    }

    public Ball getBall() {
        return ball;
    }
}
