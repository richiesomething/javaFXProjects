
/* Richard Hernandez 8556435618*/

package hw04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Paint extends Application{

    public static final String savePath = "./src/hw04/";
    public static final String loadPath = "./src/hw04/";

    static public Map<Rectangle, String> rectMap = new HashMap<>();
    static public Map<Line, String> lineMap = new HashMap<>();

    static Stage mainStage;
    static AnchorPane anchorPane;
    static Shape fShape;
    static Scene scene;
    static VBox leftVbox;
    static HBox io;
    static Double mouseX;
    static double mouseY;
    static double rectOffsetX;
    static double rectOffsetY;
    static String selectedShape = "";
    static String selectedColor = "";

    @Override
    public void start(Stage stage) {

        try {

            mainStage = stage;

            leftVbox = new PaintOptions();
            io = new Io();

            anchorPane = loadDefaultAnchorPane(new AnchorPane());

            scene = new Scene(anchorPane, 500, 500);
            stage.setTitle("Paint!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ignored) {}

    }

    public static AnchorPane loadDefaultAnchorPane(AnchorPane anchorPane) {

        anchorPane.getChildren().addAll(leftVbox, io);
        AnchorPane.setTopAnchor(leftVbox, 0d);
        AnchorPane.setLeftAnchor(leftVbox, 0d);
        AnchorPane.setTopAnchor(io, 2d);
        AnchorPane.setRightAnchor(io, 2d);

        anchorPane.setOnMousePressed(m -> {
            if (m.getTarget() instanceof AnchorPane) {
                fShape = null;
                anchorPane.requestFocus();
                mouseX = m.getX();
                mouseY = m.getY();

                try {
                    Io.saveTF.setVisible(false);
                } catch (Exception ignored) {}

            }
        });

        anchorPane.addEventHandler( MouseEvent.MOUSE_RELEASED, (MouseEvent m) -> {
            if (m.getTarget() instanceof Pane){
                if (selectedShape.equalsIgnoreCase("line"))
                    Line_.clickLine(m);
                else if (selectedShape.equalsIgnoreCase("rectangle"))
                    Rect.clickRectangle(m);
            }
            mouseX = 0.0;
            mouseY = 0.0;
        });

        return anchorPane;
    }

    public void start(String[] args) { Application.launch(args); }
}
