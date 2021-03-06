package breakout.Model;

public class Paddle {

    private static final double SPEED = 25;

    private double x;
    private double y;
    private double width;
    private double height = 20;
    private PaddleMovement move = PaddleMovement.CENTER;

    Paddle(double width) {
        this.width = width;
        x = Model.WIDTH / 2 - width / 2;
        y = 10 + height;
    }


    public void collision(Ball ball) {

        double bx = ball.getX();
        double br = ball.getRadius();
        double by = ball.getY();
        double ba = ball.getAngle();

//        if collision happened
        if (by - br <= y && bx + br >= x && bx - br <= x + width && ba >= Math.PI) {
//            relativ x-position to center of paddle.
//            this allows for a simple simulation of a curved surface
            double relX = ((bx - (x + width / 2)) / 2) / width;
            ball.collide(Math.acos(relX));
        }
    }


    /**
     * update the paddles position if it is supposed to be moved
     * @param time passed time since the last update
     */
    public void update(long time) {
        time/=10000000;
        switch (move) {
            case RIGHT:
                if (x + width < Model.WIDTH) {
                    x += time * SPEED;
                }
                break;
            case LEFT:
                if (x > 0) {
                    x -= time * SPEED;
                }
                break;
        }
    }

    ///////////////////////
    // Setter and getter //
    ///////////////////////


    public void setMove(PaddleMovement move) {
        this.move = move;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x - width/2;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
