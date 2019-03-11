import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.awt.Color;

import javax.swing.*;

public class GameWindow {

    static Boolean tick = true;
    public static Board board;
    public static BoardDisplay display;
    public static TickThread tt;

    public static void Start(String[] args) {

        Start();
    }

    public static void Start() {
        board = new Board(10, 20);
        MakeWindow();
        // MakeConsole();
        if(tt!=null){
            tt.Pause();
        }
        tt = new TickThread(1000);
        tt.start();
    }

    private static void MakeWindow() {
        BoardDisplayWindow window = MakeFrame(board);
        BoardControlsDisplay controls = new BoardControlsDisplay(window, board);
        display = window;
        board.display = display;
        // boardDisplay.DrawBoard();

        controls.MakeButtons();
        controls.BindKeys();
    }

    private static void MakeConsole() {
        display = new BoardDisplayConsole(board);
    }

    public static synchronized void OnTick() {
        if (board.playing) {
            board.Tick();
            display.Tick();
        }
    }

    public static BoardDisplayWindow MakeFrame(Board b) {
        BoardDisplayWindow f = new BoardDisplayWindow(b, "Tetris");
        f.getContentPane().setBackground(Color.white);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024, 768);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
        f.setResizable(false);

        return f;
    }
}