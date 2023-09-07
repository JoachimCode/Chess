import java.util.stream.*;
import java.util.ArrayList;
public class Engine {
    public Piece last_piece;
    public Piece current_piece;
    private int phase = 0;
    private boolean white_turn = true;
    private java.util.List<int[]> move_set;
    private int[] destination;
    private java.util.List white_pieces;
    private java.util.List black_pieces;

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
            System.out.println("Take piece !!");
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
        return true;
    }


    public void move_piece(int[] cords){
        last_piece.move(cords);
        phase = 0;
        move_set.clear();
        white_turn = !white_turn;
    }

    public void get_list(java.util.List white_list, java.util.List black_list){
        white_pieces = white_list;
        black_pieces = black_list;
    }
}
