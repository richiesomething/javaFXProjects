
/* Richard Hernandez 8556435618*/

package hw04;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.FileWriter;
import java.io.IOException;

public class Line_ extends Line {

    public static void clickLine(MouseEvent m) {

        Line line = new Line(Paint.mouseX, Paint.mouseY, m.getX(), m.getY());

        if (lineLength(line) < 10)
            return;

        line.setStrokeWidth(3);
        line.setStroke(javafx.scene.paint.Paint.valueOf(Paint.selectedColor));

        addLine(line, "");
    }

    public static void saveLine(FileWriter pw, Line line, String name) {
        try {
            pw.write("line ");
            pw.write(line.getStartX() + " ");
            pw.write(line.getStartY() + " ");
            pw.write(line.getEndX() + " ");
            pw.write(line.getEndY() + " ");
            pw.write(line.getStroke().toString() + " ");
            pw.write(name + "\n");
            pw.flush();
        } catch (IOException ignored) {}
    }

    public static void loadLine(String[] input) {

        double startX = Double.parseDouble(input[1]);
        double startY = Double.parseDouble(input[2]);
        double endX = Double.parseDouble(input[3]);
        double endY = Double.parseDouble(input[4]);
        String loadColor = input[5];
        String name = input.length == 7 ? input[6] : "";

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(javafx.scene.paint.Paint.valueOf(loadColor));
        line.setStrokeWidth(3);

        addLine(line, name);
    }

    public static void addLine(Line line, String name) {

        Paint.lineMap.put(line, name);

        Circle top = new Circle(line.getStartX(), line.getStartY(), 5);
        Circle bottom = new Circle(line.getEndX(), line.getEndY(), 5);

        top.setFill(Color.TRANSPARENT);
        bottom.setFill(Color.TRANSPARENT);

        Label label = new Label(name);
        label.setLayoutX(line.getEndX() + 25);
        label.setLayoutY(line.getEndY());
        label.setStyle("-fx-background-color: white");
        label.toFront();

        TextField textField = new TextField();
        textField.setLayoutX(line.getEndX() + 25);
        textField.setLayoutY(line.getEndY());
        textField.setMinWidth(50);
        textField.setMaxWidth(80);
        textField.setVisible(false);

        label.setOnMouseClicked(mc -> {
            label.setVisible(false);
            textField.setText(label.getText());
            textField.setVisible(true);
            textField.requestFocus();
        });

        textField.setOnKeyPressed(kp -> {
            if (kp.getCode().equals(KeyCode.ENTER)) {
                label.setText(textField.getText());
                textField.setVisible(false);
                label.setVisible(true);
                Paint.lineMap.put(line, label.getText());
            }
        });

        textField.focusedProperty().addListener((obj, wasFocused, isFocused) -> {
            if (isFocused) {
                textField.setVisible(true);
                Color color = Paint.selectedColor.equals("black") ? Color.FUCHSIA : Color.valueOf(Paint.selectedColor).invert();
                textField.setEffect(new DropShadow(30, color));
            }
            if (wasFocused) {
                label.setText(textField.getText());
                textField.setVisible(false);
                label.setVisible(true);
                bottom.toFront();
                Paint.lineMap.put(line, label.getText());
            }
        });

        Paint.anchorPane.getChildren().addAll(line, top, bottom, label, textField);

        top.setOnMouseDragged(me -> {
            line.setStartX(me.getX());
            line.setStartY(me.getY());
            top.setCenterX(me.getX());
            top.setCenterY(me.getY());
        });

        bottom.setOnMouseDragged(me -> {
            line.setEndX(me.getX());
            line.setEndY(me.getY());
            bottom.setCenterX(me.getX());
            bottom.setCenterY(me.getY());
            label.setLayoutX(line.getEndX() + 25);
            label.setLayoutY(line.getEndY());
            textField.setLayoutX(line.getEndX() + 25);
            textField.setLayoutY(line.getEndY());
        });

        bottom.setOnMouseReleased(mr -> bottom.toFront());

        line.setOnMousePressed(me -> {
            Paint.fShape = line;
            line.requestFocus();
        });

        line.focusedProperty().addListener((obj, wasFocused, isFocused) -> {
            if (isFocused) {
                Color color = Paint.selectedColor.equals("black") ? Color.FUCHSIA : Color.valueOf(Paint.selectedColor).invert();
                top.setFill(color);
                bottom.setFill(color);
                label.setEffect(new DropShadow(50, color));
                if (label.getText().equals("")) {
                    textField.setVisible(true);
                    Color cl = Paint.selectedColor.equals("black") ? Color.FUCHSIA : Color.valueOf(Paint.selectedColor).invert();
                    textField.setEffect(new DropShadow(50, cl));
                }
                bottom.toFront();
            }
            if (wasFocused) {
                top.setFill(Color.TRANSPARENT);
                bottom.setFill(Color.TRANSPARENT);
                label.setText(Paint.lineMap.get(line));
                label.setEffect(null);
                textField.setVisible(false);
                textField.setEffect(null);
            }
        });

        line.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.BACK_SPACE))
                Paint.anchorPane.getChildren().removeAll(line, top, bottom, textField, label);
        });

        textField.toBack();
        label.toFront();
        line.toBack();
    }

    public static double lineLength(Line line) {

        double startX = line.getStartX();
        double startY = line.getStartY();
        double endX = line.getEndX();
        double endY = line.getEndY();

        double lenX = Math.pow((startX - endX), 2);
        double lenY = Math.pow((startY - endY), 2);

        return Math.sqrt(lenX + lenY);

    }

}
