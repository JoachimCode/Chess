import java.util.ArrayList;
import java.util.Objects;

public class Moves {
    private java.util.List<Piece> white_pieces;
    private java.util.List<Piece> black_pieces;
    private java.util.List<int[]> possible_moves = new ArrayList<int[]>();
    private java.util.List<int[]> pawn_possible_moves = new ArrayList<int[]>();

    public Moves(java.util.List<Piece> black_pieces, java.util.List<Piece> white_pieces){
        this.black_pieces = black_pieces;
        this.white_pieces = white_pieces;
    }

    public void get_moves(Piece piece, boolean is_white, Engine engine){
        if(engine.right_turn(piece)){
            pawn_possible_moves.clear();
            possible_moves.clear();
            //check white pawns
            if (Objects.equals(piece.get_type(), "pawn") && piece.get_white() && engine.white_turn) {
                //check if pawns has not moved
                if (piece.get_start()) {
                    white_pawn_move(piece, true);
                }
                white_pawn_move(piece, false);

                //pawn take
                if (piece.get_white()){
                    white_pawn_take_move(piece);
                }
                engine.pawn_move_set = pawn_possible_moves;
            }

            //check black pawns
            if (Objects.equals(piece.get_type(), "pawn") && !piece.get_white() && !engine.white_turn) {
                //check if pawns has not moved
                if (piece.get_start()) {
                    black_pawn_move(piece, true);
                }
                black_pawn_move(piece, false);
                //pawn take
                if (!piece.get_white()){
                    black_pawn_take_move(piece);
                }
                engine.pawn_move_set = pawn_possible_moves;
            }

            //check bishops
            if (Objects.equals(piece.get_type(), "bishop")) {
                bishop_move(piece);
            }
            engine.set_move(possible_moves);
            //engine.click_piece(piece);

            //check knight
            if (Objects.equals(piece.get_type(), "knight")) {
                knight_move(piece);
            }
            engine.set_move(possible_moves);

            //check rook
            if (Objects.equals(piece.get_type(), "rook")) {
                rook_move(piece);
            }
            engine.set_move(possible_moves);

            //check queen
            if (Objects.equals(piece.get_type(), "queen")) {
                queen_move(piece);
            }
            engine.set_move(possible_moves);
            if (Objects.equals(piece.get_type(), "king")) {
                king_move(piece, true);
            }
            engine.set_move(possible_moves);
        }
        engine.click_piece(piece);
    }

    public void get_list(){

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

    public void put_moves_pawn(int x, int y){
        int[] cords;
        cords = new int[2];
        cords[0] = x;
        cords[1] = y;
        pawn_possible_moves.add(cords);
    }

    public int move(String direction){
        if(direction == "up" || direction == "left"){
            return -100;
        }
        else{
            return 100;
        }
    }

    //MOVE CALCULATOR

    private void king_move(Piece piece, boolean is_start){
        int x = piece.get_x();
        int y = piece.get_y();
        //same x
        y = y + move("up");
        put_moves(x,y);
        y = y + move("down")*2;
        put_moves(x,y);
        x = x + move("left");
        put_moves(x,y);
        x = x + move("right")*2;
        put_moves(x,y);
        y = y + move("up");
        put_moves(x,y);
        y = y + move("up");
        put_moves(x,y);
        x = x + move("left")*2;
        put_moves(x,y);
        y = y + move("down");
        put_moves(x,y);

    }
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


    private void white_pawn_take_move(Piece piece){
        int y = piece.get_y() + move("up");
        int x = piece.get_x() + move("left");
        put_moves_pawn(x,y);
        x = x + move("right")*2;
        put_moves_pawn(x,y);
    }

    private void black_pawn_take_move(Piece piece){
        int y = piece.get_y() + move("down");
        int x = piece.get_x() + move("left");
        put_moves_pawn(x,y);
        x = x + move("right")*2;
        put_moves_pawn(x,y);
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

    private void knight_move(Piece piece){
        int x;
        int y;
        //upwards
        y = piece.get_y() + move("up")*2;
        x = piece.get_x() + move("right");
        put_moves(x,y);
        x = x + move("left")*2;
        put_moves(x,y);

        // right
        x = piece.get_x() + move("right")*2;
        y = piece.get_y() + move("up");
        put_moves(x,y);
        y = y + move("down")*2;
        put_moves(x,y);

        //down
        y = piece.get_y() + move("down")*2;
        x = piece.get_x() + move("right");
        put_moves(x,y);
        x = x + move("left")*2;
        put_moves(x,y);

        // left
        x = piece.get_x() + move("left")*2;
        y = piece.get_y() + move("up");
        put_moves(x,y);
        y = y + move("down")*2;
        put_moves(x,y);
    }
    private void bishop_move(Piece piece) {
        int x;
        int y;
        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("right") * i;
            y = piece.get_y() + move("up") * i;

            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }

            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }

            if(exit){
                break;
            }
            put_moves(x, y);
        }
        for (int i = 1; i <= 8; i++) {
            x = piece.get_x() + move("left") * i;
            y = piece.get_y() + move("up") * i;
            boolean exit = false;
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }

        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("left") * i;
            y = piece.get_y() + move("down") * i;
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }
        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("right") * i;
            y = piece.get_y() + move("down") * i;
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }
    }
    private void rook_move(Piece piece) {
        int x;
        int y;
        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("right") * i;
            y = piece.get_y() + move("up") * i;

            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }

            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }

            if(exit){
                break;
            }
            put_moves(x, y);
        }
        for (int i = 1; i <= 8; i++) {
            x = piece.get_x();
            y = piece.get_y() + move("up") * i;
            boolean exit = false;
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }

        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x();
            y = piece.get_y() + move("down") * i;
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }
        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("right") * i;
            y = piece.get_y();
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }
        for (int i = 1; i <= 8; i++) {
            boolean exit = false;
            x = piece.get_x() + move("left") * i;
            y = piece.get_y();
            for(Piece take_list : black_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            for(Piece take_list : white_pieces){
                int piece_x = take_list.get_x();
                int piece_y = take_list.get_y();
                if (x == piece_x && y == piece_y) {
                    put_moves(x,y);
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
            put_moves(x, y);
        }
    }
    public void queen_move(Piece piece){
        rook_move(piece);
        bishop_move(piece);
    }
    }

