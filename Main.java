import java.util.*;
import java.io.*;

public class Main {

    public static ArrayList<Diner> waitingDinersList;

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        // System.out.println("Working Directory = " + System.getProperty("user.dir"));

        File inputText = new File("/mnt/c/Users/phani/Downloads/Masters/Semester-2/6431/6431-Project/Test-Inputs/input-1.txt");

        // You can uncomment the below line and comment the above line if you want to pass the input file via the console.
    
        // File inputText = new File(args[0]); 

        Scanner scan = new Scanner(inputText);
        
        String line1 = scan.nextLine();

        int diners = Integer.parseInt(line1);

        String line2 = scan.nextLine();

        int tables = Integer.parseInt(line2);

        String line3 = scan.nextLine();

        int cooks = Integer.parseInt(line3);

        if (diners == 0 || tables == 0 || cooks == 0) {
            System.out.println("The input is not valid!");
            System.exit(0);
        }

        Restaurant.getInstance();
        Restaurant.totalCooks = cooks;

        Restaurant.getInstance();
        Restaurant.totalDiners = diners;

        Restaurant.getInstance();
        Restaurant.totalTables = tables;

        waitingDinersList = new ArrayList<Diner>(diners);

        for (int i = 1; i <= diners; i++) {

            String line = scan.nextLine();
            String[] stringArray = line.split(",", 5);

            Integer dinerID = i;
            Integer inTime = Integer.parseInt(stringArray[0]);
            Integer numBurgers = Integer.parseInt(stringArray[1]);
            Integer numFries = Integer.parseInt(stringArray[2]);
            Integer isCoke = Integer.parseInt(stringArray[3]);

            Diner diner = new Diner(dinerID, inTime, numBurgers, numFries, isCoke);

            Restaurant.getInstance().updateDinersList(diner);
        }

        Tables.getInstance();

        for (int k = 1; k <= tables; k++) {
            // Tables table = new Tables(k);
            Integer tableID = k;
            Tables.tablesList.add(tableID);
        }

        

        BurgerMaker.getInstance();

        FriesMaker.getInstance();

        CokeMachine.getInstance();

        Restaurant.getInstance().startCookThreads();

        Restaurant.getInstance().startDinerThreads();

        scan.close();
    }
}