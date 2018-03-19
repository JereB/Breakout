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
        if (lastFrame > 0) {
            long delta = now - lastFrame;

            model.update(delta);
            view.update();

            System.out.println(1000000000/delta);

        }
        lastFrame = now;
    }
}
