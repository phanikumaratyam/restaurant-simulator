import java.util.*;
import java.io.*;

public class Tables {

    public static Tables instance = null; 
    public Integer tableID;
    public boolean isOccupied;
    
    public static ArrayList<Integer> tablesList = new ArrayList<Integer>();

    public Tables(Integer tableID) {
        this.tableID = tableID;

        this.isOccupied = false;
    }

    public Tables() {

    }

    public synchronized static Tables getInstance() {
        if (instance == null) {
            Tables instance = new Tables();
            return instance;
        }
        else {
            return instance;
        }
    }

    public boolean isOccupied() {

        return isOccupied;
    }
}