import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class BoardDisplay extends JPanel{
    Board board;
    JFrame frame;

    int areaSide = 150 ;
    int width = 100, height = 200;

   protected void paintComponent(Graphics g) {
        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        System.out.println("Draw");
        g.setColor(Color.RED);
        g.fillRect(areaSide,areaSide,width,height);
    }


    public BoardDisplay(Board b, JFrame f){
        board = b;
        frame = f;
    }

    public void DrawBoard(){

        ArrayList<Tile> tiles = board.GetTiles();
        for(int i = 0; i<tiles.size();i++){
            Tile t = tiles.get(i);



        }
    }
}