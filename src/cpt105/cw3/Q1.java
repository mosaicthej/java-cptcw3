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

        q1Frame.setSize(500,350);
        q1Frame.setLayout(new BorderLayout(12,12));
        q1Frame.add(l_rd);
        q1Frame.add(t_rd);
        q1Frame.add(l_dp);
        q1Frame.add(t_dp);
        q1Frame.add(b_d2p);
        q1Frame.add(b_p2d);

        // setting location on left
        l_rd.setBounds(35, 80, 100, 30);
        l_dp.setBounds(35, 160, 100, 30);
        b_d2p.setBounds(35, 240, 200, 50);

        // setting location on right
        t_rd.setBounds(235, 80, 200, 60);
        t_dp.setBounds(235, 160, 200, 60);
        b_p2d.setBounds(235, 240, 200, 50);



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

        // when clicked on "package to data"
        b_p2d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // grab the text from 'Raw data' field
                // convert the text to package form
                // put the text to 'Data package'

                String dat = t_dp.getText();
                dat = dat.substring(4,dat.length()-6);

                String dat_str = "";
                byte i0, i1;
                char c;
                for (int i = 0; i < dat.length()/2; i++) {
                    i0 = Byte.parseByte(dat.substring(2*i,2*i+1));
                    i1 = Byte.parseByte(dat.substring(2*i+1,2*i+2));

                    i0 = (byte) (i0<<4);
                    i0 += i1;

                    c = (char) i0;
                    dat_str += c;
                }
                t_rd.setText(dat_str);
            }
        });


    }


}
