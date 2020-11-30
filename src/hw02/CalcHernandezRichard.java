package hw02;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class CalcHernandezRichard extends Application {

    BorderPane bp;
    GridPane gp;
    TextField input, output;
    VBox temperatures, weight;
    Button f2c, c2f, p2k, k2p, one, two, three, four, five, six, seven, eight, nine, decimal, zero, neg;
    Scene scene;

    @Override
    public void start(Stage stage) {

        bp = new BorderPane();										//BorderPane for general layout

        gp = new GridPane();										//GridPane for number layout
        gp.setPadding(new Insets(10));								//set styling for GridPane
        gp.setVgap(8);
        gp.setHgap(8);

        input = new TextField();									//input TextField
        input.setAlignment(Pos.CENTER);								//align text center of TextField
        bp.setTop(input);											//place at top of BorderPane

        output = new TextField();									//output TextField
        output.setAlignment(Pos.CENTER);							//align text center of TextField
        bp.setBottom(output);										//place at bottom of BorderPane

        temperatures = new VBox();									//Vbox for temperature conversion buttons
        f2c = new Button("F to C");
        c2f = new Button("C to F");

        VBox.setMargin(f2c, new Insets(10));						//set styling for temperature buttons
        VBox.setMargin(c2f, new Insets(10));

        temperatures.getChildren().addAll(f2c, c2f);				//add f2c and c2f buttons to temperature Vbox
        bp.setLeft(temperatures);									//add Vbox to left of BorderPane

        weight = new VBox();										//Vbox for weight conversion buttons
        p2k = new Button("Lb to Kg");
        k2p = new Button("Kg to Lb");

        VBox.setMargin(p2k, new Insets(10));						//set styling for weight buttons
        VBox.setMargin(k2p, new Insets(10));

        weight.getChildren().addAll(p2k, k2p);						//add p2k and k2p buttons to weight Vbox
        bp.setRight(weight);										//add Vbox to right of BorderPane

        one = new Button("1");										//Create 0-9 buttons, negative button, and decimal button
        two = new Button("2");
        three = new Button("3");
        four = new Button("4");
        five = new Button("5");
        six = new Button("6");
        seven = new Button("7");
        eight = new Button("8");
        nine = new Button("9");
        decimal = new Button(".");
        zero = new Button("0");
        neg = new Button("-");

        gp.add(one, 0, 0);											//add buttons to GridPane in appropriate positions
        gp.add(two, 1, 0);
        gp.add(three, 2, 0);
        gp.add(four, 0, 1);
        gp.add(five, 1, 1);
        gp.add(six, 2, 1);
        gp.add(seven, 0, 2);
        gp.add(eight, 1, 2);
        gp.add(nine, 2, 2);
        gp.add(decimal, 0, 3);
        gp.add(zero, 1, 3);
        gp.add(neg, 2, 3);

        bp.setCenter(gp);											//set GridPane in the center of BorderPane


        one.setOnAction(e -> input.setText(input.getText() + "1"));	//Append appropriate value to the end of input
        two.setOnAction(e -> input.setText(input.getText() + "2"));
        three.setOnAction(e -> input.setText(input.getText() + "3"));
        four.setOnAction(e -> input.setText(input.getText() + "4"));
        five.setOnAction(e -> input.setText(input.getText() + "5"));
        six.setOnAction(e -> input.setText(input.getText() + "6"));
        seven.setOnAction(e -> input.setText(input.getText() + "7"));
        eight.setOnAction(e -> input.setText(input.getText() + "8"));
        nine.setOnAction(e -> input.setText(input.getText() + "9"));
        zero.setOnAction(e -> input.setText(input.getText() + "0"));

        decimal.setOnAction(e -> {									//add a decimal, only lets you add one decimal
            if (!input.getText().contains(".")) {
                input.setText(input.getText() + ".");
            }
        });
        neg.setOnAction(e -> {										//changes from neg to pos, or pos to neg
            String tmp_input = input.getText();
            if (tmp_input.charAt(0) == '-') {
                tmp_input = tmp_input.substring(1);
            } else {
                tmp_input = "-" + tmp_input;
            }
            input.setText(tmp_input);
        });

        f2c.setOnAction(e -> {										//event handler for f -> c button
            try {
                Double f = Double.parseDouble(input.getText());
                Double c = (f - 32) * 5 / 9;
                output.setText(String.format("%.2f", c) + "C");
                input.setText("");
            } catch(Exception error) {
                input.setText("");
                output.setText("Error with input");
            }
        });

        c2f.setOnAction(e -> {										//event handler for c -> f button
            try {
                Double c = Double.parseDouble(input.getText());
                Double f = (c  * 1.8) + 32;
                output.setText(String.format("%.2f", f) + "F");
                input.setText("");
            } catch(Exception error) {
                input.setText("");
                output.setText("Error with input");
            }
        });

        p2k.setOnAction(e -> {										//event handler for p -> k button
            try {
                Double p = Double.parseDouble(input.getText());
                Double k = p / 2.205;
                output.setText(String.format("%.2f", k) + "kg");
                input.setText("");
            } catch(Exception error) {
                input.setText("");
                output.setText("Error with input");
            }
        });

        k2p.setOnAction(e -> {										//event handler for k -> p button
            try {
                Double k = Double.parseDouble(input.getText());
                Double p = k * 2.205;
                output.setText(String.format("%.2f", p) + "lbs");
                input.setText("");
            } catch(Exception error) {
                input.setText("");
                output.setText("Error with input");
            }
        });


        scene = new Scene(bp, 270, 210);							//Set scene and show

        stage.setTitle("Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { Application.launch(args); }

}
