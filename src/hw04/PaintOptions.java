
/* Richard Hernandez 8556435618*/

package hw04;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;

public class PaintOptions extends VBox {

    static GridPane gridPane;
    static List<String> colors = Arrays.asList("#ff0000", "#ff5349", "#ffa500", "#f8d568", "#ffff00", "#9acd32", "#008000", "#1164b4", "#0000ff", "#8a2be2", "#800080", "black");


    public PaintOptions() {

        this.setPadding(new Insets(8));
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER);

        Button line = new Button("line");
        Button rec = new Button("rectangle");

        line.setPrefSize(100, 30);
        line.setFont(new Font("times new roman", 18));
        line.setOnMouseClicked(m -> {
            Paint.selectedShape = "line";
            line.setEffect(new InnerShadow(0, -3, -3, Color.BLACK));
            rec.setEffect(null);
        });

        rec.setPrefSize(100, 30);
        rec.setFont(new Font("times new roman", 18));
        rec.setOnMouseClicked(m -> {
            Paint.selectedShape = "rectangle";
            rec.setEffect(new InnerShadow(0, -3, -3, Color.BLACK));
            line.setEffect(null);
        });

        gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(6);
        int color_idx = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                addColor(colors.get(color_idx++), i, j);

        this.getChildren().add(gridPane);


        this.setStyle("-fx-background-color: gray");
        this.getChildren().addAll(line, rec);

    }

    public static void addColor(String hexColor, int i, int j) {
        Button colorButton = new Button();
        colorButton.setStyle("-fx-background-color: " + hexColor + "; -fx-border-color: black");
        colorButton.setId(hexColor);
        colorButton.setOnMouseClicked(m -> {

            Paint.selectedColor = hexColor;
            if (Paint.fShape instanceof Line)
                Paint.fShape.setStroke(javafx.scene.paint.Paint.valueOf(hexColor));
            if (Paint.fShape instanceof Rectangle)
                Paint.fShape.setFill(javafx.scene.paint.Paint.valueOf(hexColor));

            for (Node n : gridPane.getChildren()) {
                Button clr = (Button) n;
                if (clr.getId().equals(Paint.selectedColor))
                    clr.setEffect(new DropShadow(10, 4, 4, Color.BLACK));
                else
                    clr.setEffect(null);
            }
        });
        gridPane.add(colorButton, i, j);
        Paint.selectedColor = hexColor;
    }


}
