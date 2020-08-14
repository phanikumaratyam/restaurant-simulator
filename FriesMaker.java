import java.util.*;
import java.io.*;

public class FriesMaker {
    public static boolean isOccupied = false;
    public static FriesMaker instance = null;
    public static Object friesMutex = new Object();


    public FriesMaker() {

    }

    public synchronized static FriesMaker getInstance() {
        if (instance == null) {
            FriesMaker instance = new FriesMaker();
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