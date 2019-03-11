import javax.swing.*;
import java.awt.*;

public interface BoardDisplay{
    void DrawBoard(Graphics g);
    void Tick();
    void Lose(int score);
}