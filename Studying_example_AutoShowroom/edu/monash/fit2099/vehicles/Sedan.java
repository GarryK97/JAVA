package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.exceptions.SedanException;
import edu.monash.fit2099.exceptions.VehicleException;
import edu.monash.fit2099.vehicles.Vehicle;

/**
 * Sedan that extends Vehicle.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.exceptions.SedanException
 * @see edu.monash.fit2099.exceptions.VehicleException
 */
public class Sedan extends Vehicle {
    /**
     * The number of seats
     */
    private int seats;

    /**
     * Constructor for a sedan, which does not require Vehicle ID. ID is auto-generated.
     * @param make The name of maker
     * @param model The name of model
     * @param seats the number of seats
     * @throws SedanException if 'seats' not in range. seats must be 4 or 5.
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Sedan(String make, String model, int seats) throws SedanException, VehicleException {
        super(make, model);
        if (setSeats(seats))
            this.seats = seats;
        else
            throw new SedanException("Incorrect Seats");
    }

    /**
     * Constructor for a sedan, including Vehicle ID.
     * @param make The name of maker
     * @param model The name of model
     * @param vId Vehicle ID
     * @param seats the number of seats
     * @throws SedanException if 'seats' not in range. seats must be 4 or 5.
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Sedan(String make, String model, int vId, int seats) throws SedanException, VehicleException{
        super(make, model, vId);
        if (setSeats(seats))
            this.seats = seats;
        else
            throw new SedanException("Incorrect Seats");
    }

    /**
     * Setter to change the seats
     * @param seats the number of seats
     * @return return true if 'seats' is 4 or 5. Otherwise, return false
     */
    public boolean setSeats(int seats) {
        if (4 <= seats && seats <= 5){
            this.seats = seats;
            return true;
        }
        return false;
    }
}
