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
public class CRC16 {

    /* return a 2 bytes CRC code from a byte array*/
    private static String getCRC(byte[] bytes) {
        int CRC = 0X0000FFFF;
        final int POLYNOMIAL = 0X0000A001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC = CRC ^ ((int) (bytes[i] & 0x000000ff));
            for (j = 0; j < 8; j++) {
                if ((CRC & 0X00000001) != 0) {
                    CRC = CRC >> 1;
                    CRC = CRC ^ POLYNOMIAL;
                } else {
                    CRC = CRC >> 1;
                }
            }
        }
        String result = Integer.toHexString(CRC).toUpperCase();
        if (result.length() != 4) {
            StringBuffer sb = new StringBuffer("0000");
            result = sb.replace(4 - result.length(), 4, result).toString();
        }
        return result.substring(2, 4) + result.substring(0, 2);
    }

    //16进制转byte
    public static byte[] hex2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            int h = hexDigits.indexOf(hexChars[pos]) << 4;
            int l = hexDigits.indexOf(hexChars[pos + 1]);
            if (h == -1 || l == -1) {
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

    //16进制转字符串
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static String getCRC(String hexData) {
        return getCRC(hex2Bytes(hexData));
    }

    public static String unpack(String original) {
        return hexStringToString(original);
    }

}
 
