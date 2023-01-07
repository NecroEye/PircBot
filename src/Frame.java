import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Frame extends JFrame {

    private JFrame frame;
    private Sound sound;
    private JLabel label;



    public Frame(String message){

        this.frame = new JFrame();
        this.label = new JLabel();
        this.sound = new Sound();

        sound.doom();

        label.setText(message);
        label.setBounds(75,75,250,75);
        label.setFont(new Font("Calibri", Font.BOLD, 19));
        label.setForeground(Color.BLACK);
        frame.add(label);

        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        frame.setSize(400,350);
        frame.setTitle("CursedEye'dan Selamlar");
        frame.setLocation(new Random().nextInt(550),new Random().nextInt(750));
        frame.setForeground(Color.PINK);
        frame.setLayout(null);
        frame.setVisible(true);


    }

}
