package labs;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class lab_elf extends Application {

    ArrayList<Rectangle> rectangles;
    ArrayList<Circle> circles;
    int elf_idx = -1;


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        rectangles = new ArrayList<>();
        circles = new ArrayList<>();

        stage.setTitle("Boxer2, click makes box");
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {

            int x = rand.nextInt(380);
            int y = rand.nextInt(380);

            Rectangle r = new Rectangle(10,20);
            r.setX(x);
            r.setY(y);
            root.getChildren().add(r);
            rectangles.add(r);

            Circle circ = new Circle(x + 5, y - 5,5);
            root.getChildren().add(circ);
            circles.add(circ);

        }

        root.addEventHandler( MouseEvent.MOUSE_PRESSED,
                (MouseEvent m) -> {
                    double x = m.getX();
                    double y = m.getY();
                    int i = 0;
                    for (Rectangle r : rectangles) {
                        double diff_x = Math.abs(x - r.getX());
                        double diff_y = Math.abs(y - r.getY());
                        if (diff_x < 10 && diff_y < 20) {
                            elf_idx = i;
                            System.out.println("clicked");
                            break;
                        }
                        i++;
                    }

                }
         );


        root.addEventHandler( MouseEvent.MOUSE_DRAGGED,
                (MouseEvent m) -> {
                    if (elf_idx != -1) {
                        Rectangle r = rectangles.get(elf_idx);
                        Circle c = circles.get(elf_idx);

                        double x = m.getX();
                        double y = m.getY();

                        r.setX(x);
                        r.setY(y);

                        c.setCenterX(x + 5);
                        c.setCenterY(y - 5);
                    }
                }
        );

        root.addEventHandler( MouseEvent.MOUSE_RELEASED,
                (MouseEvent m) -> {
                    elf_idx = -1;
                }
        );

    }

}
