package cpt105.cw3;

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

    /**
     *
     * @param hexData one HexString that contains multiple processed data Packet
     *              hexData appears like this:
     *      *         | dataLength |      Data     |      CRC     |
     *      *         |------------|---------------|--------------|
     *      *         |   1 Byte   |    N Bytes    |    2 Byte    |
     *      *  subStr |   (0, 2)   |  (2, leng-4)  | (len-4, len) |
     * @return
     * @throws DataPacketException if length or CRC check aren't passed.
     */
    public static String[] getMultiDataFromHexDataPacket(String hexData) throws DataPacketException {
        String[] dataBodies = splitByHeadtail(hexData);
        int i = 0;
        for (String dataBody:dataBodies) {
            // data verification
            if (!verifyLength(dataBody)) {

                throwLength();
            }
            if (!verifyCRC_generateSame(dataBody)) {
                System.out.println(String.format("CRC failed! at i == %d", i));
                throwCRC();
            }

            // translating data from hex to string
            dataBodies[i] = hexToString(dataBody.substring(2,dataBody.length()-4));
            System.out.println(String.format("this piece of hexData is \"%s\", " +
                    "the translated string is \"%s\"",
                    dataBody, dataBodies[i]));
            i++;
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
        int lenHex = DataPacket.hexStrToByteInt(textBody.substring(0, 2));
        String strData = DataPacket.hexToString(
                textBody.substring(2, textBody.length() - 4));
        int strLen = strData.length();
//        System.out.println(
//                String.format("Expected length is %d, calculated length is %d",
//                        lenHex, strLen));
        if (!(strLen == lenHex)){
            System.out.println(
                    String.format("Expected length is %d, calculated length is %d",
                            lenHex, strLen));
        }

        return (strLen == lenHex);
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
    private static boolean verifyCRC_generateSame(String dataBody) {
        String CRC_old = dataBody.substring(dataBody.length()-4);
        String data_trimmed = dataBody.substring(2,dataBody.length()-4);
        byte[] strOnByteArr = DataPacket.stringToByteArr(data_trimmed);

        String CRC_new = CRC16.getCRC(hexToString(data_trimmed));
        if (!CRC_old.equalsIgnoreCase(CRC_new)){
            System.out.println(
                    String.format("Expected CRC is %s, calculated CRC is %s; " +
                                    "entered data is %s, at length %d,\t" +
                                    "CRC_old == CRC_new: %b",
                            CRC_old, CRC_new, data_trimmed, data_trimmed.length(),
                            CRC_old.equalsIgnoreCase(CRC_new)));
        }
        return (CRC_old.equalsIgnoreCase(CRC_new));
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
        String emptyData = "AA00FFFFBBAA00FFFFBBAA00FFFFBB";
        String sampleData = "AA0568656c6c6fF634BBAA096d794e616d652069734623BBAA195375696b612121212043752d6c69752d6269616e2d64616e672266BB";

        testCRC(emptyData);
        testCRC(sampleData);


    }

    private static void testCRC(String multiPakData) {
        for (String part: splitByHeadtail(multiPakData)) {
            System.out.println(String.format(
                    "string is %s, result from CheckCRC is %b\n",
                    part, verifyCRC_generateSame(part)));
        }
    }


}

