package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Dinosaur;

/**
 * The action for a unconscious dinosaur
 * @see game.dinosaurs.Dinosaur
 * @see game.Item.immovableitem.Corpse
 */
public class UnconsciousAction extends Action {

    /**
     * Turn left until death
     */
    private int turnleft = 0;

    /**
     * Checks whether unconsciousness is due to starvation or not
     */
    private Boolean starving;

    /**
     * Constructor
     * @param turnleft Turn left until death
     * @param starving unconsciousness is due to starvation or not
     */
    public UnconsciousAction(int turnleft, Boolean starving) {
        this.turnleft = turnleft;
        this.starving = starving;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        String die_msg = actor + " died due to ";
        if (starving)
            die_msg += "starvation";
        else
            die_msg += "dehydration";

        if (turnleft > 0){
            if (starving){
                dinosaur.decrement_HungryDieTurn();}
            else{
                dinosaur.decrement_ThirstyDieTurn();}
        }
        else{
            // Remove Actor and add corpse
            Location actorlocation = map.locationOf(dinosaur);
            map.removeActor(dinosaur);
            actorlocation.addItem(dinosaur.getNewCorpse());
            return die_msg;
        }
        return menuDescription(dinosaur);
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        String reason;
        if (starving)
            reason = "due to hunger";
        else
            reason = "due to dehydration";

        return actor + " is unconscious " + reason + " and dies in " + turnleft + " turns!";
    }
}
