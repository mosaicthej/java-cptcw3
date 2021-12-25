/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3.partA;

import javax.swing.*;
import javax.swing.border.Border;

;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author
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
        q1Frame.setLayout(new BorderLayout());
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
                String message = t_rd.getText();
                String pak = DataPacket.convertToDataPacket(message);
                t_dp.setText(pak);
                Border b;
                b = t_dp.getBorder();

                System.out.println(t_dp.getX());
                System.out.println(t_dp.getY());

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
                // header: 1 byte, length: 1 byte
                // before = 2 byte = 4 char locations
                // CRC: 2 byte, tail: 1 byte
                // after = 3 byte = 6 char locations,

                // now dat contains the hex value of message string.
                // change from hex to string

                String dat_str = DataPacket.hexToString(dat);

                t_rd.setText(dat_str);
            }
        });


    }



}
