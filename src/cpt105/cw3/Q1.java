/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3;

import javax.swing.*;
import javax.xml.crypto.Data;

;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author zhaoyihan
 */
public class Q1{
    public static void main(String[] args) {
        JFrame q1Frame = new JFrame("Data Package Convert");
        q1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q1Frame.setVisible(true);
        JButton b_d2p = new JButton("Data to package");
        JButton b_p2d = new JButton("Package to Data");
        JLabel l_rd = new JLabel("Raw data");
        JTextField t_rd = new JTextField();
        JLabel l_dp = new JLabel("Data package");
        JTextField t_dp = new JTextField();

        q1Frame.setLayout(null);
        q1Frame.add(l_rd);
        q1Frame.add(t_rd);
        q1Frame.add(l_dp);
        q1Frame.add(t_dp);
        q1Frame.add(b_d2p);
        q1Frame.add(b_p2d);


        b_d2p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // grab the text from 'Raw data' field
                // convert the text to package form
                // put the text to 'Data package'
                String pak = DataPacket.convertToDataPacket(t_rd.getText());
                t_dp.setText(pak);
            }
        });


    }
}
