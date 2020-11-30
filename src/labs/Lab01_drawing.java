package labs;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Lab01_drawing extends Application {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub

        Circle circ = new Circle(40,40,30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("My JavaFX Circle App");
        stage.setScene(scene);
        stage.show();

    }
}
