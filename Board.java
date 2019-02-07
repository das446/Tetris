import java.util.ArrayList;

public class Board {
    Tile[][] tiles;
    int height, width;
    ArrayList<Piece> next;

    Piece activePiece;

    public Board(int width, int height) {
        this.height = height + 5;// includes hidden top rows
        this.width = width;
        tiles = new Tile[this.width][this.height];
    }

    public void LockActivePiece(){
        activePiece = null;
    }

    public void Tick() {
        if (activePiece == null) {
            activePiece = Piece.Random(new Pos(3, 21), this);
            AddPiece(activePiece);

        } else {
            activePiece.Move(Direction.Down);
        }

        UpdateTiles();
    }

    void AddPiece(Piece p) {
        for (int i = 0; i < 4; i++) {
            AddTile(p.GetTile(i));
        }
    }

    void AddTile(Tile t) {
        Pos p = t.GetPos();
        tiles[p.x][p.y] = t;
    }

    public void UpdateTiles() {
        ArrayList<Tile> ts = GetTiles();
        for (int i = 0; i < ts.size(); i++) {
            Tile t = ts.get(i);
            if (!InvalidPos(t.pos.x, t.pos.y)) {
                tiles[t.pos.x][t.pos.y] = t;
            }
        }
    }

    public void ClearLine(int i) {

    }

    public Tile GetTile(int x, int y) {
        if (InvalidPos(x, y)) {
            return null;
        }
        return tiles[x][y];
    }

    private boolean InvalidPos(int x, int y) {
        return x < 0 || y < 0 || x >= width || y >= height + 4;
    }

    public ArrayList<Tile> GetTiles() {
        ArrayList<Tile> t = new ArrayList<Tile>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y] != null) {
                    t.add(tiles[x][y]);
                }

            }
        }

        return t;

    }

    public Boolean OutOfBounds(Pos p) {
        return p.x < 0 || p.x >= width || p.y < 0 || p.y > height;
    }

    public Piece GetActivePiece() {
        return activePiece;
    }

    public Tile GetTile(Pos p) {
        return GetTile(p.x, p.y);
    }

}