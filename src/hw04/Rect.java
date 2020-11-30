
/* Richard Hernandez 8556435618*/

package hw04;

import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.IOException;

public class Rect extends Rectangle {

    public static void clickRectangle(MouseEvent m) {

        Rectangle rect = new Rectangle(Paint.mouseX, Paint.mouseY, m.getX() - Paint.mouseX, m.getY() - Paint.mouseY);
        rect.setFill(javafx.scene.paint.Paint.valueOf(Paint.selectedColor));

        if (rect.getWidth() * rect.getHeight() < 10)
            return;

        addRect(rect, "");
    }

    public static void saveRectangle(FileWriter pw, Rectangle rect, String name) {
        try {
            pw.write("rect ");
            pw.write(rect.getX() + " ");
            pw.write(rect.getY() + " ");
            pw.write(rect.getWidth() + " ");
            pw.write(rect.getHeight() + " ");
            pw.write(rect.getFill().toString() + " ");
            pw.write(name + "\n");
            pw.flush();
        } catch (IOException ignored) {}
    }

    public static void loadRect(String[] input) {
        double x = java.lang.Double.parseDouble(input[1]);
        double y = java.lang.Double.parseDouble(input[2]);
        double w = java.lang.Double.parseDouble(input[3]);
        double h = java.lang.Double.parseDouble(input[4]);
        String loadColor = input[5];
        String name = input.length == 7 ? input[6] : " ";

        Rectangle rect = new Rectangle(x, y, w, h);
        rect.setFill(javafx.scene.paint.Paint.valueOf(loadColor));

        addRect(rect, name);
    }

    private static void addRect(Rectangle rect, String name) {

        Paint.rectMap.put(rect, name);

        Label label = new Label(name);
        double labelX = (rect.getX() + .5 * rect.getWidth() - .25 * label.getFont().getSize() * label.getText().length());
        double labelY = (rect.getY() + rect.getHeight());
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);

        TextField textField = new TextField();
        textField.setLayoutX(rect.getX());
        textField.setLayoutY(labelY);
        textField.setMaxWidth(rect.getWidth());
        textField.setMinWidth(50);
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
                double lx = (rect.getX() + .5 * rect.getWidth() - .25 * label.getFont().getSize() * label.getText().length());
                double ly = (rect.getY() + rect.getHeight());
                label.setLayoutX(lx);
                label.setLayoutY(ly);
                label.setVisible(true);
                Paint.rectMap.put(rect, label.getText());
            }
        });

        textField.focusedProperty().addListener((obj, wasFocused, isFocused) -> {
            if (isFocused) {
                double shadowWidth = Math.max(rect.getHeight(), rect.getWidth()) * .2;
                javafx.scene.paint.Color color = Paint.selectedColor.equals("black") ? javafx.scene.paint.Color.FUCHSIA : javafx.scene.paint.Color.valueOf(Paint.selectedColor).invert();
                rect.setEffect(new DropShadow(shadowWidth, color));
                textField.setVisible(true);
                textField.setEffect(new DropShadow(shadowWidth, color));
            }
            if (wasFocused) {
                textField.setVisible(false);
                label.setText(textField.getText());
                label.setVisible(true);
                label.toFront();
                Paint.rectMap.put(rect, label.getText());
            }
        });

        rect.focusedProperty().addListener((obj, wasFocused, isFocused) -> {
            if (isFocused) {
                double shadowWidth = Math.max(rect.getHeight(), rect.getWidth()) * .2;
                javafx.scene.paint.Color color = Paint.selectedColor.equals("black") ? javafx.scene.paint.Color.FUCHSIA : javafx.scene.paint.Color.valueOf(Paint.selectedColor).invert();
                rect.setEffect(new DropShadow(shadowWidth, color));
                if (label.getText().equals("")) {
                    textField.setVisible(true);
                    double sw = Math.max(rect.getHeight(), rect.getWidth()) * .2;
                    javafx.scene.paint.Color cl = Paint.selectedColor.equals("black") ? javafx.scene.paint.Color.FUCHSIA : Color.valueOf(Paint.selectedColor).invert();
                    textField.setEffect(new DropShadow(sw, cl));
                }
            }
            if (wasFocused) {
                rect.setEffect(null);
                textField.setVisible(false);
                label.setText(Paint.rectMap.get(rect));
                label.toFront();
            }
        });

        rect.setOnMousePressed(me -> {
            Paint.fShape = rect;
            rect.requestFocus();
            Paint.rectOffsetX = Math.abs(rect.getX() - me.getX());
            Paint.rectOffsetY = Math.abs(rect.getY() - me.getY());
        });

        rect.setOnMouseDragged(me -> {
            rect.setX(me.getX() - Paint.rectOffsetX);
            rect.setY(me.getY() - Paint.rectOffsetY);
            label.setLayoutX(rect.getX() + .5 * rect.getWidth() - .25 * label.getFont().getSize() * label.getText().length());
            label.setLayoutY(rect.getY() + rect.getHeight());
            textField.setLayoutX(rect.getX());
            textField.setLayoutY(rect.getY() + rect.getHeight());
        });

        rect.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.BACK_SPACE)) {
                Paint.anchorPane.getChildren().removeAll(rect, textField, label);
                Paint.rectMap.remove(rect);
            }
        });

        Paint.anchorPane.getChildren().addAll(rect, label, textField);

        textField.toBack();
        label.toFront();
        rect.toBack();
    }
}
