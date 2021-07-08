package game.dinosaurbehaviour;

import edu.monash.fit2099.engine.*;

/**
 * A class that contains Algorithms to compute the location of moving one step closer to target for behaviour classes
 * @see game.dinosaurbehaviour.Behaviour
 * @see edu.monash.fit2099.engine.Location
 * @see edu.monash.fit2099.engine.Exit
 * @see edu.monash.fit2099.engine.NumberRange
 */
public abstract class LocationMoveCompute implements Behaviour{

    /**
     * A method to compute the location of an actor one step closer to the target.
     * @param actor The actor that will move to target
     * @param actorlocation The location of actor
     * @param targetlocation The location of target
     * @return return the one-step closer location. return null if there is no such location
     */
    protected Location getLocationOneStep(Actor actor, Location actorlocation, Location targetlocation) {
        int currentDistance = distance(actorlocation, targetlocation);
        for (Exit exit : actorlocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, targetlocation);
                if (newDistance < currentDistance) {
                    return destination;
                }
            }
        }
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Gets all x positions which are nearby.
     * @param x_pos the x position to compute
     * @param num_squares the width
     * @return NumberRange that contains all nearby X positions
     */
    protected NumberRange getNearbyXSquares(int x_pos, int num_squares){
        NumberRange xs = new NumberRange(x_pos - num_squares, 2*num_squares +1);
        return xs;
    }

    /**
     * Gets all y positions which are nearby.
     * @param y_pos the x position to compute
     * @param num_squares the height
     * @return NumberRange that contains all nearby Y positions
     */
    protected NumberRange getNearbyYSquares(int y_pos, int num_squares){
        NumberRange ys = new NumberRange(y_pos - num_squares, 2*num_squares +1);
        return ys;
    }
}
