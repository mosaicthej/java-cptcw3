package cpt105.cw3;

import javax.xml.crypto.Data;

public class MultiDataPacket extends DataPacket {
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

    public static String[] getMultiDataFromHexDataPacket(String hexData) throws DataPacketException {
        String[] dataBodies = splitByHeadtail(hexData);
        int i = 0;
        for (String dataBody:dataBodies) {
            // data verification
            if (!verifyLength(dataBody)) {
                throwLength();
            }
            if (!verifyCRC(dataBody)) {
                throwCRC();
            }

            // translating data from hex to string
            dataBodies[i] = hexToString(dataBody);
        }
        return dataBodies;
    }


    /**
     *
     *
     * @param textBody: the body part of text that should be checked
     *                | dataLength |   Data  |   CRC  |
     *                |------------|---------|--------|
     *                |   1 Byte   | N Bytes | 2 Byte |
     * @return true if count of Data: N == dataLength;
     *         false else.
     */
    private static boolean verifyLength(String textBody) {
        byte lenHex = DataPacket.fromHexStrToByte(textBody.substring(0,2));
        String strData = DataPacket.hexToString(
                        textBody.substring(2,textBody.length()-4));
        return ((byte) strData.length() == lenHex);
    }

    /**
     * throwing length exception if the length does not match
     * @throws DataPacketException
     */
    private static void throwLength() throws DataPacketException{
        throw new DataPacketException(DataPacketException.ERROR_CODE_LENGTH, "length not match");
    }

    /**
     * Verifying CRC by generating the a new CRC from the message
     *      and compare with the CRC given.
     * @param dataBody: the body part of text that should be checked
     *                | dataLength |   Data  |   CRC  |
     *                |------------|---------|--------|
     *                |   1 Byte   | N Bytes | 2 Byte |
     * @return true if generated CRC == CRC from the text;
     *         false else.
     */
    private static boolean verifyCRC(String dataBody) {
        String CRC_old = dataBody.substring(dataBody.length()-2,dataBody.length());
        String data_text = dataBody.substring(0,dataBody.length()-2);

        String CRC_new = CRC16.getCRC(data_text);

        return (CRC_old == CRC_new);
    }

    private static void throwCRC() throws DataPacketException{
        throw new DataPacketException(DataPacketException.ERROR_CODE_CRC, "CRC does not match");
    }


    private static String[] splitByHeadtail(String hexData){
        // split by delimiter "BBAA"
        String[] outHexs = hexData.split("BBAA");
        outHexs[0] = outHexs[0].split("AA")[1];
        outHexs[outHexs.length-1] = outHexs[outHexs.length-1].split("BB")[0];
        return outHexs;
    }

    public static void main(String[] args) {
        try {
            for (String s:getMultiDataFromHexDataPacket("AA034142435085BBAA04313233417BD7BB")){
                System.out.println(s);
            }
        } catch (DataPacketException e) {
            e.printStackTrace();
        }
        ;

    }


}

