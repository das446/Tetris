public class Tile{
    Pos pos;

    public Pos LocalPos(Piece p){
        return pos.Offset(-p.x, -p.y);
    }

    
}