package hw06;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Particle extends Rectangle {

   boolean right;
   double vx = 100; // pix per second
   double vy = 100;
   public static int speed = 300;
   protected final static int hw = 10; // size of ball
   public static int getHW() { return hw; }
   Random r;
   public  int leftSpace;
   public  int rightSpace;
   public  int heightSpace;

   public Particle( Random r1, boolean l_or_r) {
      super( hw, hw );

      heightSpace = 400;
      r=r1;

      setFill(Paint.valueOf("blue"));

      if (l_or_r) {
         leftSpace = 0; rightSpace = 800;
         setX(450 + r.nextInt(350));
         setY(50 + r.nextInt(350));
      } else {
         leftSpace = 0; rightSpace = 800;
         setX(r.nextInt(350));
         setY(r.nextInt(350));
      }
   }

   public void move( double deltat )
   {
      double x = getX();
      x += vx * deltat;
      setX(x);
      double y = getY();
      y += vy * deltat;
      setY(y);

      if (x < 400) right = false;
      else right = true;

      if ( y>heightSpace - hw ) { vy = -r.nextInt(speed/2) - hw; }
      if ( x>rightSpace - hw ) { vx = -r.nextInt(speed/2) - hw; }
      if ( x<0 ) { vx = r.nextInt(speed) + hw; }
      if ( y<0 ) { vy = r.nextInt(speed) + hw; }
   }

   public void bounce(double deltat) {

      double x = getX();
      x += vx * deltat;
      setX(x);
      double y = getY();
      y += vy * deltat;
      setY(y);

      if (right)
         vx = r.nextInt(speed) + hw;
      else
         vx = -r.nextInt(speed) + hw;

      right = !right;
   }
}
