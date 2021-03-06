
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpt105.cw3;


 
public class CRC16 {
    /*return a 2 byte CRC code from a byte array*/
    // this method is given and should not be changed!
    private static String getCRC(byte[] bytes) {
        int CRC = 0X0000FFFF;
        final int POLYNOMIAL=0X0000A001;
        int i, j;
        for(i=0; i < bytes.length; i++){
            CRC = CRC^((bytes[i] & 0x000000ff));
            for (j=0; j<8; j++){
                if ((CRC & 0X00000001) != 0){
                    CRC = CRC>>1;
                    CRC = CRC^POLYNOMIAL;
                } else{
                    CRC = CRC>>1;
                }
            }
        }
        String result = Integer.toHexString(CRC).toUpperCase();
        if (result.length()!=4){
            StringBuffer sb = new StringBuffer("0000");
            result = sb.replace(4 - result.length(), 4, result).toString();
        }

        return result.substring(2,4) + result.substring(0,2);
    }

    /**
    overloading static method getCRC(String hexData),
        invoking the method getCRC(byte[] bytes)
        and return a CRC code based on the string hexData.

    * @param hexData   The input data as hex that CRC will check on.
    * @return a string containing the CRC code generated with regards to the input.
    */ // point is to convert string hexData to a byte array.
    public static String getCRC(String hexData){
        // 把参数hexdata从string类型转换到byte[]（字节array）类型
        return getCRC(hexData.getBytes());
    }


}

