import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.*;

public class Main {

    static Boolean tick = true;

    public static void main(String[] args) {

        TickThread tt = new TickThread(x -> OnTick(), 1000);
        tt.start();

        JFrame f = new JFrame();// creating instance of JFrame

        JButton b = new JButton("click");// creating instance of JButton
        b.setBounds(130, 100, 100, 40);// x axis, y axis, width, height
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World");
                System.out.println("Hello World2");
            }
        };
        b.addActionListener(al);
        f.add(b);// adding button in JFrame

        f.setSize(400, 500);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
    }

    public static void OnTick() {
        System.out.println(tick ? "Tick" : "Tock");
        tick = !tick;
    }
}