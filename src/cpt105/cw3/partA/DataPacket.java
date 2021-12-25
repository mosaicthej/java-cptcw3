/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3.partA;

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
     * Reading the
     *  translate and the actual String message
     * @param hex (type String) hex representation of a message string
     * @return the actual String message
     */
    public static String hexToString(String hex) {
        String dat_str = "";
        byte i0;
        char c;
        for (int i = 0; i < hex.length(); i += 2) {
            i0 = fromHexCharToByte(hex.substring(i, i + 1));


            i0 = (byte) (i0 * 16);
            i0 += fromHexCharToByte(hex.substring(i + 1, i + 2));
            ;

            c = (char) i0;
            dat_str += c;
        }
        return dat_str;
    }

    /**
     *
     * @param s - string LENGTH==2 representing hex values,
     *              from "00" to "FF".
     * @return -  int that contains the unsigned byte (8-bit) value from the string
     */
    public static int hexStrToByteInt(String s){
        int b = 0;
        b += fromHexCharToByte(s.substring(0,1));
        b <<= 4;
        b += fromHexCharToByte(s.substring(1,2));
        return b;
    }

    /**
     * @param c - string LENGTH==1 representing hex values, from "0" - "F" only
     * @return - the value (from 0-16) accords
     *              to the string reading of input char.
     */
    private static byte fromHexCharToByte(String c){
        if ("0123456789".contains(c)){
            return Byte.parseByte(c);
        } else{
            int ind = "abcdef".indexOf(c.toLowerCase());
            return (byte) (10+ind);
        }
    }

    public static byte[] stringToByteArr(String data_trimmed) {
        byte[] data_arr_byte = new byte[data_trimmed.length()/2];
        for (int i = 0; i < data_trimmed.length(); i+=2) {
            data_arr_byte[i/2] = (byte) DataPacket.hexStrToByteInt(data_trimmed.substring(i,i+2));
        } return data_arr_byte;
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

    public static void main(String[] args) {
        // testing for fromHexStrToByte()
        testing_fromHexStrToByte();
        testing_getCRC("hello");
    }

    private static void testing_getCRC(String tester) {
        System.out.println(String.format("CRC for %s is\t %s", tester, CRC16.getCRC(tester)));
    }

    private static void testing_fromHexStrToByte() {
        // testing for fromHexStrToByte()
        String[] inpArr = new String[255];
        String hexStr;
        for (int i = 0; i <= Byte.MAX_VALUE-Byte.MIN_VALUE; i++) {
            hexStr = String.format("%02x", i);

            System.out.println(String.format("input is %s, output is %d", hexStr,
                    hexStrToByteInt(hexStr)));
        }
    }


}

