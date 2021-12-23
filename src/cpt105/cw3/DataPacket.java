/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3;

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


    public static String hexToString(String hex) {
        String dat_str = "";
        byte i0;
        char c;
        for (int i = 0; i < hex.length(); i += 2) {
            i0 = fromHexStrToByte(hex.substring(i, i + 1));


            i0 = (byte) (i0 * 16);
            i0 += fromHexStrToByte(hex.substring(i + 1, i + 2));
            ;

            c = (char) i0;
            dat_str += c;
        }
        return dat_str;
    }

    /**
     *
     * @param s - string representing hex values, from "0" - "F" only
     */
    public static byte fromHexStrToByte(String s){
        if ("0123456789".contains(s)){
            return Byte.parseByte(s);
        } else{
            int ind = "abcdef".indexOf(s.toLowerCase());
            return (byte) (10+ind);
        }
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

