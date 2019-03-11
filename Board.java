import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    ArrayList<Tile> tiles;
    int height, width;
    int fullHeight;
    ArrayList<Piece> next = new ArrayList<Piece>();

    Piece activePiece;

    Piece held;

    int linesCleared;

    int points;

    Boolean alreadyHeld = false;

    Pos start = new Pos(3, 20);

    public static Tile outOfBounds = new Tile(new Pos(-1, -1), Color.black);

    public static Tile empty = new Tile(new Pos(-1, -1), Color.black);

    public BoardDisplay display;
    public boolean playing = true;

    public Board(int width, int height) {
        this.height = height;
        this.width = width;
        this.fullHeight = height + 5;
        tiles = new ArrayList<Tile>();
        MakeNext();
    }

    void MakeNext() {
        if (next.size() <= 7) {
            ArrayList<Piece> newTiles = new ArrayList<Piece>();
            for (int i = 0; i < 7; i++) {
                Piece.Type t = Piece.Type.values()[i];
                newTiles.add(new Piece(t, start, this));
            }
            Collections.shuffle(newTiles);
            next.addAll(newTiles);
        }

    }

    public void LockActivePiece() {

        activePiece = null;
        for (int i = 0; i < height; i++) {
            ClearRow(i);
        }
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).pos.y > height) {
                Lose();
            }
        }
        alreadyHeld = false;
    }

    public void Tick() {
        if (activePiece == null) {
            activePiece = next.get(0);
            AddPiece(activePiece);
            next.remove(0);
            MakeNext();

        } else {
            activePiece.Move(Direction.Down, this);
        }

    }

    void AddPiece(Piece p) {
        for (int i = 0; i < 4; i++) {
            AddTile(p.GetTile(i));
        }
    }

    void AddTile(Tile t) {
        tiles.add(t);
    }

    void ClearRow(int i) {
        if (!FullRow(i)) {
            return;
        }
        ArrayList<Tile> row = Row(i);
        if (row.size() >= width) {
            for (int x = 0; x < row.size(); x++) {
                tiles.remove(row.get(x));
            }

            for (int y = i + 1; y < height; y++) {
                ArrayList<Tile> r = Row(y);
                for (int x = 0; x < width; x++) {
                    Tile tile = GetTile(x, y);
                    if (tile != empty) {
                        tile.Move(Direction.Down);
                    }
                }
            }

            linesCleared++;
            if (linesCleared % 10 == 0) {
                GameWindow.tt.SpeedUp();
            }
        }

    }

    ArrayList<Tile> Row(int y) {
        ArrayList<Tile> row = new ArrayList<Tile>();
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = GetTile(i, y);
            if (t != empty && t != outOfBounds) {
                row.add(t);
            }
        }
        return row;
    }

    Boolean FullRow(int i) {
        for (int x = 0; x < width; x++) {
            if (GetTile(x, i) == empty) {
                return false;
            }
        }
        return true;
    }

    public Tile GetTile(int x, int y) {
        if (OutOfBounds(x, y)) {
            return outOfBounds;
        }
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            if (t.pos.x == x && t.pos.y == y) {
                return t;
            }
        }
        return empty;

    }

    public Boolean OutOfBounds(Pos p) {
        return OutOfBounds(p.x, p.y);
    }

    public Boolean OutOfBounds(int x, int y) {
        return x < 0 || x > width - 1 || y < 1 || y > fullHeight;
    }

    public ArrayList<Tile> GetTiles() {
        return tiles;
    }

    public Piece GetActivePiece() {
        return activePiece;
    }

    public Tile GetTile(Pos p) {
        return GetTile(p.x, p.y);
    }

    public Boolean Empty(int x, int y) {
        return Empty(new Pos(x, y));
    }

    public Boolean Empty(Pos p) {
        return GetTile(p) == empty;
    }

    void Hold() {
        if (alreadyHeld) {
            return;
        }
        Piece temp;
        if (held == null) {
            held = activePiece;
            for (int i = 0; i < 4; i++) {
                tiles.remove(activePiece.GetTile(i));
            }
            activePiece = Piece.Random(start, this);
            AddPiece(activePiece);
        }

        else {
            temp = held;
            held = activePiece;
            for (int i = 0; i < 4; i++) {
                tiles.remove(activePiece.GetTile(i));
            }
            activePiece = temp;
            activePiece.SetPos(start);
            AddPiece(activePiece);
        }
        alreadyHeld = true;
    }

    void Lose() {
        if (playing) {
            playing = false;
            display.Lose(linesCleared);
        }
    }
}