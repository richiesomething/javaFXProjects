package mancala;

import java.util.LinkedList;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Pit extends Pane // Circle
{
	 protected Circle theCircle;
	 public Circle getTheCircle(){return theCircle;}
	 protected LinkedList<Stone> theStones;
	 public LinkedList<Stone> getTheStones(){ return theStones; }
	 int myI; // coordinates within the board
	 int myJ;
	 private Mancala6 theMan;
	 static public Pane root;
	 
	//static protected Mancala1 theMancala;
	//static public void setTheMancala( Mancala1 m ) { theMancala = m; }
	    
	public Pit( Mancala6 tm, int x, int y, double r, int i, int j )
	{
		theMan = tm;
		//super(r);
		theStones = new LinkedList<Stone>();
		theCircle = new Circle(r);
		setLayoutX(x); // setCenterX( x );
		setLayoutY(y); // setCenterY( y);
		theCircle.setFill( (i==0? Color.PINK:Color.LIGHTBLUE) );
		getChildren().add(theCircle);
		
		//initStones();

		addEventHandler(MouseEvent.MOUSE_CLICKED, 
		    (MouseEvent m)->  {
				theMan.playFrom(i,j);
			}  );
	}
	
	public void initStones( Pane root)
	{
		// all pits except #6 get 4 stones
		if ( myJ<6 )
		{
		   for ( int k=0; k<4; k++ )
		   { addStone(root); }
		}

	}
	
	public int stoneCount()
	{
		return theStones.size();
	}
	
	// add a stone to this pit
	public void addStone( Pane root )
	{
		Stone s = new Stone(this);
        theStones.add(s);
		root.getChildren().add(s);
		
		// stone location is stone_relative_to_pane + pane_location
		Point sp = Stone.location( theStones.size()-1 );
		sp.plus( getGlobal() );
		s.setCenterX( sp.getX() );
		s.setCenterY( sp.getY() );
	}
	
	// get rid of all the stones from this pit
	// (must re-add the circle for the pit itself)
	public void clear()
	{
		theStones.clear();
		getChildren().clear();
		getChildren().add(theCircle);
	}
	
 	public Stone pullStone()
 	{
 		//stones--;
 		Stone p = theStones.removeLast(); // was hold
 		return p;
 	}

 	// global position of the nth stone
 	public Point pozish(int n)
 	{
 		Point p = getGlobal(); // coords of pit in window
 		
 		Point spoint = Stone.location(n); // coords of stone rel to pit
 		p.plus(spoint);
 		return p;
 	}

 	
 	// return the position of THIS pit in window coordinates
 	public Point getGlobal()
 	{
 		return new Point( getLayoutX(), getLayoutY() );
 	}
 	
 	// Return the position that the NEXT stone will go.
 	// This is in global coordinates.
 	public Point pozish()
 	{
 		return pozish( theStones.size() );
 	}
 	
 	public void putStone( Stone s )
 	{
 		Point p = Stone.location( theStones.size() );
 		p.plus( getGlobal() );
 		s.setCenterX( p.getX() );
 		s.setCenterY( p.getY() );
 		theStones.add(s);
 	}

 	public void putStone()
 	{
 		Stone s = new Stone(this);
 		root.getChildren().add(s);
 		putStone(s);
 	}
}
