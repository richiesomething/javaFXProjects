package mancala;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
//import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
// TODO:   
// show player turn.  (feedback)
// we could put letters on the pits matching the keyboard ...
//   so someone can play from the keyboard.
// focus ..?


public class Mancala6 extends Application
{
   Pane root; // all the stuff in the window attaches here
   Pit[][] board; // pit[i][j] is for player i, j [0 to 6] left to right
   int whoseTurn=0; // 0 or 1
 
   public static void main( String[] args )
   { launch(args); }
	
   @Override
   public void start(Stage stage)
   {  
       stage.setTitle("Mancala");
       root = new Pane();  Pit.root = root;
       Scene scene = new Scene(root, 800, 400);
	   stage.setScene(scene);
	   stage.show();
	    
	   makePits();
       drawWholeThing();	   
       makeStones();
   }
   
   // add stones later so they are on top. 
   // note: stones are children of the main winow so that they
   // can move around outside of pits
   public void makeStones()
   {
       // add stones later so thay are on top ?
       for ( int i=0; i<6; i++ )
	   {  for ( int j=0; j<2; j++ )
	      { board[j][i].initStones(root); }
	   }
   }
 
   // allocate the Pit array, put all the pits in place (give
   // each their i,j, etc.
   public void makePits()
   {
	    board = new Pit[2][7];
	    for ( int i=0; i<6; i++)
	    {
	    	board[0][i] = new Pit( this, 200+80*i, 300, 30, 0,i );
	    }
	    board[0][6] = new Pit( this, 700, 200, 50, 0,6) ;

	    for ( int i=0; i<6; i++)
	    {
	    	board[1][i] = new Pit( this, 600-80*i, 100, 30, 1,i );
	    }
	    board[1][6] = new Pit( this, 100, 200, 50, 1,6) ;
   }
 
    // draw the whole state of the game
    public void drawWholeThing()
    {
    	root.getChildren().clear();
	    for ( int i=0; i<2; i++ )
	    {
	    	for ( int j=0; j<7; j++ )
	    	{
                Pit p = board[i][j];
                root.getChildren().add(p);
	    	}
	    }
    }

   /**
    * this is called when a player clicks on board[i][j]
    * @param i
    * @param j
    */
   void playFrom( int i, int j )
   {

   	 SequentialTransition st = new SequentialTransition();
	 Pit source = board[i][j];
	 int many = source.stoneCount(); 

	 for ( int m=0; m<many; m++ )
	 {
	     if ( j<5 )
	     {
	    	 j++;
             st.getChildren().add(moveStone(source,board[i][j]));
	     }
	     else if ( j==5 )
	     {
	    	 if ( whoseTurn==i )
	    	 {j++;}
	    	 else
	    	 { i=(i+1)%2; 
	    	    j=0;
	    	 }
			 st.getChildren().add(moveStone(source,board[i][j]));
	     }
	     else if ( j==6 )
	     {
	    	 i=(i+1)%2; 
	    	 j=0;
			 st.getChildren().add(moveStone(source,board[i][j]));
	     }


	 }

	 st.play();
	 whoseTurn = (whoseTurn+1)%2;

   }
   
   // take a stone from Pit a and put it in Pit b
   public PathTransition moveStone( Pit a, Pit b )
   //public TranslateTransition moveStone( Pit a, Pit b)
   {
      Stone s = a.pullStone(); // remove s Stone from Pit a

	   Point point = s.getLocation();

	   b.putStone(s);

	   Point point1 = s.getLocation();

	   Line line = new Line(point.getX(), point.getY(), point1.getX(), point1.getY());

	   PathTransition pt = new PathTransition();
	   pt.setNode(s);
	   pt.setPath(line);

	   return pt;
   }

}
