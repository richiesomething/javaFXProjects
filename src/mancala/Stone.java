package mancala;

import javafx.scene.shape.Circle;

public class Stone extends Circle
{
   private Pit myPit;
   
   public Stone(  Pit p )
   {
	   super(4);
	   myPit = p;

   }

   // returns location of kth stone relative to Pit/Pane
   // stone locations are numbered, 0 in the middle, then a
   // ring of size 'hop', then a ring of size 2*hop ... .
   static public Point location(int k )
   {
  	 //double cx = myPit.getTheCircle().getCenterX();
  	 //double cy = myPit.getTheCircle().getCenterY();
  	 //System.out.println("circle x is "+cx);
  	 
  	 int level=4; // 0 center, 1 inner circle, 2 next layer out ...
  	 double wedge=0.3; // angular jump between stones at this level
  	 int hop = 11; // space between layers
  	 if      ( k==0 )        { level = 0; wedge = 0; }
  	 else if (1<=k && k<8 )  { level = 1; wedge = 0.9; }
  	 else if (8<=k && k<21)  { level = 2; wedge = 0.48; }
  	 else if (21<=k && k<38 ){ level = 3; wedge = 0.3; }
	     //Point p = new Point();
	 
		 double angle = k * wedge;
		 double rx = level * hop * Math.cos(angle);
		 double ry = level * hop * Math.sin(angle);
		 //p.setX( cx + rx );
		 //p.setY( cy + ry );
		 
		 Point p = new Point( rx,ry);
	 
	     return p;
   }

   // returns the location within the window
   // global coords
   public Point getLocation()
   {
	   Point p = new Point( getCenterX(), getCenterY() );
	   return p;
   }

}

