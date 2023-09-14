import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
//KNIGHT CANT TAKE
//CLICK PIECE THEN ENEMY GET THEIR POSSIBLE MOVES
public class Main {
    public static void main(String[] args) {
        JFrame root = new JFrame();
        JLayeredPane layeredPane = root.getLayeredPane();
        java.util.List<Piece> white_pieces = new ArrayList<Piece>();
        java.util.List<Piece> black_pieces = new ArrayList<Piece>();
        Moves move_checker = new Moves(black_pieces, white_pieces);

        //game engine
        Engine engine = new Engine();

        //Make board
        for (int i = 1; i <= 64; i++) {
            Square board_piece = new Square(i, layeredPane, engine);
            board_piece.square.addActionListener(e -> board_piece.get_cord());
        }

        //Setup both list

        //Setup white pawns
        for (int i = 1; i <= 8; i++) {
            Piece white_pawn = new Piece("pawn", true, i, layeredPane);
            white_pawn.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(white_pawn, engine);
                    move_checker.get_moves(white_pawn, true, engine);
                }
            });
            white_pieces.add(white_pawn);
        }

        //Setup black pawns
        for (int i = 1; i <= 8; i++) {
            Piece black_pawn = new Piece("pawn", false, i, layeredPane);
            black_pawn.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(black_pawn, engine);
                    move_checker.get_moves(black_pawn, true, engine);
                }
            });
            black_pieces.add(black_pawn);
        }

        //Setup white bishiops
        for (int i = 1; i < 3; i++) {
            Piece white_bishop = new Piece("bishop", true, i, layeredPane);
            white_bishop.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(white_bishop, engine);
                    move_checker.get_moves(white_bishop, true, engine);
                }
            });
            white_pieces.add(white_bishop);
        }
        //black bishops
        for (int i = 1; i < 3; i++) {
            Piece black_bishop = new Piece("bishop", false, i, layeredPane);
            black_bishop.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(black_bishop, engine);
                    move_checker.get_moves(black_bishop, false, engine);
                }
            });
            black_pieces.add(black_bishop);
        }
        //white knight
        for (int i = 1; i < 3; i++) {
            Piece white_knight = new Piece("knight", true, i, layeredPane);
            white_knight.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(white_knight, engine);
                    move_checker.get_moves(white_knight, true, engine);
                }
            });
            white_pieces.add(white_knight);
        }

        for (int i = 1; i < 3; i++) {
            Piece black_knight = new Piece("knight", false, i, layeredPane);
            black_knight.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(black_knight, engine);
                    move_checker.get_moves(black_knight, false, engine);
                }
            });
            black_pieces.add(black_knight);
        }

            // parse lists into moves
            engine.get_list(white_pieces, black_pieces);
            root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            root.setSize(1920, 1080);
            //root.setLayout(null);
            root.setVisible(true);

        }
    }
