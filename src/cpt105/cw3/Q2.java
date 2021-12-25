package cpt105.cw3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Q2 extends JFrame{
    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public Q2(String title) throws HeadlessException {
        this.setTitle(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JButton b_d2p = new JButton("Data to package");
        JButton b_p2d = new JButton("Package to Data");
        JLabel l_info = new JLabel(
                "<html>" +
                        "Name: Ibuki Suika<br>" +
                        "Gender: Oni<br>" +
                        "StuID: 23333333" +
                        "</html>");

        ImageIcon pfp = new ImageIcon((this.getClass().getResource("suika.gif")));

        System.out.println(pfp);
        JLabel l_pfp = new JLabel(pfp);
        JLabel l_rd1 = new JLabel("Raw data 1");
        JLabel l_rd2 = new JLabel("Raw data 2");
        JLabel l_rd3 = new JLabel("Raw data 3");
        JLabel l_mdp = new JLabel("Multi data package");
        JTextField t_rd1 = new JTextField("");
        JTextField t_rd2 = new JTextField("");
        JTextField t_rd3 = new JTextField("");
        JTextField t_mdp = new JTextField("");


        int frame_w = 450; int frame_h = 685;
        int margin = 30;// 边缘留白
        int info_h = 200;
        // 把底下的高度五等分
        int comp_h = (frame_h-info_h-margin*3)/5 - margin;
        int comp_w = frame_w/2 - margin;
        int comp_y0 = info_h+margin*2;

        this.setSize(frame_w,frame_h);
        this.setLayout(new BorderLayout());

        add_components(this,
                new JComponent[]{l_info,l_pfp,l_mdp,
                        l_rd1,l_rd2,l_rd3,
                        t_rd1, t_rd2, t_rd3,
                        t_mdp, b_d2p, b_p2d, new JLabel()});
//        done with the add_components() above;
//        q1Frame.add(l_rd1); q1Frame.add(t_rd1);
//        q1Frame.add(l_rd2); q1Frame.add(t_rd2);
//        q1Frame.add(l_rd3); q1Frame.add(t_rd3);
//        q1Frame.add(l_mdp); q1Frame.add(t_mdp);
//        q1Frame.add(b_d2p); q1Frame.add(b_p2d);
//
        l_info.setBounds(margin, margin, comp_w, info_h);
        l_pfp.setBounds(frame_w/2+margin, margin, comp_w,info_h);

        l_rd1.setBounds(margin, comp_y0, comp_w, comp_h);
        l_rd2.setBounds(margin, comp_y0+(comp_h+margin), comp_w, comp_h);
        l_rd3.setBounds(margin, comp_y0+(comp_h+margin)*2, comp_w, comp_h);
        l_mdp.setBounds(margin, comp_y0+(comp_h+margin)*3, comp_w, comp_h);
        b_d2p.setBounds(margin, comp_y0+(comp_h+margin)*4, comp_w-20, comp_h-20);

        t_rd1.setBounds(margin*2+comp_w, comp_y0, comp_w-margin, comp_h);
        t_rd2.setBounds(margin*2+comp_w, comp_y0+(comp_h+margin),comp_w-margin, comp_h);
        t_rd3.setBounds(margin*2+comp_w, comp_y0+(comp_h+margin)*2, comp_w-margin, comp_h);
        t_mdp.setBounds(margin*2+comp_w, comp_y0+(comp_h+margin)*3, comp_w-margin, comp_h);
        b_p2d.setBounds(margin*2+comp_w, comp_y0+(comp_h+margin)*4, comp_w-margin-20, comp_h-20);


        // adding listeners
        b_d2p.addActionListener(new ActionListener() {
            /**
             * Invoked when "Data to multi package" clicked.
             *  take strings from the three textfields
             *  and parse it using method from MultiDataPacket
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                t_mdp.setText(MultiDataPacket.convertToHexDataPacket(new String[]
                        {t_rd1.getText(), t_rd2.getText(), t_rd3.getText()}));
            }
        });


        // adding listeners
        b_p2d.addActionListener(new ActionListener() {
            /**
             * Invoked when "Multi package to data" clicked.
             *  take string from the multiDataTextField,
             *  process it and split it to three string data
             *  catch DataPacketError
             *  and parse it using method from MultiDataPacket
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rawDataArr = {"","",""};
                try {
                    rawDataArr = MultiDataPacket.getMultiDataFromHexDataPacket(t_mdp.getText());
                } catch (DataPacketException err_DataPak) {
                    err_DataPak.printStackTrace();
                    System.out.println(err_DataPak);
                    JOptionPane.showMessageDialog(b_d2p, "Something wrong with your packet!\n\n"+err_DataPak,
                            "DataPacketException!",JOptionPane.ERROR_MESSAGE);
                } finally {
                    t_rd1.setText(rawDataArr[0]);
                    t_rd2.setText(rawDataArr[1]);
                    t_rd3.setText(rawDataArr[2]);
                }
            }
        });



    }

    public static void main(String[] args) {
        JFrame q2Frame = new Q2("Multiple Data Convert:");
        q2Frame.setVisible(true);
    }


    private static void add_components(JFrame jf, JComponent[] jcs){
        for (JComponent comp: jcs) {
            jf.add(comp);
            comp.setVisible(true);
        }
    }

}

