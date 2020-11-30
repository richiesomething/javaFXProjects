package mancala;

public class Point
{
   private double x, y; // in screen coords
	    /**
	     * Constructor for objects of class Point
	     */
	    public Point( double x, double y )
	    {
	        this.x = x;
	        this.y = y;
	    }
	    
	    public Point()
	    { this(0,0); }

	    public double getX() { return x; }
	    public double getY() { return y; }
	    public void setX(double x1) { x = x1; }
	    public void setY(double y1) { y = y1; }
	    
   public void plus( Point p )
   {
	   x += p.x;
	   y += p.y;
   }
   
   public void minus( Point p )
   {
	   x -= p.x;
	   y -= p.y;
   }
   
   public void report(String s)
   {
	   String xstr = String.format("%.0f",x);
	   String ystr = String.format("%.0f",y);
	   System.out.println(s+"("+xstr+","+ystr+") ");
   }
}

