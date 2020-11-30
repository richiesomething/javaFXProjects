package labs;

/*
 * Access Lab ... YouBet.  Can you enhance for access?
  1. attach 'first name' and 'bet' labels to their fields, so when you
  are focused on the text, it tells you what it's for
  2. make these say more, so it says "please enter your name" and
   "enter the amount to bet in dollars" and perhaps more on the bet button.
  3. make the balance and winnings Labels focus-traversable
  4. make the name field NOT focus traversable after you enter your name.

 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.event.FocusEvent;

public class YouBet extends Application
{
    VBox root; // all the stuff in the window attaches here
    double balance = 100; // dollars you have left
    Font font;
    TextField fnText;
    Label balanceLabel;
    Label winLabel;

    public static void main( String[] args )
    { launch(args); }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("You Bet");
        root = new VBox();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();

        font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);

        HBox hb1 = new HBox();
        root.getChildren().add(hb1);
        Label fnLabel = new Label("first name"); fnLabel.setFont(font);

        fnLabel.setLabelFor(fnText);
        fnLabel.setFocusTraversable(true);
        fnLabel.setAccessibleText("please enter your name");

        hb1.getChildren().add(fnLabel);
        fnText = new TextField(); fnText.setFont(font);
        hb1.getChildren().add(fnText);

        fnText.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (wasFocused) {
                System.out.println(fnText.getText());
                if (fnText.getText().equalsIgnoreCase(""))
                    fnText.setFocusTraversable(true);
                else
                    fnText.setFocusTraversable(false);
            }
        });
        /*
        HBox hb2 = new HBox();
        root.getChildren().add(hb2);
        Label lnLabel = new Label("last name"); lnLabel.setFont(font);
        hb2.getChildren().add(lnLabel);
        TextField lnText = new TextField(); lnText.setFont(font);
        hb2.getChildren().add(lnText);
        */

        balanceLabel = new Label("balance $"+balance);
        root.getChildren().add( balanceLabel );
        balanceLabel.setFont(font);

        HBox hb3 = new HBox();
        root.getChildren().add(hb3);
        Label betLabel = new Label("bet"); betLabel.setFont(font);
        hb3.getChildren().add(betLabel);
        TextField betText = new TextField(); betText.setFont(font);

        betLabel.setLabelFor(betText);
        betLabel.setFocusTraversable(true);
        betLabel.setAccessibleText("Enter the amount to bet in dollars.");

        hb3.getChildren().add(betText);

        Button betButton = new Button("make the bet");
        root.getChildren().add(betButton);
        betButton.setFont(font);

        winLabel = new Label("no winnings yet");
        root.getChildren().add(winLabel);
        winLabel.setFont(font);

        betButton.setOnAction
                (  (ActionEvent e)->
                        {
                            double bet = Double.parseDouble( betText.getText() );
                            if (bet>0 && bet<=balance)
                            {
                                double win = 0;
                                String opword = "";
                                if ( Math.random()>0.5 )
                                {
                                    setBalance( balance+bet);
                                    opword = "won";
                                }
                                else
                                {
                                    setBalance( balance-bet);
                                    opword = "lost";
                                }
                                String name = fnText.getText();
                                winLabel.setText(name+", you "+opword+" $"+bet);
                            }
                        }
                );
    }
    public void setBalance( double b )
    {
        balance = b;
        balanceLabel.setText("balance $"+b);
    }
}