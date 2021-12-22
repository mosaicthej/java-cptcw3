/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.pkg2036598;

/**
 *
 * @author rxy
 */
public class DataPacket {
    //字符串转16进制
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static String convertToDataPacket(String data) {
        String header = "AA";
        int x = data.length();
        String length = "";
        if (x < 10) {
            length += "0" + x;
        }
        if (x > 10) {
            length += x;
        }
        String segment = strTo16(data);
        String C = CRC16.getCRC(strTo16(data));
        String tail = "BB";
        String whole;
        whole = header + length + segment + C + tail;
        return whole;
    }

    public static String getDataFromDataPacket(String hexData) {
        String l1 = hexData.substring(2, 4);
        int l = Integer.parseInt(l1);
        String hex = hexData.substring(4, 4 + l * 2);
        String x = CRC16.unpack(hex);
        String CRC = CRC16.getCRC(strTo16(x));
        String CRC_inhexdata = hexData.substring(4 + l * 2, 8 + l * 2);
        if (CRC.contentEquals(CRC_inhexdata)) {
            return x;
        } else {
            return x = "wrong";
        }

    }

    static String convertToDataPacket(String[] text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static String[] getMultiDataFromHexDataPacket(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
   
