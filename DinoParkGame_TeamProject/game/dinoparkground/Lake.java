package game.dinoparkground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.FlyingDinosaur;

import java.util.Random;

/**
 * Class that represents a Lake
 * @see Actor
 * @see Ground
 * @see Location
 * @see DinoParkGround
 */
public class Lake extends Ground implements DinoParkGround{

    /**
     * The Total Water capacity of this lake
     */
    private int capacity = 25;

    /**
     * Maximum number of fish
     */
    private final int MAX_FISH_NUMBER = 25;

    /**
     * Probability of a new fish to be born in this lake
     */
    private final int FISH_BORN_PROB = 60;

    /**
     * The number of fish of this lake
     */
    private int fish_number = 5;

    /**
     * Random number generator
     */
    private Random random = new Random();

    /**
     * Constructor.
     */
    public Lake() {
        super('~');
    }

    /**
     * A method to deal with Lake when a turn passes.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (random.nextInt(100) < FISH_BORN_PROB){
            fish_number += 1;
        }
    }

    /**
     * Checks whether a plant can grow at Lake
     * @return false
     */
    @Override
    public Boolean isGrowable() {
        return false;
    }

    /**
     * Check whether Lake is a plant or not
     * @return false
     */
    @Override
    public Boolean isPlant() {
        return false;
    }

    /**
     * Check whether Lake is water-based or not
     * @return false
     */
    @Override
    public Boolean isWater() {
        return true;
    }

    /**
     * Adds the water in this lake
     * @param amount The increase of water
     */
    public void addCapacity(int amount){
        capacity += amount;
    }

    /**
     * Takes a sip from this lake if possible
     * @return true if this lake has water
     */
    public Boolean TakeOneSip() {
        if (capacity >= 1){
            capacity -= 1;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Getter for fish_number
     * @return fish_number
     */
    public int getFish_Number(){
        return fish_number;
    }

    /**
     * Method to remove fish from this lake
     * @param catch_num the number of fish that should be removed.
     * @return removed number of fish
     */
    public int removeFish(int catch_num){
        // if there is no fish
        if (fish_number == 0){
            return 0;
        }

        // if there is enough fish
        else if (fish_number - catch_num >= 0) {
            this.fish_number -= catch_num;
            return catch_num;
        }

        // if there is fish, but not enough fish to remove.
        else{
            int max_possible_catch = this.fish_number;
            this.fish_number = 0;
            return max_possible_catch;
        }
    }

    /**
     * Checks whether an actor can enter or not
     * @param actor the Actor to check
     * @return true if the actor is flying, else false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof FlyingDinosaur) {
            if (((FlyingDinosaur) actor).getFlying()){
                return true;
            }
        }
        return false;
    }
}
