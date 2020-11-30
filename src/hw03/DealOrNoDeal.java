package hw03;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class DealOrNoDeal extends Application{

    Stage mainStage;
    Scene scene;
    BorderPane borderPane, backgroundPane;
    GridPane suitcasesPane, valuesPane;

    int playersSuitcase = 0;
    List<Integer> suitcauseValues = Arrays.asList(1, 5, 10, 100, 1000, 5000, 10000, 100000, 500000, 1000000);
    Set<Integer> set = new HashSet<>(suitcauseValues);

    @Override
    public void start(Stage stage) {

        backgroundPane = new BorderPane();
        backgroundPane.setId("background");

        mainStage = stage;
        borderPane = new BorderPane();
        suitcasesPane = new GridPane();
        valuesPane = new GridPane();

        // add banner
        InputStream inStream = getClass().getResourceAsStream("deal_or_no_deal.png");
        Image imageObject = new Image(inStream);
        ImageView image = new ImageView(imageObject);
        image.fitWidthProperty().bind(backgroundPane.widthProperty());
        backgroundPane.setTop(image);

        InputStream inStream1 = getClass().getResourceAsStream("hoast.png");
        Image imageObject1 = new Image(inStream1);
        ImageView image1 = new ImageView(imageObject1);
        backgroundPane.setLeft(image1);

        suitcasesPane.setPadding(new Insets(30));
        suitcasesPane.setVgap(10);
        suitcasesPane.setHgap(10);


        // add suitcases buttons to the suitcases pane
        int suitcaseNum = 1;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 5; j++)
                makeButton(suitcaseNum++, i, j);


        // center suitcasePane and add it to borderPane
        suitcasesPane.setAlignment(Pos.TOP_CENTER);
        borderPane.setTop(suitcasesPane);

          //make the labels that will display the remaining suitcases
        int values_idx = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 5; j++)
                makeLabels(values_idx++, i, j);

        // canter labels and add it to borderPane
        valuesPane.setAlignment(Pos.TOP_CENTER);
        borderPane.setCenter(valuesPane);


        backgroundPane.setCenter(borderPane);
        scene = new Scene(backgroundPane, 1200, 800);

//        // add css style for
        URL url = this.getClass().getResource("style.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        Collections.shuffle(suitcauseValues);

        stage.setTitle("Deal or No Deal!");
        stage.setScene(scene);
        stage.show();
    }

    public void makeButton(int suitcaseNum, int i, int j) {

        // create and format button
        Button button = new Button(Integer.toString(suitcaseNum));

        /* on button click determine if first suitcase
            assign to first
           else calculate offer
         */
        button.setOnAction(e -> { pickSuitcase(button); });

        // handle suitcase being picked by clicking enter
        button.setOnKeyPressed( (KeyEvent ke) -> { if (ke.getCode() == KeyCode.ENTER) pickSuitcase(button); } );
        suitcasesPane.add(button, j, i);
    }

    public void makeLabels(int values_idx, int i, int j) {
        String value = Integer.toString(suitcauseValues.get(values_idx));
        Label label = new Label(value);
        label.setAlignment(Pos.CENTER);
        valuesPane.add(label, i, j);
    }

    public void pickSuitcase(Button button) {
        int sc_idx = Integer.parseInt(button.getText()) - 1;
        int sc_val = suitcauseValues.get(sc_idx);

        //hide and disable suitcase
        button.setOpacity(0);
        button.setDisable(true);

        // if suitcase is first choosen it belongs to the player
        if (playersSuitcase == 0) {
            set.remove(sc_val);
            playersSuitcase = sc_val;

        } else {
            // onclick change color of suitcase values
            highlightValue(valuesPane, sc_val, set);

            // offer
            double offer = calculateOffer(set);
            presentOffer(offer);
        }
    }

    public void highlightValue(GridPane gridPane, int val, Set<Integer> set) {

        for (Node node : gridPane.getChildren()) {
            Label label = (Label) node;
            int value = Integer.parseInt(label.getText());
            if (value == val) {
                label.setStyle("-fx-background-color: darkgoldenrod; -fx-border-color: black");
                set.remove(val);
            }

        }

    }

    private double calculateOffer(Set<Integer> set) {
        int sum = 0;
        int setSize = set.size();
        for (Integer i : set)
            sum += i;

        try {
            return .9 * (sum / setSize);
        } catch (Exception e) {
            System.out.println("caught exception");
            return playersSuitcase;
        }

    }

    private void presentOffer(double offer) {


        //base container to store offer
        HBox offerPane = new HBox();
        offerPane.setAlignment(Pos.CENTER);
        offerPane.setId("offerPane");

        // New window (Stage)
        Scene offerScene = new Scene(offerPane, 600, 400);
        URL url = this.getClass().getResource("style.css");
        String css = url.toExternalForm();
        offerScene.getStylesheets().add(css);

        // second stage for offer
        Stage newWindow = new Stage();
        newWindow.setScene(offerScene);
        newWindow.setTitle("You have an offer!");

        // Set position of second window, related to primary window.
        newWindow.setX(mainStage.getX() + 450);
        newWindow.setY(mainStage.getY() + 250);
        newWindow.show();
        newWindow.setOnCloseRequest(e -> {
            borderPane.getTop().setDisable(false);
        });


        Label o = new Label("Offer");
        o.setMinWidth(300);
        o.setMinHeight(100);
        o.setAlignment(Pos.CENTER);
        o.setFont(new Font("Arial", 36));
        o.setStyle("-fx-border-color: black");

        Label offerValue = new Label(String.format("$%.2f", offer));
        offerValue.setMinWidth(300);
        offerValue.setMinHeight(100);
        offerValue.setAlignment(Pos.CENTER);
        offerValue.setFont(new Font("Arial", 36));
        offerValue.setStyle("-fx-border-color: black");

        Button deal = new Button("Deal");
        deal.setId("dealButton");
        deal.setOnAction(e -> {
            o.setText("Winnings!");
        });

        Button noDeal = new Button("No Deal");
        noDeal.setId("noDealButton");
        noDeal.setOnAction(e -> {
            borderPane.getTop().setDisable(false);
            newWindow.close();
        });

        VBox offerBox = new VBox();
        offerBox.setAlignment(Pos.CENTER);
        offerBox.getChildren().add(o);
        offerBox.getChildren().add(offerValue);
        offerBox.setId("offerBox");

        offerPane.getChildren().addAll(deal, offerBox, noDeal);

        offerScene.setOnKeyPressed(
                (KeyEvent ke) -> {
                    if (ke.getCode() == KeyCode.D) {
                        o.setText("Winnings!");
                    }
                    else if (ke.getCode() == KeyCode.N) {
                        borderPane.getTop().setDisable(false);
                        newWindow.close();
                    }
                }
        );

        if (offer == playersSuitcase || set.isEmpty()) {
            o.setText("Winnings!");
            offerPane.getChildren().remove(deal);
            offerPane.getChildren().remove(noDeal);
        }

        borderPane.getTop().setDisable(true);

    }

    public static void main(String[] args) { Application.launch(args); }

}
