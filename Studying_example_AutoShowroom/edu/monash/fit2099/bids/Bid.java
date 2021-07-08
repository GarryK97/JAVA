package edu.monash.fit2099.bids;

import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.BidException;

/**
 * Bid is used to store the data of a bid, including the bid ID, buyer ID, the price of a bid and the date when bid is made.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.exceptions.BidException
 */
public class Bid {
    /**
     * ID of a bid
     */
    private int bidID;

    /**
     * ID of the buyer who made the bid
     */
    private int buyerId;

    /**
     * Price of the bid
     */
    private int price;

    /**
     * The date when a bid is made
     */
    private String dateBid;

    /**
     * Constructor for Bid class.
     * @param bidID ID of a bid
     * @param buyerId ID of the buyer
     * @param price price of the bid
     * @param dateBid date when the bid is made
     * @throws BidException if price is negative, or dateBid is in wrong format.
     */
    public Bid(int bidID, int buyerId, int price, String dateBid) throws BidException {
        if (setPrice(price) && setDateBid(dateBid)) {
            this.bidID = bidID;
            this.buyerId = buyerId;
            this.price = price;
            this.dateBid = dateBid;
        } else {
            throw new BidException("Incorrect Price OR Date of Bid");
        }
    }

    /**
     * Getter for Bid ID
     * @return the Bid ID
     */
    public int getBidID() {
        return bidID;
    }

    /**
     * Setter for Bid ID
     * @param bidID a new Bid ID
     */
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    /**
     * Getter for Buyer ID of a bid
     * @return the Buyer ID
     */
    public int getBuyerId() {
        return buyerId;
    }

    /**
     * Setter for Buyer ID
     * @param buyerId the Buyer ID to change
     */
    public void setBuyerId(int buyerId) { this.buyerId = buyerId; }

    /**
     * Getter for the price of a bid
     * @return the price of a bid
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for the price of a bid
     * @param price a new price to change
     * @return return true if price is positive. return false if price is negative(Invalid).
     */
    public boolean setPrice(int price) {
        if (price > 0) {
            this.price = price;
            return true;
        }
        else
            return false;
    }

    /**
     * Getter for the Date of the bid
     * @return the Date when a bid is made
     */
    public String getDateBid() {
        return dateBid;
    }

    /**
     * Setter for the Date of the bid
     * @param dateBid the Date when a bid is made
     * @return return true if the date is in range and in correct format.
     * return false if the date is out of range or in wrong format
     */
    public boolean setDateBid(String dateBid) {
        String[] parts_dateBid = dateBid.split("/");
        boolean is_accepted = false;

        if (parts_dateBid.length == 3) {
            int dd = Integer.parseInt(parts_dateBid[0]);
            int mm = Integer.parseInt(parts_dateBid[1]);
            int yy = Integer.parseInt(parts_dateBid[2]);

            if ((1 <= dd && dd <= 31) && (1 <= mm && mm <= 12) && (1930 <= yy && yy <= 2021)){
                is_accepted = true;
                this.dateBid = dateBid;
            }
        }
        return is_accepted;
    }

    /**
     * This method returns a String that contains all the information of a bid.
     * @return formatted string of all the information of a bid
     */
    public String description(){return ("Bid ID:"+bidID+"|"+"Buyer ID:"+buyerId+"| price:"+price+"| Date:" + dateBid);}

}
