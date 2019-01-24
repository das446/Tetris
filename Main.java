import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class Main {

    static Boolean tick = true;

    public static void main(String[] args) {

        JFrame frame = MakeFrame(); 

        Board board = new Board(10,20);
        BoardDisplay boardDisplay = new BoardDisplay(board, frame);
        frame.add(boardDisplay);
        BoardControlsDisplay controls = new BoardControlsDisplay(frame);

        //boardDisplay.DrawBoard();

        controls.MakeButtons();

        TickThread tt = new TickThread(1000);
        tt.start();
    }

    public static void OnTick() {
        System.out.println(tick ? "Tick" : "Tock");
        tick = !tick;
    }

    public static JFrame MakeFrame(){
        JFrame f = new JFrame("Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024, 768);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
        return f;
    }
}