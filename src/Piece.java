import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Piece {
    private boolean start = true;
    private int x_cor;
    private int y_cor;
    private int width = 100;
    private int height = 100;
    private String name;
    private String type;
    private boolean is_white;
    private JButton piece;
    private JLayeredPane frame;
    public Piece(String piece, boolean is_white, int number, JLayeredPane layeredPane){
        frame = layeredPane;
        //Pawn
        if(piece == "pawn" && is_white){
            type = "pawn";
            name = "WPawn";
            this.is_white = true;
            x_cor = 600 + 100*(number);
            y_cor = 700;
            this.piece = new JButton("Wpawn");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }
        else if(piece == "pawn" && !is_white){
            type = "pawn";
            name = "Bpawn";
            this.is_white = false;
            x_cor = 600 + 100*(number);
            y_cor = 200;
            this.piece = new JButton("Bpawn");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }
        else if(piece == "bishop" && is_white){
            type = "bishop";
            name = "wbishop";
            this.is_white = true;
            switch(number){
                case 1:
                    x_cor = 900;
                    break;
                case 2:
                    x_cor = 1200;
                    break;
            }
            y_cor = 800;
            this.piece = new JButton("WBishop");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "bishop" && !is_white){
            type = "bishop";
            name = "Bbishop";
            this.is_white = false;
            switch(number){
                case 1:
                    x_cor = 900;
                    break;
                case 2:
                    x_cor = 1200;
                    break;
            }
            y_cor = 100;
            this.piece = new JButton("BBishop");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "knight" && is_white){
            type = "knight";
            name = "Wknight";
            this.is_white = true;
            switch(number){
                case 1:
                    x_cor = 800;
                    break;
                case 2:
                    x_cor = 1300;
                    break;
            }
            y_cor = 800;
            this.piece = new JButton("WKnight");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
            }

        else if(piece == "knight" && !is_white){
            type = "knight";
            name = "Bknight";
            this.is_white = false;
            switch(number){
                case 1:
                    x_cor = 800;
                    break;
                case 2:
                    x_cor = 1300;
                    break;
            }
            y_cor = 100;
            this.piece = new JButton("BKnight");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "rook" && is_white){
            type = "rook";
            name = "WRook";
            this.is_white = true;
            switch(number){
                case 1:
                    x_cor = 700;
                    break;
                case 2:
                    x_cor = 1400;
                    break;
            }
            y_cor = 800;
            this.piece = new JButton("WRook");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "rook" && !is_white){
            type = "rook";
            name = "BRook";
            this.is_white = false;
            switch(number){
                case 1:
                    x_cor = 700;
                    break;
                case 2:
                    x_cor = 1400;
                    break;
            }
            y_cor = 100;
            this.piece = new JButton("BRook");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "queen" && is_white){
            type = "queen";
            name = "WQueen";
            this.is_white = true;
            x_cor = 1000;
            y_cor = 800;
            this.piece = new JButton("WQueen");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "queen" && !is_white){
            type = "queen";
            name = "BQueen";
            this.is_white = false;
            x_cor = 1100;
            y_cor = 100;
            this.piece = new JButton("BQueen");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "king" && is_white){
            type = "king";
            name = "WKing";
            this.is_white = true;
            x_cor = 1100;
            y_cor = 800;
            this.piece = new JButton("WKing");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "king" && !is_white){
            type = "king";
            name = "BKing";
            this.is_white = false;
            x_cor = 1000;
            y_cor = 100;
            this.piece = new JButton("BKing");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        }


    public void move(int[] cords){
        int x = cords[0];
        int y = cords[1];
        piece.setBounds(x,y,100,100);
        this.x_cor = x;
        this.y_cor = y;
        start = false;
    }

    public void show(){
        piece.setText("selected");

    }

    public void remove_black(java.util.List<Piece> black_pieces, Piece current_piece){
        black_pieces.remove(current_piece);
        frame.remove(piece);
    }

    public void remove_white(java.util.List<Piece> white_pieces, Piece current_piece){
        white_pieces.remove(current_piece);
        frame.remove(piece);
    }
    public void de_select(){
        piece.setText(name);
    }

    public String get_type(){
        return type;
    }
    public boolean get_white(){
        return is_white;
    }
    public int get_x(){
        return x_cor;
    }

    public int get_y(){
        return y_cor;
    }

    public boolean get_start(){
        return start;
    }
    public JButton get_piece(){
        return piece;
    }

}
