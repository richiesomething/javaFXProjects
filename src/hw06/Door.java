//package hw06;
//
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Line;
//
//import java.util.LinkedList;
//
//public class Door extends Line {
//
//    boolean opened;
//    double size;
//    Wall upper, mid, lower;
//    public LinkedList<Wall> parts;
//    Plane plane;
//
//    public Door(Plane p) {
//
//        plane = p;
//
//        size = p.getHeight();
//
//        upper = PartialLine(10, "yellow", true);
//
//        mid = PartialLine(10, "red", false);
//
//        lower = PartialLine(10, "yellow", true);
//
//        parts = new LinkedList<>();
//        parts.add(upper);
//        parts.add(mid);
//        parts.add(lower);
//
//        addDoor();
//
//        p.getChildren().addAll(upper, mid, lower);
//
//    }
//
//    public Wall PartialLine(int w, String color, boolean wall) {
//        Wall line = new Wall(wall);
//        line.setStrokeWidth(w);
//        line.setStroke(Paint.valueOf(color));
//        return line;
//    }
//
//    public void addDoor() {
//        double height = plane.getHeight();
//        double width = plane.getWidth() / 2;
//
//        upper.setStartX(width);
//        upper.setStartY(0);
//
//        upper.setEndX(width);
//        upper.setEndY(height * .4);
//
//
//        mid.setStartX(width);
//        mid.setStartY(height * .4);
//
//        mid.setEndX(width);
//        mid.setEndY(height * .6);
//
//
//        lower.setStartX(width);
//        lower.setStartY(height * .6);
//
//        lower.setEndX(width);
//        lower.setEndY(height);
//
//    }
//
//    public void move(MouseEvent me) {
//
//        double y = me.getY();
//
//        if (y < 50) {
//            mid.setStartY(0);
//            mid.setEndY(100);
//        } else if (y > 350) {
//            mid.setStartY(300);
//            mid.setEndY(400);
//        } else {
//            mid.setStartY(me.getY() - 50);
//            mid.setEndY(me.getY() + 50);
//        }
//
//        upper.setEndY(mid.getStartY());
//        lower.setStartY(mid.getEndY());
//
//    }
//
//    public void openDoor() {
//        opened = !opened;
//        mid.wall = false;
//        mid.setStroke(Paint.valueOf(opened ? "black" : "red"));
//    }
//
//    public void closeDoor() {
//        opened = !opened;
//        mid.wall = true;
//        mid.setStroke(Paint.valueOf(opened ? "black" : "red"));
//    }
//}
