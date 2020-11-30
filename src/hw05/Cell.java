package hw05;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import java.util.LinkedList;

public class Cell extends Rectangle
{
    protected Color color;
    protected char clr;
    protected int I;
    protected int J;
    LinkedList<Cell> neighbors;

    public Cell(double wh, double x, double y, char ch, int i, int j) {
        super( wh, wh );

        clr = ch;
        I = i;
        J = j;

        color = ch == 'r' ? Color.RED : Color.BLUE;
        setFill(color);

        neighbors = new LinkedList<>();
        setX(x);
        setY(y);

    }

    public void addNeighbor( Cell c ) { if ( c != null ) neighbors.add(c) ; }

    public void setNext() {

        int n = neighbors.size();
        int diff = 0;

        for ( Cell c : neighbors )
            if (c.clr != clr) diff++;   // count how many neighbors you dont like

        neighbors.clear();

        // determine if you should move
        if ( n==0 || 100*diff/n <  Neighborhood.minOther || Neighborhood.maxOther<100*diff/n )
            move();

    }

    public void move() {

        Pair<Integer, Integer> pair = Neighborhood.emptyLots.poll();

        assert pair != null;
        int i = pair.getKey();
        int j = pair.getValue();

        double scale = (double)500/Neighborhood.nSize;

        setX( i*scale );
        setY( j*scale );

        Neighborhood.neighborhood[i][j] = this;
        Neighborhood.neighborhood[I][J] = null;

        Neighborhood.emptyLots.add(new Pair<>(I, J));

        I = i; J = j;

    }

}