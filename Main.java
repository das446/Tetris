import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class Main {

    static Boolean tick = true;
    public static Board board;
    public static BoardDisplay frame;

    public static void main(String[] args) {

        board = new Board(10,20);
        frame = MakeFrame(board); 
        BoardControlsDisplay controls = new BoardControlsDisplay(frame,board);

        //boardDisplay.DrawBoard();

        controls.MakeButtons();
        controls.BindKeys();

        TickThread tt = new TickThread(1000);
        tt.start();
    }

    public static void OnTick() {
        board.Tick();
        frame.repaint();
    }

    public static BoardDisplay MakeFrame(Board b){
        BoardDisplay f = new BoardDisplay(b,"Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024, 768);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
        return f;
    }
}