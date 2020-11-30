package midterm;

import hw04.Paint;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Three extends Application {

    Scene scene;
    Pane pane;

    double offsetX, offsetY;

    @Override
    public void start(Stage stage) {

        pane = new Pane();

        Rectangle rect = new Rectangle(0,0,200,200);

        rect.setOnMousePressed(m -> {
            offsetX = Math.abs(rect.getX() - m.getX());
            offsetY = Math.abs(rect.getY() - m.getY());
        });

        rect.setOnMouseDragged(m -> {
            double x = m.getX() - offsetX;
            double y = m.getY() - offsetY;
            if (x > 0 && y > 0 && x + 200 < 500 && y + 200 < 500) {
                rect.setX(x);
                rect.setY(y);
            }
        });

        pane.getChildren().add(rect);
        scene = new Scene(pane, 500, 500);
        stage.setTitle("Paint!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
