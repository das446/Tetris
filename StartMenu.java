import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.awt.Color;

import javax.swing.*;

public class StartMenu {

    public static StartMenuWindow display;
    int width;
    int height;

    public static void main(String[] args) {
        MakeWindow();
    }

    private static void MakeWindow() {
        StartMenuWindow window = MakeFrame();
        display = window;
        window.Init();
    }

    public static StartMenuWindow MakeFrame() {
        StartMenuWindow f = new StartMenuWindow();
        return f;
    }

    

}