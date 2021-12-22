/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3;;

/**
 *
 * @author
 */
public class DataPacket {
    /**
     *
     * @param inp
     * @return
     */
    public static String stringToHex(String inp){
        String s = "";

        for (char c: inp.toCharArray()){
            String s_x = Integer.toHexString(c);
            s += s_x;
        }

        return s;
    }

    /**
     * converting the data to DataPacket format
     * @param message - the message user enters
     * @return the processed string containing DataPacket
     */
    public static String convertToDataPacket(String message){
        int l = message.length();

        String a = "AA"
                + String.format("%1$02X",message.length())       // https://stackoverflow.com/a/45495982/9499956
                + stringToHex(message)
                + CRC16.getCRC(message) + "BB";
        return a;
    }

}
