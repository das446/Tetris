import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class BoardControlsDisplay{

    JFrame frame;
    Board board;

    public BoardControlsDisplay(JFrame f, Board b){
        frame = f;
        board = b;
    }

    public void MakeButtons(){
        JButton moveLeft = new JButton("<");// creating instance of JButton
        moveLeft.setBounds(600, 100, 100, 40);// x axis, y axis, width, height
        ActionListener left = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.GetActivePiece().Move(Direction.Left);
                frame.repaint();
            }
        };
        moveLeft.addActionListener(left);

        JButton moveRight = new JButton(">");// creating instance of JButton
        moveRight.setBounds(800, 100, 100, 40);// x axis, y axis, width, height

         ActionListener right = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 board.GetActivePiece().Move(Direction.Right);
                 frame.repaint();
            }
        };
        moveRight.addActionListener(right);
        frame.add(moveLeft);// adding button in JFrame
        frame.add(moveRight);
    }
}