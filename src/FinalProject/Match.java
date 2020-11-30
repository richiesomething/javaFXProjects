package FinalProject;

import ChessFinal.Chessboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Match extends Application {

    Player white;
    Player black;
    public static Chessboard board;

    @Override
    public void start(Stage stage) {

        white = new Player();
        black = new Player();
        board = new Chessboard();

        Scene scene = new Scene(board, 600, 600);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

        int moves = 0;
        while (!checkmate()) {
            if (moves++ % 2 == 0)
                white.move();
            else
                black.move();
        }

    }

    public boolean checkmate() {
        return false;
    }

    public static void main(String[] args) { Application.launch(args); }

}
