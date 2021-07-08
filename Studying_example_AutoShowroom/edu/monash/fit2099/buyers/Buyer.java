package edu.monash.fit2099.buyers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Buyer contains the data of a buyer. The data can be accessed and modified by using setters and getters.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 */
public class Buyer {
    /**
     * ID of the buyer
     */
    private int buyerID;

    /**
     * The given name (first name) of the buyer
     */
    private String givenName;

    /**
     * The family name (last name) of the buyer
     */
    private String familyName;

    /**
     * It stores all the buyer IDs generated. It is used to avoid duplicates of ID.
     */
    public static ArrayList<Integer> buyer_IDs = new ArrayList<>();

    /**
     * Zero-param constructor for 'getInstance' method
     */
    private Buyer(){this.buyerID = generate_buyerId();}

//    private Buyer(int newBuyerId) {
//        this.buyerID = newBuyerId;
//    }
//
//    private Buyer(int newBuyerId, String newGivenName, String newFamilyName) {
//        this.buyerID = newBuyerId;
//        this.givenName = newGivenName;
//        this.familyName = newFamilyName;
//    }
//
//    private Buyer(String newGivenName, String newFamilyName){
//        this.givenName = newGivenName;
//        this.familyName = newFamilyName;
//
//        int randomId = generate_buyerId();
//        this.buyerID = randomId;
//        buyer_IDs.add(randomId);
//    }

    /**
     * This method generates a unique ID for a buyer randomly.
     * @return The generated buyer ID
     */
    private int generate_buyerId() {
        Random r = new Random();
        int low = 1000;
        int high = 9999;

        int randomId = r.nextInt(high - low) + low;
        while (buyer_IDs.contains(randomId))     // To prevent duplicates of ID
            randomId = r.nextInt(high - low) + low;
        return randomId;
    }

    /**
     * This method is the static factory method for error handling process. Used as constructor
     * @param givenName given name of the buyer
     * @param familyName family name of the buyer
     * @return return the buyer constructed if there is no error. return null if an error occurred.
     */
    public static Buyer getInstance(String givenName, String familyName){
        Buyer buyer = new Buyer();
        if (buyer.setGivenName(givenName) && buyer.setFamilyName(familyName)){
            return buyer;
        }
        return null;
    }

    /**
     * Getter of buyer ID
     * @return the buyer ID
     */
    public int getBuyerID() {
        return buyerID;
    }

    /**
     * Setter to modify the given name of a buyer
     * @param givenName given name of a buyer
     * @return return true if the length of given name is in range. Otherwise, return false.
     */
    public boolean setGivenName(String givenName) {
        if (2 <= givenName.length() && givenName.length() <= 15) {
            this.givenName = givenName;
            return true;
        }
        return false;
    }

    /**
     * Setter to modify the family name of a buyer
     * @param familyName family name of a buyer
     * @return return true if the length of family name is in range. Otherwise, return false.
     */
    public boolean setFamilyName(String familyName) {
        if (2 <= familyName.length() && familyName.length() <= 15) {
            this.familyName = familyName;
            return true;
        }
        return false;
    }

    /**
     * This method returns a String that contains all the information of a buyer.
     * @return a String in format (Buyer ID:Buyer Name)
     */
    public String description(){
        String descrp = (buyerID + ":" + givenName + " " + familyName);
        return descrp;
    }

}
