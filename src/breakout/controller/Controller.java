package breakout.controller;

import breakout.Model.Model;
import breakout.Model.PaddleMovement;
import breakout.view.View;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller implements EventHandler<KeyEvent> {

    private Model model;
    private View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(KeyEvent event) {

        switch (model.getState()) {
            case GAME_RUNNING:
                gameRunning(event);
                break;
            case GAME_READY:
            case GAME_PAUSED:
                gameReady(event);

        }

    }


    private void gameRunning(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.LEFT) {
//                move paddle to the left
                model.getPaddle().setMove(PaddleMovement.LEFT);
            } else if (event.getCode() == KeyCode.RIGHT) {
//                move paddle to the right
                model.getPaddle().setMove(PaddleMovement.RIGHT);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                model.setState(GameState.GAME_PAUSED);
                view.pauseScreen();
            }
        }
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            // set paddle to move no more
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                model.getPaddle().setMove(PaddleMovement.CENTER);
            }

        }
    }

    private void gameReady(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if(event.getCode() == KeyCode.SPACE) {
                model.setState(GameState.GAME_RUNNING);
            } else if (event.getCode() == KeyCode.Q){
                System.exit(0);
            }
        }
    }

}
