//package hw06;
//import javafx.animation.AnimationTimer;
//import javafx.geometry.Bounds;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//
//import java.util.LinkedList;
//import java.util.Random;
//
//public class Plane extends Pane {
//
//    public static int p = 6;
//    public static int winCondition = 3;
//    long lasttime;
//    boolean firsttime = true;
//    public static Driver d;
//    public static Random r;
//    Door door;
//    public static LinkedList<Particle> particles;
//
//    public Plane(int width, int height) {
//
//        r = new Random();
//        particles = new LinkedList<>();
//
//        setWidth(width);
//        setHeight(height);
//        setStyle("-fx-background-color: black");
//
//        door = new Door(this);
//
//        addEventHandler(MouseEvent.ANY, (MouseEvent me) -> door.move(me));
//        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> door.openDoor());
//        addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent me) -> door.closeDoor());
//
//        d = new Driver();
//
//    }
//
//    public void addParticles() {
//
//        for (int i = 0; i < p; i++) {
//            Particle p;
//            if (i % 2 == 0)
//                p = new Particle(r, true);
//            else
//                p = new Particle(r, false);
//
//            particles.add(p);
//            getChildren().add(p);
//
//        }
//
//    }
//
//    public void addParticle(boolean right) {
//        Particle p = new Particle(r, right);
//        particles.add(p);
//        this.getChildren().add(p);
//        updateParticles(Plane.p++);
//    }
//
//    public class Driver extends AnimationTimer {
//        @Override
//        public void handle (long now) {
//
//            if ( firsttime ) { lasttime = now; firsttime = false;  }
//            else
//            {
//                double deltat = (now-lasttime ) * 1.0e-9;
//                lasttime = now;
//                for (Particle p : particles) {
//                    p.move(deltat);
//                    for (Wall l : door.parts) {
//
//                        Bounds bo1 = p.getBoundsInLocal();
//
//                        Bounds bo2 = l.getBoundsInLocal();
//
//                        if ( bo2.intersects(bo1)) {
//                            if (l.wall) p.bounce(deltat);
//                            else checkWon();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public static void updateParticles(int i) {
//        p = i;
//        winCondition = p/2;
//    }
//
//
//    public void  checkWon() {
//        int sum = 0;
//        int diff = 0;
//        for (Particle p : particles) {
//            if (p.getX() > 400) sum++;
//            else diff++;
//            if (sum > 0 && diff > 0) return;
//        }
//
//        if (sum <= p || diff <= p) Main06.WIN();
//
//    }
//
//    public void START() {
//        for (Particle p : particles) getChildren().remove(p);
//        particles.clear();
//
//        addParticles();
//        d = new Driver();
//        d.start();
//    }
//
//}
