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
public class MultiDataPacket extends DataPacket{
    public static String[] stringToHex(String[] str) {
        String[]hexdata = new String[str.length];
        for(int i= 0;i < str.length;i++) {
            hexdata[i] = strTo16(str[i]);
            }
        return hexdata;
    }
     String convertToHexDataPacket(String[] data){
        String multi = "";
        for(String n:data){
            multi += convertToDataPacket(n);
        }
        return multi;
    }
    
   
    public static String[] getMultiDataFromHexDataPacket(String hexData){
        String[] datapackage = hexData.split("BB");//分隔函数
        String[] packet = new String[datapackage.length];
        int x = 0;
        for (String y:packet){
            y += "BB";
            packet[x] = getDataFromDataPacket(y);
            x += 1;
        }
        return packet;
        }
    
    
}
