package midterm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.*;

public class Two extends Application {

    AnchorPane anchorPane;
    Set<Label> users;
    Random rand;

    @Override
    public void start(Stage stage) {

        anchorPane = new AnchorPane();
        users = new HashSet<>();
        rand = new Random();

        HBox hBox = new HBox();

        Label label = new Label("Social Network: ");
        TextField textField = new TextField();
        textField.setFocusTraversable(true);
        textField.setAccessibleText("Enter name here");

        Button button = new Button("Go!");

        button.setOnMouseClicked(m -> {
            if (!textField.getText().equalsIgnoreCase("")) {
                Label user = new Label(textField.getText());
                int x = rand.nextInt(490);
                int y = rand.nextInt(490);
                user.setLayoutX(x);
                user.setLayoutY(y);
                anchorPane.getChildren().add(user);
                users.add(user);

                user.setFocusTraversable(true);
                user.setAccessibleText(textField.getText());

                for (Label l : users) {
                    Line line = new Line(x, y, l.getLayoutX(), l.getLayoutY());
                    line.setStroke(Paint.valueOf("red"));
                    line.setOpacity(.3);
                    anchorPane.getChildren().add(line);
                }

            }
        });

        hBox.getChildren().addAll(label, textField, button);
        anchorPane.getChildren().add(hBox);

        AnchorPane.setBottomAnchor(hBox, 0d);
        AnchorPane.setLeftAnchor(hBox, 100d);
        AnchorPane.setRightAnchor(hBox, 100d);

        Scene scene = new Scene(anchorPane, 500, 500);
        stage.setTitle("Paint!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
