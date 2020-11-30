package midterm;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class One extends Application {

    Scene scene;
    AnchorPane anchorPane;
    Set<Circle> set = new HashSet<>();

    @Override
    public void start(Stage stage) {

        anchorPane = new AnchorPane();
        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new DrawCircleEvent(anchorPane, set));

        Button save = new Button("save");
        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new SaveEvent(anchorPane, set));

        anchorPane.getChildren().add(save);

        AnchorPane.setTopAnchor(save, 0d);
        AnchorPane.setRightAnchor(save, 0d);

        scene = new Scene(anchorPane, 500, 500);
        stage.setTitle("Paint!");
        stage.setScene(scene);
        stage.show();

    }

    private static class DrawCircleEvent implements EventHandler<MouseEvent> {
        AnchorPane ap;
        Set<Circle> st;

        public DrawCircleEvent(AnchorPane a, Set<Circle> s) { ap = a; st = s; }
        @Override

        public void handle(MouseEvent me) {
            Circle circle = new Circle(me.getX(), me.getY(), 10);
            st.add(circle);
            ap.getChildren().add(circle);
        }
    }

    private static class SaveEvent implements EventHandler<MouseEvent> {
        AnchorPane ap;
        Set<Circle> st;

        public SaveEvent(AnchorPane a, Set<Circle> s) { ap = a; st = s; }

        @Override
        public void handle(MouseEvent me) {

            try {
                FileWriter fw = new FileWriter("./src/midterm/one.txt");
                for (Circle c : st) {
                    fw.write(c.getCenterX() + " " + c.getCenterY() + " circle\n");
                    System.out.println(c.getCenterX() + " " + c.getCenterY() + " circle\n");
                }

                fw.flush();
                fw.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
