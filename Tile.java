public class Tile{
    Pos pos;

    public void SetPos(Pos p){
        pos.x = p.x;
        pos.y = p.y;
    }

    public void SetPos(int x, int y){
        pos.x = x;
        pos.y = y;
    }

    public Pos GetPos(){
        return new Pos(pos);
    }

    public Pos LocalPos(Piece p){
        Pos pos2 = p.GetPos();
        return pos.Offset(-pos2.x, -pos2.y);
    }

    public void Move(Direction d){
        Pos newPos = pos.Neighbor(d);

    }

    
}