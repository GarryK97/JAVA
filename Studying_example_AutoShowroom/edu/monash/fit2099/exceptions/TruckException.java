package edu.monash.fit2099.exceptions;

/**
 * Custom Exception, which extends VehicleException to handle errors in Truck Class.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.vehicles.Truck
 * @see edu.monash.fit2099.exceptions.VehicleException
 */
public class TruckException extends VehicleException{

    public TruckException(String message) {
        super(message);
    }
}
