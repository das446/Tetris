import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame{

    int score;
    ArrayList<Score> scores;

    public GameOverScreen(int score){
        super();
        this.score = score;
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        Init();
    }

    public void Init(){
        ReadScores();

        JButton back = new JButton("PlayAgain");
        back.setBounds(150, 400, 200, 40);
        ActionListener backClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameWindow.Start();
                setVisible(false);
                dispose();
            }
        };
        back.addActionListener(backClick);
        add(back);
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