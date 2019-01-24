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
        pos = pos.Neighbor(d);
        for(int i=0;i<4;i++){
            tiles[i].Move(d);
        }
    }

    public Pos GetPos(){
        return new Pos(pos);
    }

   
    public enum Type{
        I,
        O,
        T,
        S,
        Z,
        J,
        L

    }

}