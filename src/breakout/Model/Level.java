package breakout.Model;

import java.util.ArrayList;

/**
 * Small class that stores all bricks for the current Level
 */
class Level {
    private ArrayList<Brick> bricks;

    public Level(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    /**
     * If no Bricks are Left the level is won
     *
     * @return return {@code true} if there are no Bricks left
     */
    public boolean isWon() {
        return bricks.isEmpty();
    }


    /**
     * Detect if there is any collision with a Brick and if so, remove the brick and
     * change the balls direction accordingly
     *
     * @param ball the Ball of the game
     */
    void brickCollisions(Ball ball) {

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


}
