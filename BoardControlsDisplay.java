import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import javax.swing.*;

public class BoardControlsDisplay {

    JFrame frame;
    Board board;

    public BoardControlsDisplay(JFrame f, Board b) {
        frame = f;
        board = b;
    }

    public void MakeButtons() {
        JButton moveLeft = new JButton("<");
        moveLeft.setBounds(600, 100, 100, 40);
        ActionListener left = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MoveLeft();
            }
        };
        moveLeft.addActionListener(left);

        JButton moveRight = new JButton(">");
        moveRight.setBounds(800, 100, 100, 40);
        ActionListener right = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MoveRight();
            }
        };
        moveRight.addActionListener(right);

        JButton rotateRight = new JButton("↷");// creating instance of JButton
        rotateRight.setBounds(600, 300, 100, 40);// x axis, y axis, width, height
        ActionListener rot = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RotateRight();
            }
        };
        rotateRight.addActionListener(rot);

        JButton rotateLeft = new JButton("↶");// creating instance of JButton
        rotateLeft.setBounds(800, 300, 100, 40);// x axis, y axis, width, height
        ActionListener rotCC = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RotateLeft();
            }
        };
        rotateLeft.addActionListener(rotCC);

        frame.add(moveLeft);// adding button in JFrame
        frame.add(moveRight);
        frame.add(rotateRight);
        frame.add(rotateLeft);
    }

    public void BindKeys() {
        System.out.println("Bind keys");
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent arg0) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {
                    MoveLeft();
                } else if (e.getKeyCode() == 39) {
                    MoveRight();
                } else if (e.getKeyCode() == 81) {
                    RotateLeft();
                } else if (e.getKeyCode() == 87) {
                    RotateRight();
                }
            }
        });

        frame.setFocusable(true);
    }

    void MoveLeft() {
        board.GetActivePiece().Move(Direction.Left);
        board.UpdateTiles();
        frame.repaint();
    }

    void MoveRight() {
        board.GetActivePiece().Move(Direction.Right);
        board.UpdateTiles();
        frame.repaint();
    }

    void RotateRight() {
        board.GetActivePiece().RotateClockwise(board);
        board.UpdateTiles();
        frame.repaint();
    }

    void RotateLeft() {
        board.GetActivePiece().RotateCounterClockwise(board);
        board.UpdateTiles();
        frame.repaint();
    }
}