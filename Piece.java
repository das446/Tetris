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

    public static Piece Random(Pos p,Board b){
        Type t =Type.getRandomType();

        return new Piece(t,p,b);
    }

    public Piece(Type t,Pos p, Board b){
        System.out.println(t.toString());
        pos = new Pos(p);
        Tile[] ts = new Tile[4];
        switch(t){
            case O:
            {
                Color color = Color.yellow;
                ts[0] = new Tile(1,1,color,pos);
                ts[1] = new Tile(2,1,color,pos);
                ts[2] = new Tile(1,2,color,pos);
                ts[3] = new Tile(2,2,color,pos);
                
                break;
            }

             case I:
             {
                Color color = Color.cyan;
                ts[0] = new Tile(1,0,color,pos);
                ts[1] = new Tile(1,1,color,pos);
                ts[2] = new Tile(1,2,color,pos);
                ts[3] = new Tile(1,3,color,pos);
                break;
             }

             case T:{
                Color color = Color.magenta;
                ts[0] = new Tile(0,0,color,pos);
                ts[1] = new Tile(1,0,color,pos);
                ts[2] = new Tile(2,0,color,pos);
                ts[3] = new Tile(1,1,color,pos);
                break;
             }

             case S:{
                Color color = Color.green;
                ts[0] = new Tile(0,0,color,pos);
                ts[1] = new Tile(1,1,color,pos);
                ts[2] = new Tile(1,0,color,pos);
                ts[3] = new Tile(2,1,color,pos);
                break;
             }

             case Z:{
                Color color = Color.red;
                ts[0] = new Tile(0,1,color,pos);
                ts[1] = new Tile(1,1,color,pos);
                ts[2] = new Tile(1,0,color,pos);
                ts[3] = new Tile(2,0,color,pos);
                break;
             }

            case L:{
                Color color = Color.orange;
                ts[0] = new Tile(0,0,color,pos);
                ts[1] = new Tile(0,1,color,pos);
                ts[2] = new Tile(0,2,color,pos);
                ts[3] = new Tile(1,0,color,pos);
                break;
            }

            case J:
            {
                Color color = Color.pink;
                ts[0] = new Tile(0,0,color,pos);
                ts[1] = new Tile(1,0,color,pos);
                ts[2] = new Tile(1,1,color,pos);
                ts[3] = new Tile(1,2,color,pos);
                break;
            }
            
            default:
                break;
        }

        tiles = ts;
        SetTiles(tiles);
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

        System.out.println("Moved "+pos.ToString());
    }

    public Pos GetPos(){
        return new Pos(pos);
    }

    public Tile GetTile(int i){
        return tiles[i];
    }

   
    public enum Type{
        I,
        O,
        T,
        S,
        Z,
        J,
        L;

        public static Type getRandomType() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

    }

}