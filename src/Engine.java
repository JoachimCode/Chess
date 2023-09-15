import java.util.stream.*;
import java.util.ArrayList;
public class Engine {
    public Piece last_piece;
    public Piece current_piece;
    private int phase = 0;
    public boolean white_turn = true;
    private java.util.List<int[]> move_set;
    private java.util.List<int[]> check_move_set;
    public java.util.List<int[]> pawn_move_set;
    private int[] destination;
    private java.util.List<Piece> white_pieces;
    private java.util.List<Piece> black_pieces;
    public int[] piece_cords;
    private Piece white_king;
    private Piece black_king;
    private boolean is_black_check;
    private boolean is_white_check;
    Moves move_checker;

    public void get_king(Piece king, Piece white_king){
            this.white_king = white_king;
            this.black_king = king;
        }


    public boolean check(boolean is_white){
        boolean is_check = false;
        is_black_check = false;
        is_white_check = false;
        if(is_white){
            is_check = false;
            for(Piece this_piece : white_pieces){
                check_move_set.clear();
                move_checker.get_check(this_piece, this);
                int king_x = black_king.get_x();
                int king_y = black_king.get_y();
                int[] cords = {king_x,king_y};
                is_check = check_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]);
                if(is_check){
                    is_black_check = true;
                    return is_check;
                }
            }
        }
        else{
            is_check = false;
            for(Piece this_piece : black_pieces){
                check_move_set.clear();
                move_checker.get_check(this_piece, this);
                int king_x = white_king.get_x();
                int king_y = white_king.get_y();
                int[] cords = {king_x,king_y};
                is_check = check_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]);
                if(is_check){
                    is_white_check = true;
                    return is_check;
                }
            }
        }
        return is_check;
    }
    public boolean check_check(){
        move_checker.get_moves(last_piece, this);
        boolean is_yes = false;
        if(!white_turn){
            int x_cord = white_king.get_x();
            int y_cord = white_king.get_y();
            int[] cords = {x_cord,y_cord};
            is_yes = check_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]);
        }
        else {
            int x_cord = black_king.get_x();
            int y_cord = black_king.get_y();
            int[] cords = {x_cord,y_cord};
            is_yes = check_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]);
        }
        check_move_set.clear();
        return is_yes;
    }

    public void set_move(java.util.List<int[]> moves){
        move_set = moves;
    }

    public void set_check_move(java.util.List<int[]> moves){
        check_move_set = moves;
    }
    public void set_pawn_take_move(java.util.List<int[]> moves){
        pawn_move_set = moves;
    }
    public void click_piece(Piece piece){
        if (white_turn && piece.get_white()) {
            if (last_piece != null) {
                last_piece.de_select();
            }
            last_piece = piece;
            white_king.de_select();
            piece.show();
            phase = 1;
        }

        else if (!white_turn && !piece.get_white()){
            if (last_piece != null) {
                last_piece.de_select();
            }
            last_piece = piece;
            black_king.de_select();
            piece.show();
            phase = 1;
        }


        //Special pawn take statement
        else if(phase == 1 && white_turn && !piece.get_white() && last_piece.get_type() == "pawn"){
            int x = piece.get_x();
            int y = piece.get_y();
            int[] cords = {x,y};
            if(pawn_check_white(cords)) {
                if(check_white_pawn_take(piece_cords)) {
                    move_piece(piece_cords);
                    current_piece.remove_black(black_pieces, current_piece);
                }
            }
        }

        else if(phase == 1 && !white_turn && piece.get_white() && last_piece.get_type() == "pawn" ){
            int x = piece.get_x();
            int y = piece.get_y();
            int[] cords = {x,y};
            if(pawn_check_black(cords)) {
                if(check_black_pawn_take(piece_cords)) {
                    move_piece(piece_cords);
                    current_piece.remove_white(white_pieces, current_piece);
                }
            }
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

    public boolean pawn_check_white(int[] cords){
        return pawn_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]) && last_piece.get_white();
    }

    public boolean pawn_check_black(int[] cords){
        return pawn_move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1]) && !last_piece.get_white();
    }

    public boolean check_white_pawn_take(int[] cords){
        //move_set.add(cords);
        if(pawn_check_white(cords)){
            pawn_move_set.clear();
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

    public boolean check_black_pawn_take(int[] cords){
        //move_set.add(cords);
        if(pawn_check_black(cords)){
            pawn_move_set.clear();
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


    public boolean check_white_take(int[] cords){
        //move_set.add(cords);
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
        //move_set.add(cords);
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
        int prev_x = last_piece.get_x();
        int prev_y = last_piece.get_y();
        int[] prev_cords = {prev_x, prev_y};
        last_piece.move(cords);
        //Check if you get checked if you move;
        if(white_turn && check(false)){
            last_piece.move(prev_cords);
            white_king.check_show();
            return;
        }
        else if(!white_turn && check(true)){
            last_piece.move(prev_cords);
            black_king.check_show();
            return;
        }
        //Check if you get check
        if(white_turn && check(true)){
            black_king.check_show();
        }
        else if(!white_turn && check(false)){
            white_king.check_show();
        }
        last_piece.de_select();
        phase = 0;
        move_set.clear();
        white_turn = !white_turn;
    }

    public void get_list(java.util.List<Piece> white_list, java.util.List<Piece> black_list){
        white_pieces = white_list;
        black_pieces = black_list;
        move_checker = new Moves(white_pieces, black_pieces);
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
