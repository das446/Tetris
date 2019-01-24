import java.awt.Color;

public class Tile {
    Pos pos;
    public Color color;

    public Tile(Pos p, Color c) {
        pos = new Pos(p);
        color = c;
    }

    public Tile(int x, int y, Color c) {
        pos = new Pos(x, y);
        color = c;
    }

    public Tile(Pos p, Color c, Pos origin) {
        pos = origin.Offset(p);
        color = c;
    }

    public Tile(int x, int y, Color c, Pos origin) {
        pos = new Pos(x + origin.x, y + origin.y);
        color = c;
    }

    public void SetPos(Pos p) {
        pos.x = p.x;
        pos.y = p.y;
    }

    public void SetPos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public void SetLocalPos(int x, int y, Piece p) {
        pos.x = x + p.pos.x;
        pos.y = y + p.pos.y;
    }

    public Pos GetPos() {
        return new Pos(pos);
    }

    public Pos LocalPos(Piece p) {
        Pos pos2 = p.GetPos();
        return pos.Offset(-pos2.x, -pos2.y);
    }

    public void Move(Direction d) {
        pos = pos.Neighbor(d);
    }

    public void Lock() {

    }

}