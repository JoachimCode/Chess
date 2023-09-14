import java.util.stream.*;
import java.util.ArrayList;
public class Engine {
    public Piece last_piece;
    public Piece current_piece;
    private int phase = 0;
    public boolean white_turn = true;
    private java.util.List<int[]> move_set;
    private int[] destination;
    private java.util.List<Piece> white_pieces;
    private java.util.List<Piece> black_pieces;
    public int[] piece_cords;

    public void set_move(java.util.List<int[]> moves){
        move_set = moves;
    }

    public void click_piece(Piece piece){
        if (white_turn && piece.get_white()) {
            if (last_piece != null) {
                last_piece.de_select();
            }
            last_piece = piece;
            piece.show();
            phase = 1;
        }

        else if (!white_turn && !piece.get_white()){
            if (last_piece != null) {
                last_piece.de_select();
            }
            last_piece = piece;
            piece.show();
            phase = 1;
        }

        else if(phase == 1 && white_turn && !piece.get_white()){
            int x = piece.get_x();
            int y = piece.get_y();
            int[] cords = {x,y};
            if(check_white(cords)) {
                if(check_white_take(piece_cords)) {
                    move_piece(piece_cords);
                    current_piece.remove_black(black_pieces, current_piece);
                }
            }
        }

        else if(phase == 1 && !white_turn && piece.get_white()){
            int x = piece.get_x();
            int y = piece.get_y();
            int[] cords = {x,y};
            if(check_black(cords)){
                if(check_black_take(piece_cords)){
                    move_piece(piece_cords);
                    current_piece.remove_white(white_pieces, current_piece);
                }
            }
        }
    }

    public void click_empty(int[] cords){
        destination = cords;
        //white turn
        if(phase == 1 && white_turn){
            //boolean is_true = move_set.contains(cords);
            if(check_white(cords)) {
                move_piece(cords);
            }
        }
        //black turn
        else if(phase == 1 && !white_turn){
            //boolean is_true = move_set.contains(cords);
            if(check_black(cords)) {
                move_piece(cords);
            }
        }
    }

    public boolean check_white(int[] cords){
        return move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]) && last_piece.get_white();
    }

    public boolean check_black(int[] cords){
        return move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]) && !last_piece.get_white();
    }

    public boolean check_white_take(int[] cords){
        move_set.add(cords);
        if(check_white(cords)){
            move_set.clear();
        for(Piece take_list : black_pieces){
            int piece_x = take_list.get_x();
            int piece_y = take_list.get_y();
            if(cords[0] == piece_x && cords[1] == piece_y) {
                return true;
                }
            }
        }
        return false;
        }

    public boolean check_black_take(int[] cords){
        move_set.add(cords);
        if(check_black(cords)){
            move_set.clear();
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if(cords[0] == piece_x && cords[1] == piece_y) {
                    return true;
                }
            }
        }
        return false;
    }
    public void move_piece(int[] cords){
        last_piece.move(cords);
        last_piece.de_select();
        phase = 0;
        move_set.clear();
        white_turn = !white_turn;
    }

    public void get_list(java.util.List<Piece> white_list, java.util.List<Piece> black_list){
        white_pieces = white_list;
        black_pieces = black_list;
    }

    public boolean right_turn(Piece piece){
        if(piece.get_white() == white_turn){
            return true;
        }
        else if(!piece.get_white() == !white_turn){
            return true;
        }
        else{
            return false;

        }
    }
}
