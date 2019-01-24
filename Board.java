import java.util.ArrayList;

public class Board {
    Tile[][] tiles;
    int height, width;

    Piece activePiece;

    public Board(int width, int height){
        this.height = height;
        this.width = width;
        tiles = new Tile[width][height];
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



}