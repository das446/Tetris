import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class Main {

    static Boolean tick = true;
    public static Board board;
    public static BoardDisplay display;

    public static void main(String[] args) {

        board = new Board(10, 20);
        MakeWindow();
        //MakeConsole();

        TickThread tt = new TickThread(1000);
        tt.start();
    }

    private static void MakeWindow() {
        BoardDisplayWindow window = MakeFrame(board);
        BoardControlsDisplay controls = new BoardControlsDisplay(window, board);
        display = window;
        // boardDisplay.DrawBoard();

        controls.MakeButtons();
        controls.BindKeys();
    }

    private static void MakeConsole() {
        display = new BoardDisplayConsole(board);
    }

    public static synchronized void OnTick() {
        board.Tick();
        display.Tick();
    }

    public static BoardDisplayWindow MakeFrame(Board b) {
        BoardDisplayWindow f = new BoardDisplayWindow(b, "Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024, 768);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
        return f;
    }
}