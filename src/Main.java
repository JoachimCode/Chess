import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JFrame root = new JFrame();
        JLayeredPane layeredPane = root.getLayeredPane();
        Moves move_checker = new Moves();

        //Make board
        for (int i = 1; i <= 64; i++){
            Square board_piece = new Square(i, layeredPane);
            board_piece.square.addActionListener(e -> board_piece.get_cord());
        }

        //Setup white list
        java.util.List<Piece> white_pieces = new ArrayList<Piece>();

        //Setup white pawns
        for (int i = 1; i <= 8; i++) {
            Piece pawn = new Piece("pawn", 0, i, layeredPane);
            white_pieces.add(pawn);
        }

        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(1920,1080);
        root.setLayout(null);
        root.setVisible(true);

    }
}