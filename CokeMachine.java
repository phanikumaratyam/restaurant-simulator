import java.util.*;
import java.io.*;

public class CokeMachine {
    public static boolean isOccupied = false;
    public static CokeMachine instance = null;
    public static Object cokeMutex = new Object();


    public CokeMachine() {

    }

    public synchronized static CokeMachine getInstance() {
        if (instance == null) {
            CokeMachine instance = new CokeMachine();
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