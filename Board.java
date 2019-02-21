import java.util.ArrayList;

public class Board {
    ArrayList<Tile> tiles;
    int height, width;
    ArrayList<Piece> next;

    Piece activePiece;

    Piece held;

    int points;

    Boolean alreadyHeld = false;

    public Board(int width, int height) {
        this.height = height + 5;// includes hidden top rows
        this.width = width;
        tiles = new ArrayList<Tile>();
    }

    void MakeNext() {
        if (next.size() <= 7) {
            ArrayList<Tile> newTiles = new ArrayList<Tile>();

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
            activePiece = Piece.Random(new Pos(3, 21), this);
            AddPiece(activePiece);

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
        ArrayList<Tile> row = Row(i);
        if (row.size() >= width) {
            for (int x = 0; x < row.size(); x++) {
                tiles.remove(row.get(x));
            }

            for (int y = i + 1; y < height; y++) {
                ArrayList<Tile> r = Row(y);
                for (int x = 0; x < width; x++) {
                    Tile tile = GetTile(x, y);
                    if (tile != null) {
                        tile.Move(Direction.Down);
                    }
                }
            }
        }
    }

    ArrayList<Tile> Row(int y) {
        ArrayList<Tile> row = new ArrayList<Tile>();
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = GetTile(i, y);
            if (t != null) {
                row.add(t);
            }
        }
        return row;
    }

    Boolean FullRow(int i) {
        for (int x = 0; x < width; x++) {
            if (GetTile(x, i) == null) {
                return false;
            }
        }
        return true;
    }

    public Tile GetTile(int x, int y) {
        if (OutOfBounds(x, y)) {
            return null;
        }
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            if (t.pos.x == x && t.pos.y == y) {
                return t;
            }
        }
        return null;

    }

    private Boolean OutOfBounds(Pos p) {
        return OutOfBounds(p.x, p.y);
    }

    private Boolean OutOfBounds(int x, int y) {
        return x < 0 || x > width - 1 || y < 1 || y > height;
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
        Boolean hasTile = GetTile(p) != null;
        return !OutOfBounds(p) && !hasTile;
    }

    void Hold() {
        System.out.println("Hold");
        if (alreadyHeld) {
            return;
        }
        Piece temp;
        if (held == null) {
            held = activePiece;
            for (int i = 0; i < 4; i++) {
                tiles.remove(activePiece.GetTile(i));
            }
            activePiece = Piece.Random(new Pos(3, 21), this);
            AddPiece(activePiece);
        }

        else {
            temp = held;
            held = activePiece;
            for (int i = 0; i < 4; i++) {
                tiles.remove(activePiece.GetTile(i));
            }
            activePiece = temp;
            AddPiece(activePiece);
        }
        alreadyHeld = true;
    }

    void Lose() {

    }
}