package cpt105.cw3.partA;


public class Main {

    public static void main(String[] args) {
	// write your code here
        // testing_methods();
        // Q1.main(new String[]{"mm"});

        DataPacketException e1 = new DataPacketException(404, "Data not found");

        System.out.println(e1.getID());
        System.out.println(e1);
    }

    private static void testing_methods() {

        System.out.print("Testing: CRC16.getCRC('31323341'), expecting 7BD7, result: ");
        System.out.println( // expecting 7BD7
                CRC16.getCRC("31323341")
        );

        System.out.print("Testing: DataPacket.stringToHex('123A'), expecting 3123341, result: ");
        System.out.println( // expecting 31323341
                DataPacket.stringToHex("123A")
        );

        System.out.print("Testing: DataPacket.convertToDataPacket('123A'), expecting AA04313233417BD7BB, result: ");
        System.out.println( // expecting AA04313233417BD7BB
                DataPacket.convertToDataPacket("123A")
        );





    }

}
