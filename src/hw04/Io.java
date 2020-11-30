
/* Richard Hernandez 8556435618*/

package hw04;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Io extends HBox {

    public static TextField saveTF;

    public Io() {
        this.setSpacing(2);

        Button save = new Button("save");
        save.setFont(new Font("times new roman", 12));
        save.maxHeight(20);
        save.setPrefSize(50, 20);

        save.setOnMousePressed(mp -> {

            TextField fileSave = new TextField();
            saveTF = fileSave;
            saveTF.setMaxWidth(107);
            Paint.anchorPane.getChildren().add(saveTF);
            AnchorPane.setTopAnchor(fileSave, 24d);
            AnchorPane.setRightAnchor(fileSave, 2d);

            fileSave.setOnKeyPressed( kp -> {
                if (kp.getCode().equals(KeyCode.ENTER)) {
                    try {

                        String fn = saveTF.getText();

                        if (fn.equalsIgnoreCase("")) {
                            fileSave.setText("Enter file name");
                            FadeTransition ft = new FadeTransition(Duration.millis(700), fileSave);
                            ft.onFinishedProperty().setValue( e -> fileSave.setText(""));
                            ft.play();
                            return;
                        }

                        String fileName = fn.endsWith(".txt") ? fn : fn + ".txt";

                        FileWriter pw = new FileWriter(Paint.savePath + fileName);

                        Paint.rectMap.forEach((k, v) -> Rect.saveRectangle(pw, k, v) );

                        Paint.lineMap.forEach((k, v) -> Line_.saveLine(pw, k, v));

                        pw.close();
                    } catch (IOException ignored) { }
//
                    fileSave.setText("file saved");
                    FadeTransition ft = new FadeTransition(Duration.millis(2000), fileSave);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setCycleCount(1);
                    ft.setAutoReverse(true);
                    ft.onFinishedProperty().setValue( e -> Paint.anchorPane.getChildren().remove(fileSave));
                    ft.play();

                }
            });

        });

        MenuButton load = new MenuButton("load");
        load.setFont(new Font("times new roman", 12));
        load.maxHeight(20);
        load.setPrefSize(55, 20);

        File folder = new File(Paint.loadPath);

        File[] files = folder.listFiles();

        assert files != null;
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".txt")) {

                MenuItem menuItem = new MenuItem(fileName);
                menuItem.setOnAction(click -> {
                    try {
                        File myObj = new File(Paint.loadPath + fileName);
                        Scanner myReader = new Scanner(myObj);



                        Paint.anchorPane = Paint.loadDefaultAnchorPane(new AnchorPane());

                        Paint.scene = new Scene(Paint.anchorPane, 500, 500);
                        Paint.mainStage.setScene(Paint.scene);


                        while (myReader.hasNextLine()) {

                            String[] input = myReader.nextLine().split(" ", 0);

                            if (input[0].equalsIgnoreCase("rect"))
                                Rect.loadRect(input);

                            if (input[0].equalsIgnoreCase("line"))
                                Line_.loadLine(input);
                        }
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                });

                load.getItems().add(menuItem);
            }

        }

        this.getChildren().addAll(save, load);
        this.toFront();
    }

}
