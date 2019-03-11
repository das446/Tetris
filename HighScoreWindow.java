import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreWindow extends JFrame {

    ArrayList<Score> scores;

    public HighScoreWindow() {
        super();
        ReadScores();
        //getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(512, 700);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        Init();

    }

    void Init() {
        JButton back = new JButton("Back");
        back.setBounds(200, 500, 100, 40);
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

    public void paint(Graphics g) {
        int x = 200;
        int y = 200;
        int fontSize = 18;

        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.drawString("High Scores:", x, y += g.getFontMetrics().getHeight());
        try {
            for (Score line : scores) {
                g.drawString(line.name + "    " + line.amnt, x, y += g.getFontMetrics().getHeight());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        

    }

    public void ReadScores() {
        String path = "Data/Scores.txt";
        scores = new ArrayList<Score>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line; (line = br.readLine()) != null;) {
                String[] s = line.split(" ");
                scores.add(new Score(s[0], Integer.parseInt(s[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WriteScores() {

    }
}