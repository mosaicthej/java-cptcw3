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

/**
 *
 * @author rxy
 */
public class DataPacketException extends Exception{

    static void retrivedID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int id;
    String message;
    public DataPacketException(int id,String message) {
        this.id = id;
        this.message = message;
    }
    public void retrieveId() {
        JFrame jf = new JFrame();
        jf.setTitle("Error Message");
        jf.setSize(500,150);
        jf.setLayout(null);
        JLabel jc = new JLabel(toString());
        JButton jz = new JButton("OK");
        jf.add(jc);
        jf.add(jz);
        jc.setBounds(50,25,350,25);
        jz.setBounds(225,100,35,30);
        jz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);}
        });
            jf.setVisible(true);
      }
    
    @Override
    public String toString(){
        return "DataPackageException:Exception ID = " + id + "," + "Exception Message = " + message;
    }
        
        
        
        
    }       
    

    

