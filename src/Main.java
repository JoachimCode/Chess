import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JFrame root = new JFrame();
        JLayeredPane layeredPane = root.getLayeredPane();
        Moves move_checker = new Moves();

        //game engine
        Engine engine = new Engine();

        //Make board
        for (int i = 1; i <= 64; i++){
            Square board_piece = new Square(i, layeredPane, engine);
            board_piece.square.addActionListener(e -> board_piece.get_cord());
        }

        //Setup white list
        java.util.List<Piece> white_pieces = new ArrayList<Piece>();

        //Setup white pawns
        for (int i = 1; i <= 8; i++) {
            Piece white_pawn = new Piece("pawn", true, i, layeredPane);
            white_pawn.get_piece().addActionListener(e -> move_checker.get_moves(white_pawn, true, engine));
            white_pieces.add(white_pawn);
        }

        //Setup black pawns
        for (int i = 1; i <= 8; i++) {
            Piece black_pawn = new Piece("pawn", false, i, layeredPane);
            black_pawn.get_piece().addActionListener(e -> move_checker.get_moves(black_pawn, true, engine));
            //make black hashmap !!!black_pieces.add(white_pawn);
        }


        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(1920,1080);
        root.setLayout(null);
        root.setVisible(true);

    }
}