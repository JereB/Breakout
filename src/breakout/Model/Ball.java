package breakout.Model;


/**
 * Model of the ball with position, speed and moving angles
 */
public class Ball {
    private double angle;
    private double speed;
    private double x;
    private double y;
    private double radius = 8;


    /**
     * Create new ball
     *
     * @param angle starting angle in degrees. 0 is horizontal to the right
     * @param speed starting speed of the ball
     * @param x     x-position in the model
     * @param y     y-position in the model
     */
    public Ball(double angle, double speed, double x, double y) {
        this.angle = Math.toRadians(angle);
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the current speed of the ball
     *
     * @return current speed
     */
    public double getAngle() {
        return angle;
    }

    /**
     * sets the angle of movement of the ball.
     * 0 is horizontal to the right.
     *
     * @param angle new angle in degrees
     */
    public void setAngle(double angle) {
        this.angle = Math.toRadians(angle);
    }

    /**
     * get the current Speed
     *
     * @return the current speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * set the current speed
     *
     * @param speed current speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * get current x-postion
     *
     * @return x position
     */
    public double getX() {
        return x;
    }

    /**
     * set the x-position of the ball
     *
     * @param x the new x-position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * get current y-postion
     *
     * @return y position
     */
    public double getY() {
        return y;
    }

    /**
     * set the y-position of the ball
     *
     * @param y the new y-position
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * changes the balls position depending on the time passed, the position and speed of the ball
     *
     * @param time the time passed
     */
    public void updatePos(long time) {
        long t = time / 100000;
        x = Math.cos(angle) * speed * t * 0.001 + x;
        y = Math.sin(angle) * speed * t * 0.001 + y;
    }

    public double getRadius() {
        return radius;
    }


    /**
     * changes the angle of the ball if a surface is hit
     * @param surfaceAngle the angle of the surfaces normal-vector
     */
    public void collide(double surfaceAngle) {
        angle = (2 * surfaceAngle - angle + Math.PI) % (Math.PI * 2);
        if(angle < 0 ){
            angle += Math.PI *2;
        }

    }

    public boolean isLeftOut(){
        return (x - radius <= 0 && angle >= Math.PI *0.5 && angle <= Math.PI *1.5);
    }
    public boolean isRightOut(){
        return (x + radius >= Model.WIDTH && !(angle > Math.PI * 0.5 && angle < Math.PI * 1.5));
    }
    public boolean isUpperOut(){
        return (y + radius >= Model.HEIGHT && angle >= 0 && angle <= Math.PI);
    }
    public boolean isDownOut(){
        return (y -radius <= 0 && angle >= Math.PI && angle <= Math.PI *2);
    }

}


