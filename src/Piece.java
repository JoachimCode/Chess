import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;

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
            Image k;
            try {
                k = ImageIO.read(new File("white_pawn.png"));
            }
            catch (Exception lol){
                k = null;
            }
            k = k.getScaledInstance(100, 100,0);
            this.piece = new JButton(new ImageIcon(k));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }
        else if(piece == "pawn" && !is_white){
            type = "pawn";
            name = "Bpawn";
            this.is_white = false;
            x_cor = 600 + 100*(number);
            y_cor = 200;
            Image k;
            try {
                k = ImageIO.read(new File("black_pawn.png"));
            }
            catch (Exception lol){
                k = null;
            }
            k = k.getScaledInstance(100, 100,0);
            this.piece = new JButton(new ImageIcon(k));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("white_bishop.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("black_bishop.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("white_knight.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("black_knight.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("white_rook.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
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
            this.piece = new JButton(new ImageIcon(get_image("black_rook.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "queen" && is_white){
            type = "queen";
            name = "WQueen";
            this.is_white = true;
            x_cor = 1000;
            y_cor = 800;
            this.piece = new JButton(new ImageIcon(get_image("white_queen.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "queen" && !is_white){
            type = "queen";
            name = "BQueen";
            this.is_white = false;
            x_cor = 1000;
            y_cor = 100;
            this.piece = new JButton(new ImageIcon(get_image("black_queen.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "king" && is_white){
            type = "king";
            name = "WKing";
            this.is_white = true;
            x_cor = 1100;
            y_cor = 800;
            this.piece = new JButton(new ImageIcon(get_image("white_king.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

        else if(piece == "king" && !is_white){
            type = "king";
            name = "BKing";
            this.is_white = false;
            x_cor = 1100;
            y_cor = 100;
            this.piece = new JButton(new ImageIcon(get_image("black_king.png")));
            this.piece.setContentAreaFilled(false);
            this.piece.setBorderPainted(false);
            this.piece.setBounds(x_cor, y_cor, width, height);
            layeredPane.add(this.piece, JLayeredPane.PALETTE_LAYER);
        }

    }

    public String get_piece_type(){
        return type;
    }

    public Image get_image(String image_path){
        Image image;
        try {
            image = ImageIO.read(new File(image_path));
        }
        catch (Exception lol){
            image = null;
        }
        image = image.getScaledInstance(100, 100,0);
        return image;
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
        piece.setBorderPainted(true);
        piece.setBorder(BorderFactory.createLineBorder(Color.yellow, 4));

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
        piece.setBorderPainted(false);


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
