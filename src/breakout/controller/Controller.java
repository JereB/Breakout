package breakout.controller;

import breakout.Model.Model;
import breakout.Model.PaddleMovement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller implements EventHandler<KeyEvent> {

    private GameState state;
    private Model model;

    public Controller(GameState state, Model model) {
        this.state = state;
        this.model = model;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.LEFT) {
//                move paddle to the left
                model.getPaddle().setMove(PaddleMovement.LEFT);
            } else if (event.getCode() == KeyCode.RIGHT) {
//                move paddle to the right
                model.getPaddle().setMove(PaddleMovement.RIGHT);
            }else if (event.getCode() == KeyCode.ESCAPE){
                System.exit(0);
            }
        }
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            // set paddle to move no more
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                model.getPaddle().setMove(PaddleMovement.CENTER);
            }

        }
    }
}
