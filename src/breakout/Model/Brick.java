package breakout.Model;

public class Brick {
    private double x;
    private double y;

    private int color;


    public Brick(double x, double y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    public void collision(Ball b){

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getColor() {
        return color;
    }
}
