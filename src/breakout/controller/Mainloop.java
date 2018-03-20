package breakout.controller;

import breakout.Model.Model;
import breakout.view.View;
import javafx.animation.AnimationTimer;

public class Mainloop extends AnimationTimer {

    private long lastFrame;
    private Model model;
    private View view;

    public Mainloop(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(long now) {

        switch (model.getState()){
            case GAME_RUNNING:
                gameLoop(now);
                break;

            case GAME_OVER:
                gameOver();
                break;
            case GAME_PAUSED:
                lastFrame = 0;
                break;
            case GAME_READY:
                break;
        }

    }

    private void gameLoop(long now){
        if (lastFrame > 0) {
            long delta = now - lastFrame;

            model.update(delta);
            view.update();

//            print fps
//            System.out.println(1000000000/delta);

        }
        lastFrame = now;
    }

    public void gameOver(){
        model.init();
        lastFrame = 0;
        view.update();
    }


}
