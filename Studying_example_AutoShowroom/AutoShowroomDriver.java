import edu.monash.fit2099.autoshowroom.AutoShowroom;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * AutoShowroomDriver is the driver class of AutoShowroom class.
 * This class contains a console I/O, including printing menus and getting the input from the user
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.autoshowroom.AutoShowroom
 */
public class AutoShowroomDriver {
    /**
     * This is the main method that actually runs the program.
     * It will print the menu and will respond to the user inputs, using selectMenuItem() method.
     * @param args  Basic parameter for main method
     * @exception   InputMismatchException  if the input type is not an integer when integer is needed
     */
    public static void main(String[] args) {
        AutoShowroom showroom = new AutoShowroom();

        Scanner scanner = new Scanner(System.in);
        int selection;
        do{
            selection = selectMenuItem();
            switch(selection){
                case 1:
                    System.out.print("Enter Car Maker:");
                    String maker = scanner.next();
                    System.out.print("Enter Car Model:");
                    String model = scanner.next();
                    try {
                        System.out.print("Enter Car Seats: ");
                        int seats = scanner.nextInt();
                        showroom.createSedan(maker, model, seats);
                    } catch (InputMismatchException e){
                        System.out.println("Incorrect Seats Type");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Enter Car Maker:");
                    maker = scanner.next();
                    System.out.print("Enter Car Model:");
                    model = scanner.next();
                    try {
                        System.out.print("Enter Car Capacity: ");
                        int capacity = scanner.nextInt();
                        System.out.print("Enter Car Wheels: ");
                        int wheel = scanner.nextInt();
                        showroom.createTruck(maker, model, capacity, wheel);
                    } catch (InputMismatchException e){
                        System.out.println("Incorrect Capacity OR Wheels Type");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    showroom.displayFleet();
                    break;

                case 4:
                    System.out.print("Enter Buyer Given Name:");
                    String givenname = scanner.next();
                    System.out.print("Enter Buyer First Name:");
                    String firstname = scanner.next();
                    showroom.createBuyer(givenname, firstname);
                    break;

                case 5:
                    showroom.displayBuyers();
                    break;

                case 6:
                    try {
                        System.out.print("Enter Vehicle ID:");
                        int vId = scanner.nextInt();
                        System.out.print("Enter Buyer ID:");
                        int buyerId = scanner.nextInt();
                        System.out.print("Enter the Bid Price: ");
                        int price = scanner.nextInt();
                        System.out.print("Enter the Date of the Bid: ");
                        String dob = scanner.next();
                        if (!showroom.createBid(vId, buyerId, price, dob))
                            System.out.println("Vehicle or Buyer ID does not exist");
                    } catch (InputMismatchException e){
                        System.out.println("Incorrect Vehicle ID, Buyer ID or Price Type");
                        scanner.nextLine();
                    }
                    break;

                case 7:
                    try{
                        System.out.print("Enter Bid ID:");
                        int bidId = scanner.nextInt();
                        showroom.removeBid(bidId);
                    }
                    catch (InputMismatchException e){
                        System.out.println("Incorrect Bid ID Type");
                        scanner.nextLine();
                    }
                    break;

                case 8:
                    try{
                        System.out.print("Enter Vehicle ID:");
                        int vId = scanner.nextInt();
                        showroom.displayBestBid(vId);
                    }
                    catch (InputMismatchException e){
                        System.out.println("Incorrect Vehicle ID Type");
                        scanner.nextLine();
                    }
                    break;

                case 9:
                    try{
                        System.out.print("Enter Vehicle ID:");
                        int vId = scanner.nextInt();
                        showroom.displayWorstBid(vId);
                    }
                    catch (InputMismatchException e){
                        System.out.println("Incorrect Vehicle ID Type");
                        scanner.nextLine();
                    }
                    break;

                case 10:
                    try {
                        System.out.print("Enter Vehicle ID:");
                        int vId = scanner.nextInt();
                        Boolean isNoError = showroom.sellVehicle(vId);
                        if (!isNoError) {System.out.println("There is no Vehicle!!!");}
                        else {System.out.println("The Vehicle is sold successfully");}
                    }
                    catch (InputMismatchException e){
                        System.out.println("Incorrect Vehicle ID Type");
                        scanner.nextLine();
                    }
                    break;
            }
        }while (selection != 11);
        System.out.println("---Ends the Program---");
    }

    /**
     * This method prints the menu and gets the user input
     * @return  returns the choice of the user (User input).
     * @exception   InputMismatchException  if the user input is not integer.
     */
    public static int selectMenuItem(){
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int choice = 0;
        try {
            choice = scanner.nextInt();
            if (choice > 11 || choice < 1) {
                System.out.println("Input is Out of Range !!!");
            }
        } catch (InputMismatchException e){
            System.out.println("The input type is not integer!!!");
        }

        return choice;
    }

    /**
     * This method simply prints the menu. This is used with selectMenuItem().
     */
    public static void printMenu(){
        System.out.println("\n1) Add New Sedan");
        System.out.println("2) Add New Truck");
        System.out.println("3) Display Fleet");
        System.out.println("4) Add New Buyer");
        System.out.println("5) List All Buyers");
        System.out.println("6) Add Bid");
        System.out.println("7) Remove Bid");
        System.out.println("8) Show Best Bid of a Vehicle");
        System.out.println("9) Show Worst Bid of a Vehicle");
        System.out.println("10) Sell a Vehicle");
        System.out.println("11) Exit");
        System.out.println("------------------------------");
        System.out.print("Select Your Option : ");
    }
}
