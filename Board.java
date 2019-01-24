import java.util.ArrayList;

public class Board {
    Tile[][] tiles;
    int height, width;

    Piece activePiece;

    public Board(int width, int height){
        this.height = height+5;//includes hidden top rows
        this.width = width;
        tiles = new Tile[this.width][this.height];
        System.out.println(tiles.length);
        System.out.println(tiles[0].length);
    }

    public void Tick(){
        if(activePiece==null){
            activePiece = Piece.Random(new Pos(3,21), this);
            AddPiece(activePiece);

        }
        else{
            activePiece.Move(Direction.Down);
        }
    }

    void AddPiece(Piece p){
        for(int i = 0; i<4; i++){
            AddTile(p.GetTile(i));
        }
    }

    void AddTile(Tile t){
        Pos p = t.GetPos();
        System.out.println("Added tile "+p.x+","+p.y);
        tiles[p.x][p.y]=t;

    }

    public void ClearLine(int i){

    }

    public ArrayList<Tile> GetTiles(){
        ArrayList<Tile> t = new ArrayList<Tile>();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(tiles[x][y]!=null){
                    t.add(tiles[x][y]);
                }

            }   
        }

        return t;

    }

    public Piece GetActivePiece(){
        return activePiece;
    }



}