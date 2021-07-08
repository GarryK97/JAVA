package edu.monash.fit2099.bids;

import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.autoshowroom.AutoShowroom;
import edu.monash.fit2099.exceptions.BidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * BidsManager stores all of the bids of a vehicle. one BidsManager is assigned to each vehicle.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.exceptions.BidException
 * @see edu.monash.fit2099.bids.Bid
 */
public class BidsManager {
    /**
     * Hashmap that stores all the bids in this format (Buyer ID, the Bid made)
     */
    private HashMap<Integer, Bid> bids_hashmap;

    /**
     * It stores all the Bid IDs generated. It is used to avoid duplicates of ID.
     */
    public static ArrayList<Integer> bid_IDs = new ArrayList<>();

    /**
     * Constructor for BidsManager
     */
    public BidsManager() {
        bids_hashmap = new HashMap<Integer, Bid>();
    }

    /**
     * Getter for the Hashmap that has all the bids.
     * @return The hashmap with all the bids
     */
    public HashMap<Integer, Bid> getBids_hashmap() {
        return bids_hashmap;
    }

    /**
     * This method generates a unique ID for a bid randomly.
     * @return the generated bid ID
     */
    public int generate_BidId(){
        Random r = new Random();
        int low = 1000;
        int high = 9999;

        int randomId = r.nextInt(high - low) + low;
        while (bid_IDs.contains(randomId))     // To prevent duplicates of ID
            randomId = r.nextInt(high - low) + low;

        return randomId;
    }

    /**
     * This method creates a new bid and store the bid in bids_hashmap.
     * @param buyerID the Buyer ID
     * @param bid_price the price of bid
     * @param bid_date the date when the bid is made
     * @exception BidException if bid_price or bid_date is not in range or in wrong format.
     */
    public void addBid(int buyerID, int bid_price, String bid_date){
        int randomId = generate_BidId();
        bid_IDs.add(randomId);
        Bid bid = new Bid(randomId, buyerID, bid_price, bid_date);
        bids_hashmap.put(buyerID, bid);
        System.out.println("New Bid Added");
    }
}
