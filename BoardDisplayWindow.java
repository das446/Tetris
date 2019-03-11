import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BoardDisplayWindow extends JFrame implements BoardDisplay {
    private static final long serialVersionUID = 1L;

    Board board;

    int areaSide = 150;
    int tileSize = 30;
    int width = 10, height = 20;
    Pos origin = new Pos(150, 100);

    int boxSize = 120;

    int nextAmnt = 3;

    Color lineColor = Color.black;

    public void Tick() {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // try {
        // BufferedImage bg = ImageIO.read(new File("bg.jpg"));
        // g.drawImage(bg, 0, 0, new JLayeredPane());
        // } catch (Exception e) {
        // System.out.println("Failed to draw Background");
        // }
        g.setColor(lineColor);
        g.drawRect(origin.x, origin.y, width * tileSize, height * tileSize);
        DrawBoard(g);
        DrawGhostPiece(g);

        DrawHeld(g);
        Pos o = origin.Offset(width * tileSize, 0);
        DrawNexts(o, tileSize, g);

        g.drawString("Points: " + board.linesCleared, 450, 650);
    }

    private void DrawHeld(Graphics g) {
        Font font = new Font("Comic Sans", Font.PLAIN, 48);
        g.setFont(font);
        g.drawRect(origin.x - boxSize, origin.y, boxSize, boxSize);
        g.drawString("Hold", origin.x - boxSize, origin.y + boxSize + 40);

        if (board.held != null) {
            DrawPiece(board.held, new Pos(origin.x - boxSize, origin.y), tileSize, g);
        }
    }

    public BoardDisplayWindow(Board b, String s) {
        super(s);
        board = b;
    }

    public void DrawBoard(Graphics g) {

        ArrayList<Tile> tiles = board.GetTiles();
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            DrawTile(t, false, g);
        }
    }

    public void DrawTile(Tile t, Boolean ghost, Graphics g) {
        Pos p = t.GetPos();
        Color c = t.color;
        if (p.y > height || ghost) {
            c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 50);
        }
        g.setColor(c);
        Pos drawPos = p.Offset(100, 100);
        int x = origin.x + p.x * tileSize;
        int y = origin.y + height * tileSize - p.y * tileSize;
        g.fillRect(x, y, tileSize, tileSize);
        g.setColor(lineColor);
        g.drawRect(x, y, tileSize, tileSize);
    }

    public void DrawGhostPiece(Graphics g) {
        if (board.GetActivePiece() == null) {
            return;
        }
        Piece p = new Piece(board.GetActivePiece());
        p.HardDrop(board);
        for (int i = 0; i < 4; i++) {
            DrawTile(p.GetTile(i), true, g);
        }
    }

    public void DrawPiece(Piece piece, Pos origin, int tileSize, Graphics g) {
        for (int i = 0; i < 4; i++) {
            Tile t = piece.GetTile(i);
            g.setColor(t.color);
            int x = origin.x + t.LocalPos(piece).x * tileSize;
            int y = origin.y + ((3 - t.LocalPos(piece).y) * tileSize);
            g.fillRect(x, y, tileSize, tileSize);
            g.setColor(lineColor);
            g.drawRect(x, y, tileSize, tileSize);
        }
    }

    public void DrawNexts(Pos origin, int tileSize, Graphics g) {
        int y = origin.y;
        int boxSize = tileSize * 4;
        for (int i = 0; i < nextAmnt; i++) {
            g.drawRect(origin.x, y, boxSize, boxSize);
            DrawPiece(board.next.get(i), new Pos(origin.x, y), tileSize, g);
            y = y + boxSize;
            tileSize -= 4;
            boxSize = tileSize * 4;
        }
    }

    public void Lose(int score) {
        new GameOverScreen(score);
        setVisible(false);
        dispose();
    }
}