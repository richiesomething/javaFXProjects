package hw05;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeftVBox extends VBox {

    static Label edgeLB, minLB, maxLB, redLB, blueLB, emptyLB;
    static TextField edgeTF, minTF, maxTF, redTF, blueTF;
    static Button singleStep, runButton, resetButton, faster, slower;
    static HBox edgeHB, controlsHB, minHB, maxHB, redHB, blueHB, resetHB;

    static boolean started, running;

    Driver driver;
    static long sleep = 200;

    public LeftVBox() {

        super();

        setWidth(200);
        setPadding(new Insets(0, 10, 0, 0));
        setSpacing(3);

        driver = new Driver();

        setupControlUI();
    }

    public void setupControlUI() {

        edgeLB = makeLabel("City edge length");
        edgeTF = makeTextField("40");
        edgeHB = makeHBox(edgeLB, edgeTF);
        edgeTF.textProperty().addListener(
                (obj, oldText, newText) -> Neighborhood.nSize = newText.equals("") ? 0 : Integer.parseInt(newText)
        );



        singleStep = makeButton("Start", e-> {

            if (!started) {
                started = true;

                int size  = Integer.parseInt(edgeTF.getText());
                int min   = Integer.parseInt(minTF.getText());
                int max   = Integer.parseInt(maxTF.getText());
                int pRed  = Integer.parseInt(redTF.getText());
                int pBlue = Integer.parseInt(blueTF.getText());

                Main.nb = new Neighborhood(size, min, max, pRed, pBlue);

                if (Neighborhood.nSize == 0) return;

                Main.ap.getChildren().add(Main.nb);

                AnchorPane.setTopAnchor(Main.nb, 0d);
                AnchorPane.setRightAnchor(Main.nb, 0d);
                AnchorPane.setBottomAnchor(Main.nb, 0d);

                singleStep.setText("Step");

                edgeTF.setDisable(true);
                redTF.setDisable(true);
                blueTF.setDisable(true);
                faster.setDisable(false);
                slower.setDisable(false);

            }

            else Neighborhood.step();

        });

        faster = makeButton("f", e-> sleep-= 100);
        faster.setDisable(true);

        slower = makeButton("s", e -> sleep += 100);
        slower.setDisable(true);

        runButton = makeButton("Run", e-> {

            if (!started) return;

            if (running) {
                driver.stop();
                running = false;
                runButton.setText("Run");
            }
            else {
                driver.start();
                running = true;
                runButton.setText("Stop");
            }
        });

        controlsHB = new HBox();
        controlsHB.setSpacing(3);
        controlsHB.setAlignment(Pos.BASELINE_RIGHT);
        controlsHB.getChildren().addAll(slower, faster, runButton);

        minLB = makeLabel("minimum other");
        minTF = makeTextField("0");
        minHB = makeHBox(minLB, minTF);
        minTF.textProperty().addListener(
                (obj, oldText, newText) -> {
                    int min = newText.equals("") ? 0 : Math.max(Integer.parseInt(newText), 0);
                    int max = Integer.parseInt(maxTF.getText());
                    if (min > max) min = max - 1;
                    Neighborhood.minOther = min;
                    minTF.setText(String.valueOf(min));
                }
        );

        maxLB = makeLabel("maximum other");
        maxTF = makeTextField("20");
        maxHB = makeHBox(maxLB, maxTF);
        maxTF.textProperty().addListener(
                (obj, oldText, newText) -> {
                    int max = newText.equals("") ? 0 : Math.min(Integer.parseInt(newText), 100);
                    int min = Integer.parseInt(minTF.getText());
                    if (max < min) max = min + 1;
                    Neighborhood.maxOther = max;
                    maxTF.setText(String.valueOf(max));
                }
        );

        redLB = makeLabel("percent red");
        redTF = makeTextField("40");
        redHB = makeHBox(redLB, redTF);
        redTF.textProperty().addListener(
                (obj, oldText, newText) -> {
                    int red = newText.equals("") ? 0 : Integer.parseInt(newText);
                    int blue = Integer.parseInt(blueTF.getText());
                    int i = blue + red > 100 ? 100 - blue : red;
                    Neighborhood.percentRed = i;
                    redTF.setText(String.valueOf(i));
                }
        );

        blueLB = makeLabel("percent blue");
        blueTF = makeTextField("40");
        blueHB = makeHBox(blueLB, blueTF);
        blueTF.textProperty().addListener(
                (obj, oldText, newText) -> {
                    int blue = newText.equals("") ? 0 : Integer.parseInt(newText);
                    int red = Integer.parseInt(redTF.getText());
                    int i = blue + red > 100 ? 100 - red : blue;
                    Neighborhood.percentBlue = i;
                    blueTF.setText(String.valueOf(i));
                }
        );

        emptyLB = makeLabel("empty: 20%");
        resetButton = makeButton("Reset", e -> reset());
        resetButton.setPrefWidth(50);
        resetButton.setAlignment(Pos.BASELINE_RIGHT);

        resetHB = new HBox();
        resetHB.setAlignment(Pos.BASELINE_RIGHT);
        resetHB.getChildren().addAll(emptyLB, resetButton);

        getChildren().addAll(edgeHB, singleStep, controlsHB, minHB, maxHB, redHB, blueHB, resetHB);
        setStyle("-fx-background-color: yellow");
        setAlignment(Pos.BASELINE_RIGHT);

    }

    public Label makeLabel(String title) {
        Label l = new Label(title);
        l.setPadding(new Insets(0, 10, 0, 10));
        return l;
    }

    public TextField makeTextField(String title) {
        TextField tf = new TextField(title);
        tf.setPrefWidth(50);
        tf.setAlignment(Pos.BASELINE_RIGHT);
        return tf;
    }

    public Button makeButton(String s, EventHandler<ActionEvent> h ) {
        Button b = new Button(s);
        b.setOnAction(h);
        b.setAlignment(Pos.CENTER);
        b.setPrefWidth(50);
        return b;
    }

    public HBox makeHBox(Label lb, TextField tf) {

        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_RIGHT);
        hb.getChildren().addAll(lb, tf);

        return hb;
    }

    public static class Driver extends AnimationTimer {

        @Override
        public void handle( long now ) {

            sleep = sleep > 500 ? 500 : sleep < 0 ? 0 : sleep;

            try {
                Thread.sleep(sleep);
                Neighborhood.step();

            } catch (InterruptedException ignored) {}

        }
    }

    public void reset() {

        Main.ap.getChildren().remove(Main.nb);

        driver.stop();

        edgeTF.setText("40");
        minTF.setText("0");
        maxTF.setText("20");
        redTF.setText("40");
        blueTF.setText("40");

        started = false;
        running = false;

        edgeTF.setDisable(false);
        redTF.setDisable(false);
        blueTF.setDisable(false);
        faster.setDisable(true);
        slower.setDisable(true);

        singleStep.setText("Start");
        runButton.setText("Run");

    }

}
