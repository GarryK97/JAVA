package edu.monash.fit2099.exceptions;

/**
 * Custom Exception, which extends VehicleException to handle errors in Sedan Class.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.vehicles.Sedan
 * @see edu.monash.fit2099.exceptions.VehicleException
 */
public class SedanException extends VehicleException{

    public SedanException(String message) {
        super(message);
    }
}
