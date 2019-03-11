import javax.swing.*;
import java.awt.*;

public class BoardDisplayConsole implements BoardDisplay {

    Board board;

    public BoardDisplayConsole(Board b) {
        board = b;
    }

    public void DrawBoard(Graphics g) {
        for (int y = board.height; y >= 0; y--) {
            for (int x = 0; x < board.width; x++) {
                if (board.Empty(new Pos(x, y))) {
                    System.out.print("-");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void Tick() {
        DrawBoard(null);
    }

    public void Lose(int score){
        
    }
}