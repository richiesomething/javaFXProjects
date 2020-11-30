//package hw06;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Paint;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//public class Main06 extends Application {
//
//    public static AnchorPane ap;
//    public static HBox won;
//    public static Setting settings;
//    public static Plane plane;
//
//    @Override
//    public void start(Stage stage) {
//
//        ap = new AnchorPane();
//        plane = new Plane(800, 400);
//        settings = new Setting();
//        won = wonHbox();
//
//        ap.getChildren().addAll(plane, settings, won);
//
//        AnchorPane.setTopAnchor(plane, 0d);
//        AnchorPane.setRightAnchor(plane, 0d);
//        AnchorPane.setLeftAnchor(plane, 0d);
//        AnchorPane.setBottomAnchor(plane, 50d);
//        AnchorPane.setBottomAnchor(settings, 10d);
//        AnchorPane.setLeftAnchor(settings, 20d);
//        AnchorPane.setBottomAnchor(won, 0d);
//        AnchorPane.setRightAnchor(won, 0d);
//
//        Scene scene = new Scene(ap, 800, 450);
//        stage.setTitle("Demon");
//        stage.setScene(scene);
//        stage.show();
//
//
//    }
//
//    public HBox wonHbox() {
//        Label l = new Label("Congratulations!");
//        l.setTextFill(Paint.valueOf("Red"));
//        l.setFont(new Font("Arial", 24));
//
//        HBox hBox = new HBox();
//        hBox.getChildren().add(l);
//        hBox.setVisible(false);
//
//        hBox.setPadding(new Insets(10, 10, 10, 10));
//
//        return hBox;
//    }
//
//
//    public static void WIN() {
//        won.setVisible(true);
//    }
//
//    public static  void START() {
//        won.setVisible(false);
//        plane.START();
//    }
//
//    public static void main(String[] args) { Application.launch(args); }
//}
