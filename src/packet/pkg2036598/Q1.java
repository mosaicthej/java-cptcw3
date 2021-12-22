/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.pkg2036598;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author rxy
 */
public class Q1 {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(675, 400);
        frame.setVisible(true);
        frame.setTitle("Data packet Convert:");
        frame.setLayout(null);
        JLabel jb = new JLabel("Raw data:");
        JLabel jt = new JLabel("Data packet:");
        JTextField j1 = new JTextField();
        JTextField j2 = new JTextField();
        JButton jx = new JButton("Data to Package");
        JButton jy = new JButton("package to Data");
        frame.add(jb);
        frame.add(j1);
        frame.add(jt);
        frame.add(j2);
        frame.add(jx);
        frame.add(jy);
        jb.setBounds(25, 50, 100, 50);
        jt.setBounds(25, 150, 100, 50);
        jx.setBounds(25, 250, 300, 75);
        j1.setBounds(350, 40, 275, 75);
        j2.setBounds(350, 150, 275, 75);
        jy.setBounds(350, 250, 275, 75);
        
        jx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Rawdata = j1.getText();
                if (Rawdata == null) {
                    j2.setText("wrong!");
                } else {
                    String packet = DataPacket.convertToDataPacket(Rawdata);
                    j2.setText(packet);
                }

            }

        });
        jy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String PTD = j2.getText();
                if (PTD == null) {
                    j1.setText("wrong!");
                } else {
                    String data = DataPacket.getDataFromDataPacket(PTD);
                    j1.setText(data);
                }

            }

        });
    }
}
