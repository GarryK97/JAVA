package edu.monash.fit2099.exceptions;

/**
 * Custom Exception, which extends Exception to handle errors in Bid Class.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.bids.Bid
 */
public class BidException extends Exception{

    public BidException(String message) {
        super(message);
    }
}
