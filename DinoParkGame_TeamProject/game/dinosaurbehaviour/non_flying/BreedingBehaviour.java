package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.action.BreedAction;
import game.dinosaurbehaviour.BreedingChecking;

/**
 * Class to represent Breeding Behaviour of dinosaurs
 * @see BreedingChecking
 * @see BreedAction
 *
 */
public class BreedingBehaviour extends BreedingChecking{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return BreedAction(). return null if there is no dinosaur to mate or the actor recently mated
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // if actor recently mated
        if (!checkNotPregnant(actor)){
            return null;
        }

        Location actorlocation = map.locationOf(actor);

        for (Exit exit : actorlocation.getExits()){
            Location location = exit.getDestination();
            Actor target = location.getActor();
            if (target != null && !(target instanceof Player) && checkSameSpecies(actor, target)){
                if(checkOppositeGender(actor, target) && checkNotPregnant(target)){
                    return (new BreedAction(target));
                }
            }
        }
        return null;
    }

}
