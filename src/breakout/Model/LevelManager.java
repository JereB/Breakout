package breakout.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that stores the Path to levels and reads and creates the next Level if demanded
 */
class LevelManager {

    private int levelCounter = -1;
    private File[] levelFiles;


    public LevelManager(String path) {
        levelFiles = new File(path).listFiles();

    }

    /**
     * Read the next Level from a file.
     * @return the new level
     */
    public Level getLevel() {
//        increment counter and prevent negative Index via overflow
        if (++levelCounter < 0) {
            levelCounter = 0;
        }
//        read the new Level and return the created Level
        return new Level(readLevel(levelFiles[levelCounter % levelFiles.length]));
    }

    /**
     * Read the file to create the corresponding bricks in a List
     * @param file the file in wich the level is saved
     * @return the list of Bricks in the level
     */
    private ArrayList<Brick> readLevel(File file) {

        ArrayList<Brick> bricks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
//                read each line as a new brick
                String[] data = sc.next().split(",");
                for (int i = 0; i < data.length; i++) {
                    System.out.println(i + ": " + data[i]);

                }

//                interpret each comma seperated Value as one Parameter for the brick
                double x = Double.valueOf(data[0].trim());
                double y = Double.valueOf(data[1].trim());
                int color = Integer.valueOf(data[2].trim());
                Brick b = new Brick(x,y,color);
                bricks.add(b);
            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return bricks;
    }

}
