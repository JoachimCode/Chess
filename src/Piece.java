import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Piece {
    private boolean start = true;
    private int x_cor;
    private int y_cor;
    private int width = 100;
    private int height = 100;
    private String type;
    private int color;
    private JButton piece;
    public Piece(String piece, int color, int number, JLayeredPane layeredPane){
        //Pawn
        if(piece == "pawn" && color == 0){
            type = "pawn";
            this.color = 0;
            x_cor = 600 + 100*(number);
            y_cor = 700;
            this.piece = new JButton("Wpawn");
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, 1);
        }


    }

    public void move(){

    }

    public String get_type(){
        return type;
    }
    public int get_color(){
        return color;
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
}
