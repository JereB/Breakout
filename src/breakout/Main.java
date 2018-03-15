package breakout;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Erster Entwurf");
        Pane root = new Pane();
        Rectangle rect = new Rectangle(100, 10, Color.GREEN);
        root.setPrefSize(800, 500);
        Button btn = new Button("hex");
        btn.setPrefHeight( 30);
        btn.setLayoutY(root.getPrefHeight() - btn.getPrefHeight());
        rect.setY(root.getPrefHeight() - btn.getPrefHeight() - 20);
        Hand h = new Hand(rect);
        root.addEventHandler(MouseEvent.ANY, h);
        root.getChildren().add(rect);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                h.flipColor();
            }
        });
        root.getChildren().add(btn);
        root.resize(400, 400);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    private class Hand implements EventHandler<MouseEvent> {

        private Rectangle rect;
        private Color[] colors = {Color.CORAL, Color.RED, Color.GREEN, Color.KHAKI};
        private int colorIndex;

        public Hand(Rectangle rect) {
            this.rect = rect;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
                rect.setX(event.getX() - rect.getWidth() / 2);
            }
        }


        public void flipColor() {
            colorIndex = (colorIndex + 1) % colors.length;
            rect.setFill(colors[colorIndex]);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}


