package cpt105.cw3;

import javax.swing.*;
import java.awt.*;

public class Q2 {
    public static void main(String[] args) {

        JFrame q1Frame = new JFrame("Mutiple Data Package Convert");
        q1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q1Frame.setVisible(true);
        JButton b_d2p = new JButton("Data to package");
        JButton b_p2d = new JButton("Package to Data");
        JLabel l_rd1 = new JLabel("Raw data 1");
        JLabel l_rd2 = new JLabel("Raw data 2");
        JLabel l_rd3 = new JLabel("Raw data 3");
        JLabel l_mdp = new JLabel("Multi data package");
        JTextField t_rd1 = new JTextField("some text");
        JTextField t_rd2 = new JTextField("some text");
        JTextField t_rd3 = new JTextField("some text");
        JTextField t_mdp = new JTextField("some text");


        q1Frame.setSize(350,700);
        q1Frame.setLayout(new BorderLayout());
        q1Frame.add(l_rd1); q1Frame.add(t_rd1);
        q1Frame.add(l_rd2); q1Frame.add(t_rd2);
        q1Frame.add(l_rd3); q1Frame.add(t_rd3);
        q1Frame.add(l_mdp);  q1Frame.add(t_mdp);
        q1Frame.add(b_d2p);
        q1Frame.add(b_p2d);

        // setting location on left
        l_rd1.setBounds(35, 80, 100, 30);
        l_mdp.setBounds(35, 160, 100, 30);
        b_d2p.setBounds(35, 240, 200, 50);

        // setting location on right
        t_rd1.setBounds(235, 80, 200, 60);
        t_mdp.setBounds(235, 160, 200, 60);
        b_p2d.setBounds(235, 240, 200, 50);

    }

}
