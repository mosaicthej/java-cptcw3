package cpt105.cw3;

import javax.xml.crypto.Data;

public class MultiDataPacket extends DataPacket {
    private DataPacketException lengthException =
            new DataPacketException(1, "dataLength section is not correct");

    /**
     *
     * @param str
     * @return
     */
    public static String[] stringToHex(String[] str){
        int l = str.length;
        String[] outHexs = new String[l];

        for (int i = 0; i < l; i++) {
            outHexs[i] = DataPacket.stringToHex(str[i]);
        }

        return outHexs;
    }

    public static String convertToHexDataPacket(String[] data){
        String partialPak;
        String finalPak = "";
        for (String partialRaw:data) {
            partialPak = DataPacket.convertToDataPacket(partialRaw);
            finalPak += partialPak;
        }
        return finalPak;
    }

    public static String[] getMultiDataFromHexDataPacket(String hexData){
        String[] dataBodies = splitByHeadtail(hexData);
        for (String dataBody:dataBodies){
            if (!verifyLength(dataBody)){

            }
        }



    }

    /**
     *
     * @param textBody: the body part of text that should be checked
     *                | dataLength |   Data  |   CRC  |
     *                |------------|---------|--------|
     *                |   1 Byte   | N Bytes | 2 Byte |
     * @return true if count of Data: N == dataLength;
     *         false else.
     */
    private static boolean verifyLength(String textBody){
        byte lenHex = DataPacket.fromHexStrToByte(textBody.substring(0,2));
        String strData = DataPacket.hexToString(
                        textBody.substring(2,textBody.length()-4));
        return ((byte) strData.length() == lenHex);

    }

    private static String[] splitByHeadtail(String hexData){
        // split by delimiter "BBAA"
        String[] outHexs = hexData.split("BBAA");
        outHexs[0] = outHexs[0].split("AA")[1];
        outHexs[outHexs.length-1] = outHexs[outHexs.length-1].split("BB")[0];
        return outHexs;
    }

    public static void main(String[] args) {
        for (String s:getMultiDataFromHexDataPacket("AA034142435085BBAA04313233417BD7BB")){
            System.out.println(s);
        };

    }


}

