import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuWindow extends JFrame {

    int width = 512;
    int height = 700 ;
    int buttonWidth = 100;
    int buttonHeight = 40;

    private static final long serialVersionUID = 1L;


    public void Init() {
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        MakeButtons();

    }

    public void paint(Graphics g) {
        super.paint(g);
        int fontSize = 72;

        g.setFont(new Font("Arial", Font.PLAIN, fontSize));

        g.setColor(Color.black);
        int x = 80;
        int y = 10;
        String line = "Tetris ++";
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void MakeButtons() {
        int x = (width-buttonWidth)/2;
        JButton start = new JButton("Start");
        start.setBounds(x, 200, buttonWidth, 40);
        ActionListener startClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameWindow.Start();
                setVisible(false);
                dispose();
            }
        };
        start.addActionListener(startClick);

        JButton help = new JButton("Help");
        help.setBounds(x, 300, buttonWidth, 40);
        ActionListener helpClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenHelp();
                setVisible(false);
                dispose();
            }
        };
        help.addActionListener(helpClick);

        JButton scores = new JButton("Hi Scores");
        scores.setBounds(x, 400, buttonWidth, 40);
        ActionListener scoresClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HighScoreWindow();
                setVisible(false);
                dispose();
            }
        };
        scores.addActionListener(scoresClick);

        JButton quit = new JButton("Quit");
        quit.setBounds(x, 500, buttonWidth, 40);
        ActionListener quitClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        };
        quit.addActionListener(quitClick);

        add(start);
        add(help);
        add(scores);
        add(quit);
    }

    public void OpenHelp() {
        HelpWindow helpWindow = new HelpWindow();
    }

    public void OpenSettings(){
        SettingsWindow settingsWindow = new SettingsWindow();
    }

}