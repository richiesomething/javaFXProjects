//package hw06;
//
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//
//public class Setting extends HBox {
//
//    public static Button slow, med, fast, start, left, right;
//    TextField sTF;
//    Label btnLabel, sLabel, add;
//
//    public Setting() {
//
//        slow = makeButton("slow", "green", 100);
//        med = makeButton("med", "yellow", 200);
//        fast = makeButton("fast", "red", 300);
//        start = makeButton("start", "gray", 0);
//
//        btnLabel = new Label("Speed: ");
//        sLabel = new Label("Particles: ");
//        sTF = new TextField("6");
//        sTF.setPrefWidth(50);
//        sTF.textProperty().addListener((obj, old, nw) -> {
//            int p = Integer.parseInt(nw.equals("") ? "0" : nw);
//
//            Plane.updateParticles(p);
//        });
//
//
//        add = new Label("Add: ");
//        left = new Button("Left");
//        left.setOnMouseClicked(m -> Main06.plane.addParticle(false) );
//
//        right = new Button("Right");
//        right.setOnMouseClicked(m -> Main06.plane.addParticle(true) );
//
//        setSpacing(10);
//
//        getChildren().addAll(btnLabel, slow, med, fast, add, left, right, sLabel, sTF, start);
//    }
//
//    public Button makeButton(String speed, String color, int dif) {
//        Button b = new Button(speed);
//        b.setStyle("-fx-background-color: " + color);
//
//        if (dif > 0)
//            b.setOnMouseClicked((e) -> Particle.speed = dif);
//
//        else
//            b.setOnMouseClicked((e) -> Main06.START());
//
//        return b;
//    }
//
//}
