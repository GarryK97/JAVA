package game.dinoparkground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.action.PickFruitAction;

import java.util.Random;

/**
 * Plant class that will be used to implement Living plants
 * @see game.dinoparkground.Bush
 * @see game.dinoparkground.Tree
 * @see game.dinoparkground.DinoParkGround
 * @see game.dinoparkground.PlantProperty
 */
public abstract class Plant extends Ground implements DinoParkGround, PlantProperty {

    /**
     * The age of this plant
     */
    protected int age = 0;

    /**
     * The number of fruits of a plant
     */
    protected int fruit = 0;

    /**
     * The probability of producing a fruit
     */
    private int prob_makeFruit = 0;

    /**
     * Random variable Generator
     */
    protected Random random = new Random();

    /**
     * The probability for fruit picking when an actor picks fruit
     */
    private int pick_prob = 60;

    /**
     * Constructor
     * @param displayChar The character that will be displayed in game
     * @param prob_makeFruit The probability of producing a fruit
     */
    public Plant(char displayChar, int prob_makeFruit) {
        super(displayChar);
        this.prob_makeFruit = prob_makeFruit;
    }

    /**
     * Getter for pick_prob
     * @return The probability for fruit picking when an actor picks fruit
     */
    public int getPickProb(){
        return pick_prob;
    }

    /**
     * Getter for fruit
     * @return The number of fruits of a plant
     */
    public int getFruit() {
        return fruit;
    }

    /**
     * A method to reduce number of fruits
     * @param fruit
     * @return the fruits taken from plant. return -1 if the plant has no fruit.
     */
    public int takeFruit(int fruit) {
        if (this.fruit > 0) {
            if (this.fruit - fruit >= 0) {
                this.fruit -= fruit;
                return fruit;
            }
            else {
                // if a plant has not enough fruits, return the maximum possible number of fruits
                int takenfruit = this.fruit;
                this.fruit = 0;
                return takenfruit;
            }
        }
        return -1; // Plant has no fruit
    }

    /**
     * A method to implement a plant producing a fruit
     */
    public void produceFruit(){
        if (random.nextInt(100) < prob_makeFruit) {
            fruit++;
            Player.add_points(1);
        }
    }

    /**
     * Checks whether the plant is recently created or not
     * @return true if age <= 1, else false.
     */
    public Boolean is_NewPlant() {return (age <= 1);}

    /**
     * Returns the allowable actions that can be taken to this plant
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actions that can be taken
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return new Actions(new PickFruitAction(direction, location));
    }

    /**
     * Check whether this ground is a plant or not
     * @return true if this ground is plant, else false
     */
    public Boolean isPlant(){
        return true;
    }

    /**
     * Check whether Plant is water-based or not
     * @return false
     */
    public Boolean isWater(){
        return false;
    }

}
