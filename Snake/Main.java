import java.awt.Color;

import javax.swing.JFrame;

class Main{
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        gameplay gp = new gameplay();

        obj.setBounds(10, 10, 905, 700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gp);
    }
}