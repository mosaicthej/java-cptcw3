/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3;;

/**
 *
 * @author zhaoyihan
 */
public class DataPacket {
    /**
     *
     * @param inp
     * @return
     */
    public static String stringToHex(String inp){
        String s = "";
        for(int i = 0; i< inp.length(); i++){
            int ch = inp.charAt(i);
            String s4 = Integer.toHexString(ch);
            s = s + s4;
        }
        return s;
    }

    /**
     *
     * @param data
     * @return
     */
    public static String convertToDataPacket(String data){
        int l = data.length();
        // https://stackoverflow.com/a/45495982/9499956
        String a = "AA" + String.format("%1$02X",data.length()) + stringToHex(data) + "BB";
        return a;
    }

    public static void main(String[] args) {
        String y = "123A";
        String k = convertToDataPacket(y);
        System.out.println(k);
        System.out.println(stringToHex(y));

    }
}
