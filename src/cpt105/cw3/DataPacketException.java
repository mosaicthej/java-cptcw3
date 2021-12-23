package cpt105.cw3;

public class DataPacketException extends Exception {
    private int eId;
    private String message;

    public DataPacketException (int id, String message){
        this.eId = id;
        this.message = message;
    }

    public int getID(){
        return this.eId;
    }

    @Override
    public String toString(){
        String s = "";
        s += "DataPacketException:\nException ID is: " +  Integer.toString(this.eId)
                +"\n"+ this.message;
        return s;
    }


}

