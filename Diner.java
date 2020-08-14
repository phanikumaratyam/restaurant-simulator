import java.util.*;
import java.io.*;

public class Diner implements Runnable{

    public Integer dinerID;
    public Integer inTime;
    public Integer numBurgers;
    public Integer numFries;
    public Integer isCoke;
    public Integer assignedTable;
    private String currentTime;

    public Diner(Integer dinerID, Integer inTime, Integer numBurgers, Integer numFries, Integer isCoke) {

        this.dinerID = dinerID;
        this.inTime = inTime;
        this.numBurgers = numBurgers;
        this.numFries = numFries;
        this.isCoke = isCoke;
    }

    public void dinnerArrives() {
        System.out.println(currentTime() + "Diner " + dinerID + " arrives.");
    }

    public void tableAssigned() throws InterruptedException {
        synchronized(Tables.tablesList) {

            while (Tables.tablesList.isEmpty()) {
                Tables.tablesList.wait();
            }
            assignedTable = Tables.tablesList.remove(0);
            System.out.println(currentTime() + "Diner " + dinerID + " is seated at table " + assignedTable + ".");
        }          
    }

    public void orderFood() throws InterruptedException {
        synchronized(Main.waitingDinersList) {
            Main.waitingDinersList.add(this);

            Main.waitingDinersList.notify();
        }
    }

    public void waitForFood() throws InterruptedException {
        synchronized(this) {
            this.wait();
        }
    }

    public void eatFood() throws InterruptedException {
        System.out.println(currentTime() + "Diner " + dinerID + "'s order is ready. Diner " + dinerID + " starts eating.");
        Thread.sleep(30000);
    }

    public void leaveRestaurant() {
        System.out.println(currentTime() + "Diner " + dinerID + " finishes. Diner " + dinerID + " leaves the restaurant.");
        synchronized(Tables.tablesList) {
            Tables.tablesList.add(assignedTable);
            Tables.tablesList.notify();
        }
    }

    public void allDinersDone() {
        synchronized(Restaurant.completedDiners) {
            Restaurant.completedDiners = Restaurant.completedDiners + 1;

            if (Restaurant.completedDiners == Restaurant.totalDiners) {
                System.out.println(currentTime() + "The last diner leaves the restaurant.");
                System.exit(0);
            }
        }
        
    }

    private String currentTime() {
        long timeSpent = System.currentTimeMillis()/1000 - Restaurant.startTime;

        if (timeSpent < 60){
            if (timeSpent < 10) {
                currentTime = "00:0" + timeSpent;
            }
            else {
                currentTime = "00:" + timeSpent;
            }
        }
        else {
            long hours = timeSpent/60;
            long minutes = timeSpent % 60;
            String hour;
            String minute;

            if (hours < 10) {
                hour = "0" + hours;
            }
            else {
                hour = "" + hours;
            }

            if (minutes < 10) {
                minute = "0" + minutes;
            }
            else {
                minute = "" + minutes;
            }

            currentTime = hour + ":" + minute;

        }

        return currentTime + " - ";
    }

    @Override
    public void run() {

        Restaurant.getInstance();
        
        dinnerArrives();

        try {
            tableAssigned();
        }
        catch(Exception e) {
            System.out.println("Error occurred while assigning table");
        }

        try {
            orderFood();
        }
        catch(Exception e) {
            System.out.println("Error occurred while ordering food");
        }

        try {
            waitForFood();
        }
        catch(Exception e) {
            System.out.println("Error occurred while waiting for food");
        }

        try {
            eatFood();
        }
        catch(Exception e) {
            System.out.println("Error occurred while eating food");
        }

        try {
            leaveRestaurant();
        }
        catch(Exception e) {
            System.out.println("Error occurred while leaving restaurant");
        }

        try {
            allDinersDone();
        }
        catch(Exception e) {
            System.out.println("Error occurred when the last diner was leaving");
        }
    }
}