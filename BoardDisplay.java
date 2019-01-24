import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;



public class BoardDisplay extends JFrame{
    Board board;

    int areaSide = 150 ;
    int tileSize = 30;
    int width = 10, height = 20;
    Pos origin = new Pos(100,100);

   public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(origin.x, origin.y, width*tileSize, height*tileSize);
        DrawBoard(g);
    }

    public BoardDisplay(Board b,String s){
        super(s);
        board = b;
    }

    public void DrawBoard(Graphics g){

        ArrayList<Tile> tiles = board.GetTiles();
        for(int i = 0; i<tiles.size();i++){
            Tile t = tiles.get(i);
            Pos p = t.GetPos();
            Color c = t.color;
            if(p.y>height){
                c = new Color(c.getRed(),c.getGreen(),c.getBlue(),50);
            }
            g.setColor(c);
            Pos drawPos = p.Offset(100,100);
            int x = origin.x + p.x * tileSize;
            int y = origin.y + height*tileSize - p.y * tileSize;
            g.fillRect(x, y, tileSize, tileSize);
            g.setColor(Color.black);
            g.drawRect(x, y, tileSize, tileSize);
        }
    }

    public void DrawGhostPiece(){
        Pos target;
    }
}