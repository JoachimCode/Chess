import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square {
    private int x_cor;
    private int y_cor;
    private int row;
    private int width = 100;
    private int height = 100;
    private int[] cords;
    Engine engine;
    public JButton square;
    public Square(int number, JLayeredPane layeredPane, Engine engine){
        this.engine = engine;
        // Assign x and y coordinates
        if(number <= 8){
            x_cor = 600 + 100*number;
            y_cor = 800;
            row = 1;
        }
        else if(number <= 16){
            x_cor = 600 + 100*(number-8);
            y_cor = 700;
            row = 2;
        }
        else if(number <= 24){
            x_cor = 600 + 100*(number-16);
            y_cor = 600;
            row = 3;
        }
        else if(number <= 32){
            x_cor = 600 + 100*(number-24);
            y_cor = 500;
            row = 4;
        }
        else if(number <= 40){
            x_cor = 600 + 100*(number-32);
            y_cor = 400;
            row = 5;
        }
        else if(number <= 48){
            x_cor = 600 + 100*(number-40);
            y_cor = 300;
            row = 6;
        }
        else if(number <= 56){
            x_cor = 600 + 100*(number-48);
            y_cor = 200;
            row = 7;
        }
        else if(number <= 64){
            x_cor = 600 + 100*(number-56);
            y_cor = 100;
            row = 8;
        }
        //end

        // Check if odd or even and create button
        if(row % 2 != 0) {
            if (number % 2 == 0) {
                square = new JButton(new ImageIcon("white_square.jpg"));
            } else {
                square = new JButton(new ImageIcon("black_square.jpg"));
            }
        }
        else {
            if (number % 2 == 0) {
                square = new JButton(new ImageIcon("black_square.jpg"));
            } else {
                square = new JButton(new ImageIcon("white_square.jpg"));
            }
        }

        //Set up cords
        cords = new int[2];
        cords[0] = x_cor;
        cords[1] = y_cor;

        //Set Jbuttons coordinates and spawn
        square.setBounds(x_cor,y_cor,width,height);
        layeredPane.add(square, JLayeredPane.DEFAULT_LAYER);
    }

    public void get_cord(){
        System.out.println(String.valueOf(x_cor) + String.valueOf(y_cor));
        engine.click_empty(cords);
    }
}
