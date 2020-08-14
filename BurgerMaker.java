import java.util.*;
import java.io.*;

public class BurgerMaker {
    public static boolean isOccupied = false;
    public static BurgerMaker instance = null;
    public static Object burgerMutex = new Object();


    public BurgerMaker() {

    }

    public synchronized static BurgerMaker getInstance() {
        if (instance == null) {
            BurgerMaker instance = new BurgerMaker();
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