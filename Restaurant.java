import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;

public class Restaurant {

    private static Restaurant instance = new Restaurant();

    public static ArrayList<Diner> dinersList = new ArrayList<Diner>();
    public static Integer totalDiners;
    public static Integer totalCooks;
    public static Integer totalTables;
    public static long startTime = System.currentTimeMillis()/1000;
    public Integer lastInTime = 0;
    public Integer waitTime;
    public static Integer completedDiners = 0;

    public static Restaurant getInstance() {
        return instance;
    }

    public void updateDinersList(Diner dinerObject){
        dinersList.add(dinerObject);
    }

    public void setTotalDiners(Integer diners) {
        totalDiners = diners;
    }

    public void setTotalCooks(Integer cooks) {
        totalCooks = cooks;
    }

    public void setTotalTables(Integer tables) {
        totalTables = tables;
    }

    public Diner getDinerObject(Integer dinerID){
        return dinersList.get(dinerID-1);
    }

    public Integer getTotalDiners() {
        return totalDiners;
    }

    public Integer getTotalCooks() {
        return totalCooks;
    }

    public Integer getTotalTables() {
        return totalTables;
    }

    public void startCookThreads() {
        for (int i = 1; i <= totalCooks; i++) {
            Cook cookObject = new Cook(i);
            Thread cookThread = new Thread(cookObject);
            cookThread.start();
            
        }
    }

    public void startDinerThreads() throws InterruptedException {
        for (int i = 1; i <= totalDiners; i++) {
            Diner dinerObject = dinersList.get(i-1);
            waitTime = dinerObject.inTime - lastInTime;
            Thread dinerThread = new Thread(dinerObject);
            Thread.sleep(waitTime*1000);
            dinerThread.start();
            lastInTime = dinerObject.inTime;
        }
    }
}