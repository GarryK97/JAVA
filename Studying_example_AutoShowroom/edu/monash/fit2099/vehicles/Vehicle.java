package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.bids.Bid;
import edu.monash.fit2099.bids.BidsManager;
import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.VehicleException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Abstract class that contains the outline of a vehicle. This is used to create vehicle types. e.g) Truck, Sedan
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.bids
 * @see edu.monash.fit2099.exceptions.VehicleException
 */
abstract public class Vehicle {
    /**
     * The name of the maker
     */
    private String make;

    /**
     * The name of the model
     */
    private String model;

    /**
     * the ID of the vehicle
     */
    private int vId;

    /**
     * BidsManager object to store the bids of a vehicle.
     */
    private BidsManager bidsManager;

    /**
     * It stores all the Vehicle IDs generated. It is used to avoid duplicates of ID.
     */
    public static ArrayList<Integer> vehicle_ids = new ArrayList<Integer>();

    /**
     * Constructor that accepts maker and model parameter for Vehicle. ID will be auto-generated.
     *
     * @param make  Name of the maker
     * @param model Name of the model
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Vehicle(String make, String model) throws VehicleException {
        if (setMake(make) && setModel(model)) {
            bidsManager = new BidsManager();
            int randomId = generate_VehicleId();
            this.vId = randomId;
            vehicle_ids.add(randomId);
        } else {
            throw new VehicleException("Incorrect Maker OR Model");
        }
    }

    /**
     * Constructor that accepts maker, model and Vehicle ID.
     *
     * @param make  Name of the maker
     * @param model Name of the model
     * @param vId   a new Vehicle ID
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Vehicle(String make, String model, int vId) throws VehicleException {
        if (setMake(make) && setModel(model)) {
            this.make = make;
            this.model = model;
            this.vId = vId;
        } else {
            throw new VehicleException("Incorrect Maker OR Model");
        }
    }

    /**
     * This method generates a unique ID for a vehicle randomly.
     *
     * @return the generated Vehicle ID
     */
    public int generate_VehicleId() {
        Random r = new Random();
        int low = 1000;
        int high = 9999;

        int randomId = r.nextInt(high - low) + low;
        while (vehicle_ids.contains(randomId))    // To prevent duplicates of ID
            randomId = r.nextInt(high - low) + low;
        return randomId;
    }

    /**
     * Getter for Vehicle ID
     *
     * @return the Vehicle ID
     */
    public int getvId() {
        return vId;
    }

    /**
     * Getter for the BidsManager of a vehicle. BidsManager has all the bids made for a vehicle.
     *
     * @return the BidsManager of a vehicle
     */
    public BidsManager getBidsManager() {
        return bidsManager;
    }

    /**
     * Setter to change the maker of a vehicle
     *
     * @param make Name of the maker
     * @return return true if make can be accepted. Otherwise, return false. The length must be in between 3 and 15
     */
    public boolean setMake(String make) {
        if (3 <= make.length() && make.length() <= 15) {
            this.make = make;
            return true;
        }
        return false;
    }

    /**
     * Setter to change the model of a vehicle
     *
     * @param model Name of the model
     * @return return true if model can be accepted. Otherwise, return false. The length must be in between 3 and 15
     */
    public boolean setModel(String model) {
        if (3 <= model.length() && model.length() <= 15) {
            this.model = model;
            return true;
        }
        return false;
    }

    /**
     * This method returns a String that contains all the information of a vehicle.
     *
     * @return a String in format (Vehicle ID | Maker | Model)
     */
    public String description() {
        return ("ID:" + vId + "| Maker:" + make + "| Model:" + model);
    }

    /**
     * This method is used to show all the bids made for a vehicle.
     *
     * @return the bids of a vehicle. Each line will have one bid for better visibility.
     */
    public String showBid() {
        HashMap<Integer, Bid> bids_map = bidsManager.getBids_hashmap();

        String output = "";
        for (int key : bids_map.keySet())
            output += bids_map.get(key).description() + "\n";

        if (output.equals(""))
            output = "No Bid Found";
        return output;
    }

    /**
     * This method shows the Best Bid which has a highest bid price of a vehicle
     * @return "No Bid Found" if the vehicle has no vid. Else, the best bid with description will be returned
     */
    public String showBestBid() {
        HashMap<Integer, Bid> bids_map = bidsManager.getBids_hashmap();

        Bid best_bid = null;
        int max_bid_price = 0;

        int bid_price = 0;
        for (int key : bids_map.keySet()) {
            bid_price = bids_map.get(key).getPrice();
            if (bid_price > max_bid_price) {
                best_bid = bids_map.get(key);
                max_bid_price = best_bid.getPrice();
            }
        }
        String output = "";
        if (best_bid == null)
            output = "No Bid Found";
        else
            output = "The Best Bid : " + best_bid.description();

        return output;
    }

    /**
     * This method shows the Worst Bid which has a lowest bid price of a vehicle
     * @return "No Bid Found" if the vehicle has no vid. Else, the worst bid with description will be returned
     */
    public String showWorstBid() {
        HashMap<Integer, Bid> bids_map = bidsManager.getBids_hashmap();

        Bid worst_bid = null;
        int min_bid_price = 0;

        int bid_price = 0;
        for (int key : bids_map.keySet()) {
            bid_price = bids_map.get(key).getPrice();
            if (bid_price < min_bid_price) {
                worst_bid = bids_map.get(key);
                min_bid_price = worst_bid.getPrice();
            }
        }
        String output = "";
        if (worst_bid == null)
            output = "No Bid Found";
        else
            output = "The Worst Bid : " + worst_bid.description();

        return output;
    }

    /**
     * This method deletes a Bid with the given Bid ID.
     * @param bidID the Bid ID to remove
     * @return return true if Bid successfully deleted, return false if no Bid exist that matches the ID.
     */
    public Boolean deleteBid_byBidID(int bidID){
        HashMap<Integer, Bid> bids_map = bidsManager.getBids_hashmap();

        for (int key : bids_map.keySet()){
            if (bids_map.get(key).getBidID() == bidID){
                bids_map.remove(key);
                return true;
            }
        }
        return false;
    }
}
