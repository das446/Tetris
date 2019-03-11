import java.awt.Color;
import java.util.Random;
import java.util.*;

public class Piece {
    Tile[] tiles = new Tile[4];
    Pos pos; // origin is bottom left

    public Tile[][] GetArea() {
        Tile[][] area = new Tile[4][4];
        for (int x = 0; x < 4; x++) {
            Tile t = tiles[x];
            area[t.LocalPos(this).x][t.LocalPos(this).y] = t;
        }
        return area;
    }

    void SetTiles(Tile[] tiles) {
        this.tiles = new Tile[4];
        for (int i = 0; i < 4; i++) {
            this.tiles[i] = tiles[i];
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

    public Piece(Piece p) {
        pos = p.pos;
        Tile[] ts = new Tile[4];
        for (int i = 0; i < 4; i++) {
            ts[i] = new Tile(p.GetTile(i).pos, p.GetTile(i).color);
        }
        SetTiles(ts);
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

        SetTiles(ts);
    }

    public void Rotate(Board b, boolean clockwise) {
        if(!CanRotate(b, clockwise)){
            return;
        }
        System.out.println("Valid rotate");

        for (int i = 0; i < 4; i++) {
            Pos p = tiles[i].LocalPos(this);

            Pos newPos = new Pos(3 - p.y, p.x);
            if (clockwise) {
                newPos = new Pos(p.y, 3 - p.x);
            }
            tiles[i].SetLocalPos(newPos.x, newPos.y, this);
        }
    }

    public void RotateClockwise(Board b) {
        Rotate(b, true);
    }

    // (3 - y, x)
    public void RotateCounterClockwise(Board b) {
        Rotate(b, false);
    }

    boolean CanRotate(Board b, boolean clockwise){
        for (int i = 0; i < 4; i++) {
            Pos p = tiles[i].LocalPos(this);
            Pos globalPos = tiles[i].GetPos();

            Pos newPos = new Pos(3 - p.y, p.x);
            if (clockwise) {
                newPos = new Pos(p.y, 3 - p.x);
            }
            Pos newGlobalPos = globalPos.Offset(newPos);

            Tile target = b.GetTile(newGlobalPos);
            if (target == Board.outOfBounds) {
                System.out.println("out of bounds " + newGlobalPos.ToString());
                return false;
            } else if (target == Board.empty) {
            } else if (HasTileAtPos(newGlobalPos.x, newGlobalPos.y)) {
            } else {
                System.out.println("blocked at " + globalPos.ToString() + " -> " + newGlobalPos.ToString());
                return false;
            }
        }
        return true;
    }
    public void Move(Direction d, Board b) {
        if (CanMove(d, b)) {
            pos = pos.Neighbor(d);
            for (int i = 0; i < 4; i++) {
                tiles[i].Move(d);
            }
        } else if (d == Direction.Down && !CanMove(d, b) && b.GetActivePiece() == this) {
            b.LockActivePiece();
        }

    }

    public Boolean CanMove(Direction d, Board b) {
        ArrayList<Tile> edge = Edge(d);
        for (int i = 0; i < edge.size(); i++) {
            Tile t = edge.get(i);
            Pos p = t.pos.Neighbor(d);
            if (b.Empty(p) == false) {
                return false;
            }
        }
        return true;
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
        Tile[][] area = GetArea();
        ArrayList<Tile> edge = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            Pos p = tiles[i].LocalPos(this);
            if (p.x == 0) {
                edge.add(tiles[i]);
            } else if (area[p.x - 1][p.y] == null) {
                edge.add(tiles[i]);
            }
        }
        return edge;
    }

    ArrayList<Tile> RightEdge() {
        Tile[][] area = GetArea();
        ArrayList<Tile> edge = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            Pos p = tiles[i].LocalPos(this);
            if (p.x == 3) {
                edge.add(tiles[i]);
            } else if (area[p.x + 1][p.y] == null) {
                edge.add(tiles[i]);
            }
        }
        return edge;
    }

    ArrayList<Tile> BottomEdge() {
        Tile[][] area = GetArea();
        ArrayList<Tile> edge = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            Pos p = tiles[i].LocalPos(this);
            if (p.y == 0) {
                edge.add(tiles[i]);
            } else if (area[p.x][p.y - 1] == null) {
                edge.add(tiles[i]);
            }
        }
        return edge;
    }

    public Pos HardDrop(Board board) {
        while (CanMove(Direction.Down, board)) {
            Move(Direction.Down, board);
        }
        return pos;
    }

    public void SetPos(Pos p) {
        Tile[][] area = GetArea();
        pos = p;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Tile t = area[x][y];
                if (t != null) {
                    t.SetLocalPos(x, y, this);
                }
            }
        }
    }

    public Boolean HasTile(Tile t) {
        for (int x = 0; x < 4; x++) {
            if (GetTile(x) == t) {
                System.out.println(GetTile(x).toString());
                return true;
            }
        }
        return false;
    }

    public Boolean HasTileAtPos(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (GetTile(i).pos.equals(new Pos(x, y))) {
                return true;
            }
        }
        return false;
    }

}