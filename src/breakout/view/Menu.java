package breakout.view;

import breakout.controller.ButtonHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class Menu extends BorderPane {


//    styling of buttons
    private String CSS_PATH = "Menu.css";

    Menu(ButtonHandler handler) {

        File f = new File(CSS_PATH);
        getStylesheets().clear();
        String path = f.getAbsolutePath();
        if(path.charAt(0) == 'C'){
            path = path.substring(3).replace('\\','/');
        }
        getStylesheets().add("file:///" + path );

        setPrefSize(View.PLAYFIELD_WIDTH, View.PLAYFIELD_HEIGHT);
        VBox btnBox = new VBox();
        btnBox.setSpacing(20);
        btnBox.setMaxWidth(View.PLAYFIELD_WIDTH/3);
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
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    private Button createQuitButton(){
        Button btn = new Button("Quit Game");
        btn.setOnAction(e -> {System.exit(0);});
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }
}
