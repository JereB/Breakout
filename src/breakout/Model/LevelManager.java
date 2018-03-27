package breakout.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that stores the Path to levels and reads and creates the next Level if demanded
 */
class LevelManager {

    private File[] levelFiles;


    public LevelManager(String path) {
        levelFiles = new File(path).listFiles();

    }

    /**
     * Read the desired Level from a file.
     *
     * @param counter the number of the level
     * @return the new level
     */
    public Level getLevel(int counter) {
//        read the new Level and return the created Level

        ArrayList<Brick> bricks = new ArrayList<>();
        File file = levelFiles[counter % levelFiles.length];

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
//                read each line as a new brick
                String[] data = sc.next().split(",");

//                interpret each comma seperated Value as one Parameter for the brick
                double x = Double.valueOf(data[0].trim());
                double y = Double.valueOf(data[1].trim());
                int color = Integer.valueOf(data[2].trim());
                Brick b = new Brick(x, y, color);
                bricks.add(b);
            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return new Level(bricks);
    }

}
