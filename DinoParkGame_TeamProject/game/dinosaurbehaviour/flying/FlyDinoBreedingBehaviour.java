package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.action.BreedAction;
import game.dinoparkground.Tree;
import game.dinosaurbehaviour.BreedingChecking;
import game.dinosaurs.FlyingDinosaur;

/**
 * Behaviour that represents a breeding behaviour of flying dinosaurs
 * @see BreedingChecking
 * @see FlyingDinosaur
 * @see Actor
 * @see GameMap
 * @see Location
 * @see BreedAction
 */
public class FlyDinoBreedingBehaviour extends BreedingChecking{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return BreedAction(). return null if conditions are not met.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // if actor recently mated
        if (!checkNotPregnant(actor)){
            return null;
        }

        Location actorlocation = map.locationOf(actor);
        FlyingDinosaur fly_actor = (FlyingDinosaur) actor;

        if (actorlocation.getGround() instanceof Tree) {
            for (Exit exit : actorlocation.getExits()) {
                Location location = exit.getDestination();
                Actor target = location.getActor();
                if (target != null && !(target instanceof Player) && checkSameSpecies(actor, target) && checkAdult(actor)) {
                    if (checkOppositeGender(actor, target) && checkNotPregnant(target)) {
                        if (((FlyingDinosaur) target).getOn_Tree()) {
                            fly_actor.land(map);    // Lands on the tree before breed
                            return (new BreedAction(target));
                        }
                    }
                }
            }
        }
        return null;
    }
}
