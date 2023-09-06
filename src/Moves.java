import java.util.ArrayList;
import java.util.Objects;

public class Moves {
    private java.util.List<int[]> possible_moves = new ArrayList<int[]>();
    public void get_moves(Piece piece, boolean is_white, Engine engine){
        possible_moves.clear();
        //check white pawns
        if(Objects.equals(piece.get_type(), "pawn") && piece.get_white()){
            //check if pawns has not moved
            if(piece.get_start()){
                int x = piece.get_x();
                int y = piece.get_y()+move("up")*2;
                put_moves(x,y);
            }
            int x = piece.get_x();
            int y = piece.get_y()+move("up");
            put_moves(x,y);
        }
        //check black pawns
        if(Objects.equals(piece.get_type(), "pawn") && !piece.get_white()){
            //check if pawns has not moved
            if(piece.get_start()){
                int x = piece.get_x();
                int y = piece.get_y()+move("down")*2;
                put_moves(x,y);
            }
            int x = piece.get_x();
            int y = piece.get_y()+move("down");
            put_moves(x,y);
        }

        //System.out.println(possible_moves.get(0)[0]+","+possible_moves.get(0)[1] + "," + possible_moves.get(1)[0]+","+possible_moves.get(1)[1]);
        engine.set_move(possible_moves);
        engine.click_piece(piece);
    }

    public void put_moves(int x, int y){
        int[] cords;
        cords = new int[2];
        cords[0] = x;
        cords[1] = y;
        possible_moves.add(cords);
    }

    public int move(String direction){
        if(direction == "up" || direction == "right"){
            return -100;
        }
        else{
            return 100;
        }
    }
}

