import java.awt.Color;
import java.util.Random;
import java.util.*;

public class Piece {
    Tile[][] area; //
    Tile[] tiles;
    Pos pos; // origin is bottom left

    void SetTiles(Tile[] tiles) {
        area = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            Tile t = tiles[i];
            int x = t.LocalPos(this).x;
            int y = t.LocalPos(this).y;
            area[x][y] = t;
        }
    }

    public static Piece Random(Pos p, Board b) {
        Type t = Type.getRandomType();

        return new Piece(t, p, b);
    }

    public enum Type {
        I, O, T, S, Z, J, L;

        public static Type getRandomType() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

    }

    public Piece(Type t, Pos p, Board b) {
        pos = new Pos(p);
        Tile[] ts = new Tile[4];
        switch (t) {
        case O: {
            Color color = Color.yellow;
            ts[0] = new Tile(1, 1, color, pos);
            ts[1] = new Tile(2, 1, color, pos);
            ts[2] = new Tile(1, 2, color, pos);
            ts[3] = new Tile(2, 2, color, pos);

            break;
        }

        case I: {
            Color color = Color.cyan;
            ts[0] = new Tile(1, 0, color, pos);
            ts[1] = new Tile(1, 1, color, pos);
            ts[2] = new Tile(1, 2, color, pos);
            ts[3] = new Tile(1, 3, color, pos);
            break;
        }

        case T: {
            Color color = Color.magenta;
            ts[0] = new Tile(1, 1, color, pos);
            ts[1] = new Tile(2, 1, color, pos);
            ts[2] = new Tile(3, 1, color, pos);
            ts[3] = new Tile(2, 2, color, pos);
            break;
        }

        case S: {
            Color color = Color.green;
            ts[0] = new Tile(1, 1, color, pos);
            ts[1] = new Tile(2, 2, color, pos);
            ts[2] = new Tile(2, 1, color, pos);
            ts[3] = new Tile(3, 2, color, pos);
            break;
        }

        case Z: {
            Color color = Color.red;
            ts[0] = new Tile(1, 2, color, pos);
            ts[1] = new Tile(2, 2, color, pos);
            ts[2] = new Tile(2, 1, color, pos);
            ts[3] = new Tile(3, 1, color, pos);
            break;
        }

        case L: {
            Color color = Color.orange;
            ts[0] = new Tile(1, 0, color, pos);
            ts[1] = new Tile(1, 1, color, pos);
            ts[2] = new Tile(1, 2, color, pos);
            ts[3] = new Tile(2, 0, color, pos);
            break;
        }

        case J: {
            Color color = Color.pink;
            ts[0] = new Tile(1, 0, color, pos);
            ts[1] = new Tile(2, 0, color, pos);
            ts[2] = new Tile(2, 1, color, pos);
            ts[3] = new Tile(2, 2, color, pos);
            break;
        }

        default:
            break;
        }

        tiles = ts;
        SetTiles(tiles);
    }

    public void RotateClockwise(Board b) {

        Tile[][] newArea = new Tile[4][4];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (area[x][y] != null) {
                    Tile t = area[x][y];
                    Pos p = t.GetPos();
                    if (b.GetTile(y, 3 - x) != null) {
                        return;
                    }
                    t.SetLocalPos(y, 3 - x, this);
                    newArea[y][3 - x] = t;
                }
            }
        }
        area = newArea;
    }

    public void RotateCounterClockwise(Board b) {
        Tile[][] newArea = new Tile[4][4];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (area[x][y] != null) {
                    Tile t = area[x][y];
                    Pos p = t.GetPos();
                    if (b.GetTile(3 - y, x) != null) {
                        return;
                    }
                    t.SetLocalPos(3 - y, x, this);
                    newArea[3 - y][x] = t;
                }
            }
        }
        area = newArea;
    }

    public void Move(Direction d) {

        pos = pos.Neighbor(d);
        for (int i = 0; i < 4; i++) {
            tiles[i].Move(d);
        }

    }

    public boolean CanMove(Direction d, Board b){
        return true;
    }

    public void Move(Direction d, int i) {
        for (int j = 0; j < i; j++) {
            Move(d);
        }
    }

    public Pos GetPos() {
        return new Pos(pos);
    }

    public Tile GetTile(int i) {
        return tiles[i];
    }

    ArrayList<Tile> Edge(Direction d) {
        if (d == Direction.Left) {
            return LeftEdge();
        } else if (d == Direction.Right) {
            return RightEdge();
        } else {
            return BottomEdge();
        }

    }

    ArrayList<Tile> LeftEdge() {
        ArrayList<Tile> edge = new ArrayList<Tile>();
        Boolean hit = false;
        for (int x = 0; x < 4; x++) {
            if (hit) {
                return edge;
            }
            for (int y = 0; y < 4; y++) {
                Tile t = area[x][y];
                if (t != null) {
                    edge.add(t);
                    hit = true;
                }
            }
        }
        return edge;
    }

    ArrayList<Tile> RightEdge() {
        ArrayList<Tile> edge = new ArrayList<Tile>();
        Boolean hit = false;
        for (int x = 3; x > 0; x--) {
            if (hit) {
                return edge;
            }
            for (int y = 0; y < 4; y++) {
                Tile t = area[x][y];
                if (t != null) {
                    edge.add(t);
                    hit = true;
                }
            }
        }
        return edge;
    }

    ArrayList<Tile> BottomEdge() {
        ArrayList<Tile> edge = new ArrayList<Tile>();
        Boolean hit = false;
        for (int y = 0; y < 4; y++) {
            if (hit) {
                return edge;
            }
            for (int x = 0; x < 4; x++) {
                Tile t = area[x][y];
                if (t != null) {
                    edge.add(t);
                    hit = true;
                }
            }
        }
        return edge;
    }

    public Pos HardDrop(Board board) {
        while (HitBeneath(board) == null) {
            Move(Direction.Down);
        }
        System.out.println(pos.ToString());
        return pos;
    }

    public Pos HitBeneath(Board board) {
        ArrayList<Tile> edge = Edge(Direction.Down);
        for (int i = 0; i < edge.size(); i++) {
            Pos p = edge.get(i).GetPos();
            Pos check = p.Down();
            if (board.GetTile(check) != null || p.y == 0) {
                return p;
            }
        }
        return null;
    }

    public Pos HardDropTarget(Board board) {
        ArrayList<Tile> edge = Edge(Direction.Down);
        int y = edge.get(0).GetPos().y;

        while (y >= 0) {
            if (HitBeneath(board) != null) {
                return new Pos(pos.x, y);
            }
            y--;
            Move(Direction.Down);
        }
        return new Pos(pos.x, y);

    }

}