public class Pos {
    public int x, y;

    public Pos Offset(Pos offset) {
        return new Pos(x + offset.x, y + offset.y);
    }

    public Pos Offset(int x, int y) {
        return new Pos(this.x + x, this.y + y);
    }

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos Right() {
        return Offset(1, 0);
    }

    public Pos Left() {
        return Offset(-1, 0);
    }

    public Pos Down() {
        return Offset(0, -1);
    }

    public Pos Neighbor(Direction d){
        if(d==Direction.Left){
            return Left();
        }
        else if(d==Direction.Right){
            return Right();
        }
        else{
            return Down();
        }
    }
}

public enum Direction{
    Left,
    Right,
    Down,
    //I don't think I need Up
}