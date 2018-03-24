package breakout.view;

import breakout.controller.ButtonHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Menu extends BorderPane {


//    styling of buttons

    public Menu(ButtonHandler handler) {
        setPrefSize(View.PLAYFIELD_WIDTH, View.PLAYFIELD_HEIGHT);
        VBox btnBox = new VBox(30);
        btnBox.setAlignment(Pos.CENTER);

        // Add buttons
        btnBox.getChildren().add(createResumeButton(handler.getCancelHandler()));
        btnBox.getChildren().add(createQuitButton());
        setCenter(btnBox);
    }

    private Button createResumeButton(EventHandler<ActionEvent> handler){
        Button btn = new Button("Resume");
        btn.setOnAction(handler);
        btn.setCancelButton(true);
        return btn;
    }

    private Button createQuitButton(){
        Button btn = new Button("Quit Game");
        btn.setOnAction(e -> {System.exit(0);});
        return btn;
    }
}
