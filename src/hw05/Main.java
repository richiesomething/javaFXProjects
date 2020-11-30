package hw05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {



    public static LeftVBox lvb;
    public static AnchorPane ap;
    public static Neighborhood nb;

    @Override
    public void start(Stage stage) {

        /*      initial stage set up        */

        lvb = new LeftVBox();

        ap = new AnchorPane();
        ap.getChildren().add(lvb);

        AnchorPane.setTopAnchor(lvb, 0d);
        AnchorPane.setLeftAnchor(lvb, 0d);
        AnchorPane.setBottomAnchor(lvb, 0d);

        Scene scene = new Scene(ap, 700, 500);
        stage.setTitle("Neighborhood");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) { Application.launch(args); }
}
