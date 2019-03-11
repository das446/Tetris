import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpWindow extends JFrame {

    String rules;

    public HelpWindow() {
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        Init();


    }

    public void paint(Graphics g) {
        super.paint(g);
        int fontSize = 18;

        g.setFont(new Font("Arial", Font.PLAIN, fontSize));

        g.setColor(Color.black);
        int x = 10;
        int y = 30;
        for (String line : rules.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    void Init() {
        try {
            String rules = ReadFile("Data/Rules.txt");
            System.out.println(rules);
            this.rules = rules;
        } catch (Exception e) {
            System.out.println("Couldn't read file");
        }

        JButton back = new JButton("Back");
        back.setBounds(300, 200, 100, 40);
        ActionListener backClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartMenuWindow().Init();
                setVisible(false);
                dispose();
            }
        };
        back.addActionListener(backClick);

        add(back);
    }

    String ReadFile(String path) throws IOException {
        try {
            return new Scanner(new File(path)).useDelimiter("\\Z").next();
        } catch (Exception e) {
            System.out.println("Couldn't read file");
            return "";
        }

    }
}