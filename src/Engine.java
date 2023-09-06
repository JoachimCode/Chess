import java.util.stream.*;
public class Engine {
    public Piece last_piece;
    public Piece current_piece;
    private int phase = 0;
    private boolean white_turn = true;
    private java.util.List<int[]> move_set;
    private int[] destination;

    public void set_move(java.util.List<int[]> moves){
        move_set = moves;
    }

    public void click_piece(Piece piece){
        if (white_turn) {
            if (last_piece != null) {
                last_piece.de_select();
            }
            last_piece = piece;
            piece.show();
            phase = 1;
        }
    }

    public void click_empty(int[] cords){
        destination = cords;
        if(phase == 1){
            //boolean is_true = move_set.contains(cords);
            if(move_set.stream().anyMatch(x -> x[0] == cords[0] && x[1] == cords[1])) {
                last_piece.move(cords);
                move_set.clear();
            }
        }

    }
}
