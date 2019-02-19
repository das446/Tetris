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
                } else if (e.getKeyCode() == 38) {
                    HardDrop();
                } else if (e.getKeyCode() == 40) {
                    MoveDown();
                } else if (e.getKeyCode() == 69) {
                    Hold();
                }
            }
        });

        frame.setFocusable(true);
    }

    void MoveLeft() {
        if (board.GetActivePiece() == null) {
            return;
        }
        board.GetActivePiece().Move(Direction.Left, board);
        frame.repaint();
    }

    void MoveRight() {
        if (board.GetActivePiece() == null) {
            return;
        }
        board.GetActivePiece().Move(Direction.Right, board);
        frame.repaint();
    }

    void MoveDown() {
        if (board.GetActivePiece() == null) {
            return;
        }
        board.GetActivePiece().Move(Direction.Down, board);
        frame.repaint();
    }

    void RotateRight() {
        if (board.GetActivePiece() == null) {
            return;
        }
        board.GetActivePiece().RotateClockwise(board);
        frame.repaint();
    }

    void RotateLeft() {
        if (board.GetActivePiece() == null) {
            return;
        }
        board.GetActivePiece().RotateCounterClockwise(board);
        frame.repaint();
    }

    void HardDrop() {
        if (board.GetActivePiece() == null) {
            return;
        }
        System.out.println("drop");
        board.GetActivePiece().HardDrop(board);
        board.Tick();
        frame.repaint();
    }

    void Hold(){

    }
}