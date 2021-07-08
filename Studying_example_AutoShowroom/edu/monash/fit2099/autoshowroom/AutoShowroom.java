package edu.monash.fit2099.autoshowroom;

import edu.monash.fit2099.bids.Bid;
import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.SedanException;
import edu.monash.fit2099.exceptions.TruckException;
import edu.monash.fit2099.exceptions.VehicleException;
import edu.monash.fit2099.vehicles.Sedan;
import edu.monash.fit2099.vehicles.Truck;
import edu.monash.fit2099.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * AutoShowroom is used to create vehicles, bids for the vehicles including the buyers.
 * AutoShowroom stores all vehicles and buyers that are created.
 * The possible errors are handled by custom exceptions which are inside exceptions package.
 * @author  Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.vehicles
 * @see edu.monash.fit2099.buyers
 * @see edu.monash.fit2099.exceptions
 */
public class AutoShowroom {
    /**
     * All existing Vehicles are stored. Sold vehicles are not included.
     */
    public static ArrayList<Vehicle> vehicles_list = new ArrayList<>();

    /**
     * All existing Buyers are stored
     */
    public static ArrayList<Buyer> buyers_list = new ArrayList<>();

    /**
     * All sold vehicles are stored
     */
    public static ArrayList<Vehicle> sold_vehicles_list = new ArrayList<>();

    /**
     * This method creates a new sedan in the autoshowroom.
     * @param maker The maker name of the sedan
     * @param model The model name of the sedan
     * @param seats Available seats  of the sedan
     * @exception SedanException if 'seats' is out of range (seats must be between 4 and 5)
     * @exception VehicleException if 'maker' or 'model' is too long (bigger than 15) or too short (less than 3)
     */
    public void createSedan(String maker, String model, int seats) {
        try {
            Sedan sedan = new Sedan(maker, model, seats);
            vehicles_list.add(sedan);
            System.out.println("New Sedan Added");
        }
        catch (SedanException e){
            System.out.println(e.getMessage());
        }
        catch (VehicleException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method creates a new truck in the autoshowroom.
     * @param maker The maker name of the truck
     * @param model The model name of the truck
     * @param capacity  The capacity of the truck
     * @param wheels    The number of wheels
     * @exception TruckException if 'capacity' or 'wheels' is out of range. capacity must be between 1 and 15
     * wheels must be between 4 and 16.
     * @exception VehicleException if 'maker' or 'model' is too long (bigger than 15) or too short (less than 3)
     */
    public void createTruck(String maker, String model, int capacity, int wheels) {
        try {
            Truck truck = new Truck(maker, model, capacity, wheels);
            vehicles_list.add(truck);
            System.out.println("New Truck Added");
        }
        catch (TruckException e){
            System.out.println(e.getMessage());
        }
        catch (VehicleException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method creates a new buyer
     * @param given_name The given name of the buyer
     * @param family_name The family name of the buyer
     */
    public void createBuyer(String given_name, String family_name) {
        Buyer buyer = Buyer.getInstance(given_name, family_name);
        if (buyer != null) {
            buyers_list.add(buyer);
            System.out.println("New Buyer Added");
        } else {
            System.out.println("Something wrong with the buyer's value!!!");
        }
    }

    /**
     * This method checks whether a buyer with the given buyer ID exists or not.
     * @param buyerId The Buyer ID to check
     * @return return true if a buyer is found, return false if no buyer exists.
     */
    public static boolean findBuyer_byId(int buyerId) {
        for (Buyer buyer : buyers_list) {
            if (buyer.getBuyerID() == buyerId) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method creates a new bid for a vehicle.
     * @param vId The Vehicle ID which identifies the vehicle
     * @param buyerId Buyer ID for the bid
     * @param price The bid price
     * @param date The date of bid when the bid is made.
     * @return return true if successfully added a bid. return false if there is no matching vehicle or buyer.
     */
    public Boolean createBid(int vId, int buyerId, int price, String date) {
        // Error handling done in BidsManager
        if (!findBuyer_byId(buyerId))
            return false;

        Boolean vehicle_found = false;
        Vehicle vehicle_matching = null;
        for (Vehicle vehicle : vehicles_list) {
            if (vId == vehicle.getvId()) {
                vehicle_found = true;
                vehicle_matching = vehicle;
            }
        }
        if (vehicle_found) {
            vehicle_matching.getBidsManager().addBid(buyerId, price, date);
        }
        return vehicle_found;
    }

    public void removeBid(int bidID){
        Boolean isNoError = false;
        for (Vehicle a_vehicle : vehicles_list){
            isNoError = a_vehicle.deleteBid_byBidID(bidID);
            break;
        }
        if (isNoError) {System.out.println("Bid is deleted successfully");}
        else {System.out.println("Bid with the Given ID does not exist");}
    }

    /**
     * This method displays all the vehicles exist in the autoshowroom, with the bids of each vehicles.
     */
    public void displayFleet() {
        System.out.println("----- List of Vehicles -----");
        for (Vehicle vehicle : vehicles_list) {
            System.out.println(vehicle.description());
            System.out.println(vehicle.showBid());
        }
    }

    /**
     * This method displays all the buyers.
     */
    public void displayBuyers() {
        System.out.println("----- List of Buyers -----");
        for (Buyer buyer : buyers_list) {
            System.out.println(buyer.description());
        }
    }

    /**
     * This method displays the Best Bid of a vehicle that matches the given ID.
     * @param vehicleID the Vehicle ID to search
     */
    public void displayBestBid(int vehicleID){
        String output = "Vehicle with the Given ID does not exist";
        for (Vehicle a_vehicle : vehicles_list){
            if (a_vehicle.getvId() == vehicleID){
                output = a_vehicle.showBestBid();
                break;
            }
        }
        System.out.println(output);
    }

    /**
     * This method displays the Worst Bid of a vehicle that matches the given ID.
     * @param vehicleID the Vehicle ID to search
     */
    public void displayWorstBid(int vehicleID){
        String output = "Vehicle with the Given ID does not exist";
        for (Vehicle a_vehicle : vehicles_list){
            if (a_vehicle.getvId() == vehicleID){
                output = a_vehicle.showWorstBid();
                break;
            }
        }
        System.out.println(output);
    }

    /**
     * This method sells the vehicle that matches with the given vehicle ID.
     * @param vehicleID the vehicle to sell
     * @return return true if a vehicle is sold successfully, return false if there is no matching vehicle.
     */
    public Boolean sellVehicle(int vehicleID){

        for (Vehicle a_vehicle: vehicles_list){
            if (a_vehicle.getvId() == vehicleID){
                vehicles_list.remove(a_vehicle);
                sold_vehicles_list.add(a_vehicle);
                return true;
            }
        }
        return false;
    }

}

