import javax.swing.*;
import java.util.ArrayList;

public class Moves {
    private java.util.List<int[]> possible_moves = new ArrayList<int[]>();
    public java.util.List<int[]> get_moves(Piece piece, int color){
        possible_moves.clear();

        //check white pawns
        if(piece.get_type() == "pawn" && piece.get_color() == 0){
            int x = piece.get_x();
            int y = piece.get_y()-100;
            put_moves(x,y);
        }
        return possible_moves;
    }

    public void put_moves(int x, int y){
        int[] cords;
        cords = new int[2];
        cords[0] = x;
        cords[1] = y;
        possible_moves.add(cords);
    }

    public int move(String direction, String axis){
        return 1;
    }
}
