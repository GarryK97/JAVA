package edu.monash.fit2099.vehicles;

import edu.monash.fit2099.exceptions.SedanException;
import edu.monash.fit2099.exceptions.TruckException;
import edu.monash.fit2099.exceptions.VehicleException;
import edu.monash.fit2099.vehicles.Vehicle;

/**
 * Truck that extends Vehicle.
 * @author Yeonsoo Kim (29584612)
 * @version 1.0
 * @see edu.monash.fit2099.exceptions.TruckException
 * @see edu.monash.fit2099.exceptions.VehicleException
 */
public class Truck extends Vehicle {
    /**
     * The capacity of a truck
     */
    private int capacity;

    /**
     * The number of wheels of a truck
     */
    private int wheels;

    /**
     * Constructor for Truck, which does not require Vehicle ID. ID will be auto-generated.
     * @param make The name of maker
     * @param model The name of model
     * @param capacity The capacity of a truck
     * @param wheels The number of wheels
     * @throws TruckException if capacity or wheels are out of range.
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Truck(String make, String model, int capacity, int wheels) throws TruckException, VehicleException {
        super(make, model);
        if (setCapacity(capacity) && setWheels(wheels)) {
            this.capacity = capacity;
            this.wheels = wheels;
        } else {
            throw new TruckException("Incorrect Capacity OR Wheels");
        }
    }

    /**
     * Constructor for Truck, including Vehicle ID.
     * @param make The name of maker
     * @param model The name of model
     * @param vId Vehicle ID
     * @param capacity Capacity of a truck
     * @param wheels The number of wheels
     * @throws TruckException if capacity or wheels are out of range.
     * @throws VehicleException if length of maker and model is not in range. Both must be in between 3 and 15.
     */
    public Truck(String make, String model, int vId, int capacity, int wheels) throws TruckException, VehicleException {
        super(make, model, vId);
        if (setCapacity(capacity) && setWheels(wheels)) {
            this.capacity = capacity;
            this.wheels = wheels;
        } else {
            throw new TruckException("Incorrect Capacity OR Wheels");
        }
    }

    /**
     * Setter to change capacity
     * @param capacity capacity of a truck
     * @return return true if capacity is in range. Otherwise, return false. Capacity must be in between 1 and 15.
     */
    public boolean setCapacity(int capacity) {
        if (1 <= capacity && capacity <= 15) {
            this.capacity = capacity;
            return true;
        }
        return false;
    }

    /**
     * Setter to change wheels
     * @param wheels the number of wheels
     * @return return true if wheels is in range. Otherwise, return false. Wheels must be in between 4 and 16.
     */
    public boolean setWheels(int wheels) {
        if (4 <= wheels && wheels <= 16) {
            this.wheels = wheels;
            return true;
        }
        return false;
    }
}
