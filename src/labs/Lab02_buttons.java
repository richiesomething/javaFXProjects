package labs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Lab02_buttons extends Application {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        javafx.scene.control.Button btn = new javafx.scene.control.Button();
        btn.setText("Change color");
        btn.setPadding(new Insets(25));
        btn.setLayoutX(0);
        btn.setLayoutY(0);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ArrayList<String> colors = new ArrayList<>();
                colors.add("red");
                colors.add("blue");
                colors.add("green");
                colors.add("orange");
                colors.add("pink");
                colors.add("black");
                colors.add("purple");

                Random rand =  new Random();

                btn.setStyle("-fx-background-color: " + colors.get(rand.nextInt(7)));
            }
        });

        root.getChildren().add(btn);



        javafx.scene.control.Button btn1 = new javafx.scene.control.Button();
        btn1.setText("Random Box");
        btn1.setPadding(new Insets(25));
        btn1.setLayoutX(150);
        btn1.setLayoutY(0);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Random rand = new Random();
                int randx = rand.nextInt(300);
                int randy = rand.nextInt(200);

                Rectangle rect = new Rectangle(randx, 200 + randy, 100, 50);
                root.getChildren().add(rect);

            }
        });
        root.getChildren().add(btn1)
        ;

        javafx.scene.control.Button btn3 = new javafx.scene.control.Button();
        btn3.setText("Tic Tak Toe");
        btn3.setPadding(new Insets(25));
        btn3.setLayoutX(300);
        btn3.setLayoutY(0);

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Line l1 = new Line(300, 200, 300, 350);
                root.getChildren().add(l1);

                Line l2 = new Line(350, 200, 350, 350);
                root.getChildren().add(l2);

                Line l3 = new Line(250, 250, 400, 250);
                root.getChildren().add(l3);

                Line l4 = new Line(250, 300, 400, 300);
                root.getChildren().add(l4);

            }
        });
        root.getChildren().add(btn3);



        stage.setTitle("Richard Hernandez lab2");
        stage.setScene(scene);
        stage.show();
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

        }

    }
}
