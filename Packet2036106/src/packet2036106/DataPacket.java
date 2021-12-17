/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet2036106;

/**
 *
 * @author zhaoyihan
 */
public class DataPacket {
    public static String stringToHex(String str){
        String s = "";
        for(int i = 0; i< str.length(); i++){
            int ch = (int) str.charAt(i);
            String s4 = Integer.toHexString(ch);
            s = s + s4;
        }
        return s;
    }
    public static String convertToDataPacket(String data){
        int l = data.length();
        String a = "AA" + "0" + l + stringToHex(data) + "BB";
        return a;
    }
    public static void main(String[] args) {
        String y = "123A";
        String k = convertToDataPacket(y);
        System.out.println(k);
    }
}
