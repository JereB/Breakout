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


    public boolean collision(Ball b) {
        return isInside(b) || sideCollision(b) || upDowCollision(b);
    }

    private boolean isInside(Ball b) {
        // if the center of the ball is already inside the Position.
        if (b.getY() < y && b.getY() > y - Model.BRICK_HEIGHT && b.getX() > x && b.getX() < x + Model.BRICK_WIDTH) {

            double ups = Math.min(y - b.getY(), b.getY() - (y - Model.BRICK_HEIGHT));
            double sides = Math.min(b.getX() - x, (x + Model.BRICK_WIDTH) - b.getX());

            if (ups < sides) {
                b.collide(Math.PI * 0.5);
            } else {
                b.collide(0);
            }

            return true;
        }
        return false;
    }

    private boolean upDowCollision(Ball b) {
//        center of ball is on the correct width
        if (b.getX() >= x && b.getX() <= x + Model.BRICK_WIDTH) {
//            radius of ball is inside the Brick
            if (b.getY() + b.getRadius() >= y - Model.BRICK_HEIGHT && b.getY() - b.getRadius() <= y) {
                b.collide(Math.PI * 0.5);
                return true;
            }
        }
        return false;
    }

    private boolean sideCollision(Ball b) {
//        enter of ball is at the right height
        if (b.getY() <= y && b.getY() >= y - Model.BRICK_HEIGHT) {
//            radius is inside the Brick
            if (b.getX() + b.getRadius() >= x && b.getX() - b.getRadius() <= x + Model.BRICK_WIDTH) {
                b.collide(0);
                return true;
            }
        }

        return false;
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
