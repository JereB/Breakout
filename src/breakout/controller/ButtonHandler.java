package breakout.controller;

import breakout.Model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler {

    private Model model;

    public ButtonHandler(Model model){
        this.model = model;
    }

    public EventHandler<ActionEvent> getCancelHandler(){
        return new CancelHandler();
    }

    public EventHandler<ActionEvent> getQuitHandler(){
        return new CancelHandler();
    }

    ////////////////////////
    // Handler definition //
    ////////////////////////

    private class CancelHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            model.setState(GameState.GAME_RUNNING);
        }
    }


    private class QuitHandler implements  EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            System.exit(0);
        }
    }
}
