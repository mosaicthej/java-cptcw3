/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.pkg2036598;

import java.awt.Color;
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
public class Q2 {
    public static void main(String[] args) {
    
       JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setVisible(true);
        frame.setTitle("Multiple Data Package Convert:");
        frame.setLayout(null);
        JLabel My1 = new JLabel("Name: Xinyi.Ren");
        JLabel My2 = new JLabel("Gender: Male");
        JLabel My3 = new JLabel("StuID: 2036598");
        JLabel ja = new JLabel("Raw data 1:");
        JLabel jb = new JLabel("Raw data 2:");
        JLabel jc = new JLabel("Raw data 3:");
        JLabel jd = new JLabel("Multi data package:");
        JTextField j1 = new JTextField();
        JTextField j2 = new JTextField();
        JTextField j3 = new JTextField();
        JTextField j4 = new JTextField();
        JButton B1 = new JButton("Data to multi package");
        JButton B2 = new JButton("Multi package to data");
        frame.add(My1);
        frame.add(My2);
        frame.add(My3);
        frame.add(ja);
        frame.add(jb);
        frame.add(jc);
        frame.add(jd);
        frame.add(j1);
        frame.add(j2);
        frame.add(j3);
        frame.add(j4);
        frame.add(B1);
        frame.add(B2);
        My1.setBounds(50,50,150, 20);
        My2.setBounds(50,80,150, 20);
        My3.setBounds(50,110,150,20);
        My1.setForeground(Color.red);
        My2.setForeground(Color.red);
        My3.setForeground(Color.red);
        ja.setBounds(50, 220, 150, 20);
        jb.setBounds(50, 340, 150, 20);
        jc.setBounds(50, 460, 150, 20);
        jd.setBounds(50, 580, 150, 20);
        B1.setBounds(50,660 ,200, 100 );
        j1.setBounds(325 ,180 ,200, 100);
        j2.setBounds(325, 300, 200, 100);
        j3.setBounds(325, 420, 200, 100);
        j4.setBounds(325, 540, 200, 100);
        B2.setBounds(325,660,200,100);
        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String[] text = new String[3];
        text[0] = j1.getText();
        text[1] = j2.getText();
        text[2] = j3.getText();
        String all = DataPacket.convertToDataPacket(text);
         j4.setText(all);
            }
   
        });
        B2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String[] all = MultiDataPacket.getMultiDataFromHexDataPacket(j4.getText());
        j1.setText(all[0]);
        if (all.length == 2){
            j2.setText(all[1]);
        }
            if (all.length == 3){
            j3.setText(all[2]);
        }
            }
            
        });
        DataPacketException.retrivedID();
    }
}
