package game.Item.portableitem;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.Item.CarnivoreFood;
import game.Player;
import game.dinosaurs.Dinosaur;

/**
 * Abstract class that is used to create Egg objects for Dinosaurs
 * @see PortableItem
 * @see CarnivoreFood
 */
public abstract class Egg extends PortableItem implements CarnivoreFood {

    /**
     * The age of this Egg. Used to hatch the egg
     */
    protected int age = 0;

    /**
     * The Dinosaur that is hatched from this Egg
     */
    private Dinosaur hatching_dinosaur;

    /**
     * The hatching age of this Egg
     */
    private int hatch_turn = 0;

    /**
     * The Eco Point Increase amount when the Egg hatches
     */
    protected int eco_point_hatch = 0;

    /**
     * Constructor
     * @param name The name of Egg
     * @param dinosaur The Dinosaur that is hatched from this Egg
     * @param hatch_turn The hatching age of this Egg
     * @param eco_point_hatch The Eco Point Increase amount when the Egg hatches
     */
    public Egg(String name, Dinosaur dinosaur, int hatch_turn, int eco_point_hatch) {
        super(name, 'O');
        this.hatching_dinosaur = dinosaur;
        this.hatch_turn = hatch_turn;
        this.eco_point_hatch = eco_point_hatch;
    }

    /**
     * Checks whether Egg is Carnivore Food or not
     * @return true
     */
    @Override
    public Boolean isCarnivoreFood() {
        return true;
    }

    /**
     * Checks whether Egg is Herbivore Food or not
     * @return false
     */
    @Override
    public Boolean isHerbivoreFood() {
        return false;
    }

    /**
     * Gets the Food level increase amount of this Egg
     * @return 10
     */
    @Override
    public int getFoodIncrease(){
        return 10;
    }

    /**
     * Checks whether Egg is Meal kit or not
     * @return false
     */
    @Override
    public Boolean isMealKit(){
        return false;
    }

    /**
     * A method to deal with Egg when a turn passes when the Egg is on the ground.
     * @param location The location of this Egg
     */
    @Override
    public void tick(Location location){
        // if Egg can be hatched and there is no actor on the same location
        if (age >= hatch_turn && location.getActor() == null){
            // Remove Egg and Adds Dinosaur at the location
            location.removeItem(this);
            location.addActor(hatching_dinosaur);
            Player.add_points(eco_point_hatch);
        }
        age++;
    }

    /**
     * A method to deal with Egg when a turn passes when the Egg is carried by an Actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        age++;
    }
}
