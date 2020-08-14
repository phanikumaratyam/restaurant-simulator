import java.util.*;
import java.io.*;

public class Cook implements Runnable{

    public Integer cookID;
    private String currentTime;
    private Diner waitingDiner;
    public Integer waitingDinerID;

    public Cook(Integer cookID) {
        this.cookID = cookID;
    }

    public void selectDiner() throws InterruptedException {
        synchronized(Main.waitingDinersList) {
            while (Main.waitingDinersList.isEmpty()) { 

                Main.waitingDinersList.wait();
                
            }

            waitingDiner = Main.waitingDinersList.remove(0);
            System.out.println(currentTime() + "Cook " + cookID + " processes Diner " + waitingDiner.dinerID + "'s order.");
        }
    }

    public void makeFood() throws InterruptedException {

        while (waitingDiner.numBurgers != 0 || waitingDiner.numFries != 0 || waitingDiner.isCoke != 0) {

            if (waitingDiner.numBurgers > 0) {

                Boolean isBusy;

                synchronized(BurgerMaker.burgerMutex) {
                    isBusy = BurgerMaker.isOccupied;
                }

                if (isBusy == false) {
                    synchronized(BurgerMaker.getInstance()) {
                        BurgerMaker.isOccupied = true;
                    }

                    System.out.println(currentTime() + "Cook " + cookID + " uses the burger machine.");
                    Thread.sleep(5000);
                    waitingDiner.numBurgers -= 1;

                    synchronized(BurgerMaker.getInstance()) {
                        BurgerMaker.isOccupied = false;
                    }
                }
            }

            if (waitingDiner.numFries > 0) {

                Boolean isBusy;

                synchronized(FriesMaker.friesMutex) {
                    isBusy = FriesMaker.isOccupied;
                }

                if (isBusy == false) {
                    synchronized(FriesMaker.getInstance()) {
                        FriesMaker.isOccupied = true;
                    }

                    System.out.println(currentTime() + "Cook " + cookID + " uses the fries machine.");
                    Thread.sleep(3000);
                    waitingDiner.numFries -= 1;

                    synchronized(FriesMaker.getInstance()) {
                        FriesMaker.isOccupied = false;
                    }
                }
            }

            if (waitingDiner.isCoke > 0) {

                Boolean isBusy;

                synchronized(CokeMachine.cokeMutex) {
                    isBusy = CokeMachine.isOccupied;
                }

                if (isBusy == false) {
                    synchronized(CokeMachine.getInstance()) {
                        CokeMachine.isOccupied = true;
                    }

                    System.out.println(currentTime() + "Cook " + cookID + " uses the coke machine.");
                    Thread.sleep(1000);
                    waitingDiner.isCoke = 0;

                    synchronized(CokeMachine.getInstance()) {
                        CokeMachine.isOccupied = false;
                    }
                }
            }


        }

        if (waitingDiner.numBurgers == 0 && waitingDiner.numFries == 0 && waitingDiner.isCoke == 0) {
            synchronized(waitingDiner) {
                waitingDiner.notify();
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
        
        while (true) {
            try {
                selectDiner();
            }
            catch(Exception e){
                System.out.println("Error occurred while selecting diner");
            }
    
            try {
                makeFood();
                // System.out.println(cookID + " Make Food done");
            }
            catch(Exception e){
                System.out.println("Error occurred while making food");
            }
        }
        
    }
}