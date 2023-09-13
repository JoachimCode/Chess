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
            layeredPane.add(this.piece, 1);
        }
        else if(piece == "pawn" && !is_white){
            type = "pawn";
            name = "Bpawn";
            this.is_white = false;
            x_cor = 600 + 100*(number);
            y_cor = 200;
            this.piece = new JButton("Bpawn");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, 1);
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
            layeredPane.add(this.piece, 1);
        }

        else if(piece == "bishop" && !is_white){
            type = "bishop";
            name = "bbishop";
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
            layeredPane.add(this.piece, 1);
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
