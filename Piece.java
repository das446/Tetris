public class Piece {
    Tile[][] area; //
    Tile[] tiles;
    Pos pos; // origin is bottom left

    public Piece(Tile[] tiles) {
        area = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            Tile t = tiles[i];
            area[t.pos.x][t.pos.y] = t;
        }
    }

    public void RotateClockwise() {

    }

    public void RotateCounterClockwise() {

    }

    public void Move(Direction d){
        if(d==Direction.Down){
            MoveDown();
        }
        else if(d==Direction.Left){
            MoveLeft();
        }
        else if(d==Direction.Right){
            MoveRight();
        }
    }

    public void MoveDown() {
        pos = pos.Down();
        for (int i = 0; i < 4; i++) {
            Tile t = tiles[i];
            t.pos = t.pos.Down();
        }
    }

    public void MoveLeft() {

    }

    public void MoveRight() {

    }
}