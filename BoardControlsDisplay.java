import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class BoardControlsDisplay{

    JFrame frame;

    public BoardControlsDisplay(JFrame f){
        frame = f;
    }

    public void MakeButtons(){
        JButton moveLeft = new JButton("<");// creating instance of JButton
        moveLeft.setBounds(130, 100, 100, 40);// x axis, y axis, width, height
        ActionListener left = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move Left");
            }
        };
        moveLeft.addActionListener(left);

        JButton moveRight = new JButton(">");// creating instance of JButton
        moveRight.setBounds(260, 100, 100, 40);// x axis, y axis, width, height

         ActionListener right = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move Right");
            }
        };
        moveRight.addActionListener(right);
        frame.add(moveLeft);// adding button in JFrame
        frame.add(moveRight);
    }
}