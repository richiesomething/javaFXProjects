package hw01;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.Random;




public class Drawhern784 extends Application {


    // launch program
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);

    }


    //draw concentric circles
    //generated at random location
    //concentric circles of max size radius given in function
    public void concentricCircles(Group root, int x, int y, int radius) {

        for (int r = radius; r > 0; r-=5) {
            if (r % 10 == 0) {
                Circle c = new Circle(x, y, r);
                c.setFill(new Color(.9, .4, .4, 1));
                root.getChildren().add(c);
            }
            else {
                Circle c = new Circle(x, y, r);
                c.setFill(new Color(1, 1, 1, 1));
                root.getChildren().add(c);
            }

        }

    }


    //draw rays from upper left and lower left corners
    //generates rays of random colors
    public void cornerRays(Group root) {

        int x = 500, y = 0;
        Random rand = new Random();

        while (x >= 0) {
            Line line = new Line(0, 0, x, y);
            line.setStroke(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), .7));
            root.getChildren().add(line);
            x -= 2;
            y += 2;
        }

        x = 0;
        y = 0;

        while (x <= 500) {
            Line line = new Line(0, 500, x, y);
            line.setStroke(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), .7));
            root.getChildren().add(line);
            x += 2;
            y += 2;
        }

    }


    //generates the checkered background layer
    public void tiles(Group root) {
        int color = 0;
        for (int x = 0; x < 500; x+=20) {
            for (int y = 0; y < 500; y+= 10) {

                Rectangle rect = new Rectangle(x, y, 20, 10);
                if (color == 0) {
                    rect.setFill(new Color(0, 0, 0, .2));
                    color = 1;
                }
                else if (color == 1) {
                    rect.setFill(new Color(.8, .8, .8, 0));
                    color = 0;
                }
                root.getChildren().add(rect);

            }
            color = color == 1 ? 0 : 1;
        }

    }


    //generates the half circle
    //the circle is generated at low opacity
    //incrementing layers make the appearance of blurring
    public void quadCurve(Group root) {

        int r = 100;
        for (int i = 0; i < 300; i++) {
            QuadCurve qc = new QuadCurve(500, 500, r, 250, 500, 0);
            qc.setFill(new Color(.6, .3, .9, .05));
            root.getChildren().add(qc);
            r += 5;
        }

    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);


        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int rand_x = rand.nextInt(500);
            int rand_y = rand.nextInt(500);
            concentricCircles(root, rand_x, rand_y, 50);
        }

        cornerRays(root);

        tiles(root);

        quadCurve(root);

        stage.setTitle("Richard Hernandez Homework 1");
        stage.setScene(scene);
        stage.show();

    }

}
