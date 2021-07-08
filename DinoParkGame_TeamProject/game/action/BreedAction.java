package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Item.portableitem.Egg;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurCapability;
import game.dinosaurs.Gender;

/**
 * Breeding Action of Dinosaurs
 * @see game.dinosaurs.Dinosaur
 */
public class BreedAction extends Action {

    /**
     * The another dinosaur with opposite gender to breed
     */
    private Actor target;

    /**
     * Constructor
     * @param target The another dinosaur with opposite gender to breed
     */
    public BreedAction(Actor target) {
        this.target = target;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor female_actor;

        // Finds a female dinosaur
        if (((Dinosaur) actor).getGender() == Gender.FEMALE){
            female_actor = actor;
        }
        else{
            female_actor = target;
        }

        // Adds egg at the female dinosaur's location
        Location female_location = map.locationOf(female_actor);
        female_location.addItem(((Dinosaur)female_actor).getNewEgg());

        // Set them to Pregnant to prevent multiple breeding
        ((Dinosaur) female_actor).setPregnant();
        ((Dinosaur) actor).setPregnant();

        return actor + " mated successfully";
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " breeds with another " + target;
    }
}
