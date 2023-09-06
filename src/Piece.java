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
    public Piece(String piece, boolean is_white, int number, JLayeredPane layeredPane){
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

    }

    public void move(int[] cords){
        int x = cords[0];
        int y = cords[1];
        piece.setBounds(x,y,100,100);
    }

    public void show(){
        piece.setText("selected");

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
