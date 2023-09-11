import java.util.ArrayList;
import java.util.Objects;

public class Moves {
    private java.util.List<int[]> possible_moves = new ArrayList<int[]>();

    public void get_moves(Piece piece, boolean is_white, Engine engine){

        possible_moves.clear();
        //check white pawns
        if(Objects.equals(piece.get_type(), "pawn") && piece.get_white() && engine.white_turn){
            //check if pawns has not moved
            if(piece.get_start()){
                white_pawn_move(piece, true);
            }
                white_pawn_move(piece, false);
        }

        //check black pawns
        if(Objects.equals(piece.get_type(), "pawn") && !piece.get_white() && !engine.white_turn){
            //check if pawns has not moved
            if(piece.get_start()){
                black_pawn_move(piece, true);
            }
            black_pawn_move(piece, false);
        }

        engine.set_move(possible_moves);
        engine.click_piece(piece);
    }

    public void get_cords(Piece piece, Engine engine){
        int[] cords;
        cords = new int[2];
        cords[0] = piece.get_x();
        cords[1] = piece.get_y();
        engine.piece_cords = cords;
        engine.current_piece = piece;
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

    //MOVE CALCULATOR
    private void white_pawn_move(Piece piece, boolean is_start){
        int x = piece.get_x();
        int y;
        if(is_start){
            y = piece.get_y() + move("up") * 2;
        }
        else {
            y = piece.get_y() + move("up");
        }
        put_moves(x,y);
    }

    private void black_pawn_move(Piece piece, boolean is_start){
        int x = piece.get_x();
        int y;
        if(is_start){
            y = piece.get_y() + move("down") * 2;
        }
        else {
            y = piece.get_y() + move("down");
        }
        put_moves(x,y);
    }
}

