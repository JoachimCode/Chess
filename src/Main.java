import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
                    move_checker.get_moves(white_pawn, engine);
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
                    move_checker.get_moves(black_pawn, engine);
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
                    move_checker.get_moves(white_bishop, engine);
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
                    move_checker.get_moves(black_bishop, engine);
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
                    move_checker.get_moves(white_knight, engine);
                }
            });
            white_pieces.add(white_knight);
        }
        //black knight
        for (int i = 1; i < 3; i++) {
            Piece black_knight = new Piece("knight", false, i, layeredPane);
            black_knight.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(black_knight, engine);
                    move_checker.get_moves(black_knight,engine);
                }
            });
            black_pieces.add(black_knight);
        }

        //white rook
        for (int i = 1; i < 3; i++) {
            Piece white_rook = new Piece("rook", true, i, layeredPane);
            white_rook.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(white_rook, engine);
                    move_checker.get_moves(white_rook, engine);
                }
            });
            white_pieces.add(white_rook);
        }
        //black rook
        for (int i = 1; i < 3; i++) {
            Piece black_rook = new Piece("rook", false, i, layeredPane);
            black_rook.get_piece().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move_checker.get_cords(black_rook, engine);
                    move_checker.get_moves(black_rook, engine);
                }
            });
            black_pieces.add(black_rook);
        }
        //White queen
        Piece white_queen = new Piece("queen", true, 1, layeredPane);
        white_queen.get_piece().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move_checker.get_cords(white_queen, engine);
                move_checker.get_moves(white_queen,engine);
            }
        });
        white_pieces.add(white_queen);

        //Black queen
        Piece black_queen = new Piece("queen", false, 1, layeredPane);
        black_queen.get_piece().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move_checker.get_cords(black_queen, engine);
                move_checker.get_moves(black_queen,  engine);
            }
        });
        black_pieces.add(black_queen);

        //white King
        Piece white_king = new Piece("king", true, 1, layeredPane);
        white_king.get_piece().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move_checker.get_cords(white_king, engine);
                move_checker.get_moves(white_king,engine);
            }
        });
        white_pieces.add(white_king);

        //Black king
        Piece black_king = new Piece("king", false, 1, layeredPane);
        black_king.get_piece().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move_checker.get_cords(black_king, engine);
                move_checker.get_moves(black_king, engine);
            }
        });
        engine.get_king(black_king, white_king);
        black_pieces.add(black_king);

        // parse lists into moves
        engine.get_list(white_pieces, black_pieces);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(1920, 1080);
        //root.setLayout(null);
        root.setVisible(true);

        }
    }
