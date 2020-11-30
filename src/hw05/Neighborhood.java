package hw05;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.Collections;
import java.util.LinkedList;

public class Neighborhood extends Pane {

    public static Cell[][] neighborhood;
    public static LinkedList<Cell> neighbors;
    public static LinkedList<Pair<Integer, Integer>> emptyLots;

    public static int nSize ;
    public static int minOther;
    public static int maxOther;
    public static int percentRed;
    public static int percentBlue;


    /* note: neighborhood pane added by start button removed by reset button */


    public Neighborhood(int ns, int minO, int maxO, int pr, int pb) {
        super();

        nSize = ns;
        minOther = minO;
        maxOther = maxO;
        percentRed = pr;
        percentBlue = pb;

        setWidth(500);
        setHeight(500);
        setPrefHeight(500);
        setPrefWidth(500);

        neighborhood = new Cell[nSize][nSize];
        neighbors = new LinkedList<>(); // used for iterating over all residents to determine if moving
        emptyLots = new LinkedList<>(); // used for keeping track of available lots to move

        buildNeighborhood();
        meetNeighborhood();

    }


    public void buildNeighborhood() {

        double scale = getWidth() / (double)nSize;
//        neighborhood = new Cell[nSize][nSize];

        for ( int i=0; i<nSize; i++ ) {
            for ( int j=0; j<nSize; j++ ) {

                double color = Math.random() * 100d;
                char ch;

                // used to determine who moves in or if left empty based on imputs
                if (color < percentRed) ch = 'r';
                else if (color < percentRed + percentBlue) ch = 'b';
                else { emptyLots.add(new Pair<>(i, j)); continue; }

                Cell c = new Cell( scale, i*scale, j*scale, ch, i, j);
                getChildren().add(c);
                neighborhood[i][j] = c;
                neighbors.add(c);


            }
        }
    }

    public static void meetNeighborhood() {
        for ( int i=0; i<nSize; i++ ) {
            for ( int j=0; j<nSize; j++ ) {

                if (neighborhood[i][j] == null) continue;

                Cell c = neighborhood[i][j];
                c.addNeighbor( getCell(i-1,j-1) );
                c.addNeighbor( getCell(i,j-1) );
                c.addNeighbor( getCell(i+1,j-1) );
                c.addNeighbor( getCell(i-1, j) );
                c.addNeighbor( getCell(i+1, j) );
                c.addNeighbor( getCell(i-1,j+1) );
                c.addNeighbor( getCell(i  ,j+1) );
                c.addNeighbor( getCell(i+1,j+1) );

            }
        }
    }

    public static Cell getCell(int i, int j) {
        return neighborhood[wrap(i)][wrap(j)];
    }

    public static int wrap( int ij ) {
        if ( ij<0 ) { ij += nSize; }
        if ( ij>=nSize ) { ij -= nSize; }
        return ij;
    }

    public static void step() {
        Collections.shuffle(neighbors);
        Collections.shuffle(emptyLots);
        for ( Cell c : neighbors ) { c.setNext(); }
        meetNeighborhood();
    }

}
