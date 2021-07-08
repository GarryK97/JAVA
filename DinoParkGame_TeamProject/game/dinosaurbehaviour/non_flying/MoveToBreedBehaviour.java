package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.dinoparkground.DinoParkGround;
import game.dinosaurbehaviour.BreedingChecking;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurCapability;

/**
 * Behaviour for Dinosaurs to move to another dinosaur to breed
 * @see BreedingChecking
 * @see Dinosaur
 * @see MoveActorAction
 */
public class MoveToBreedBehaviour extends BreedingChecking{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(). return null if there is no nearby dinosaur to breed or cannot go close.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (!checkAdult(actor)){
            return null;
        }

        Actor target = getNearbyDinosaurToBreed(actor, map);
        if (target == null){
            return null;
        }

        Location actorlocation = map.locationOf(actor);
        Location targetlocation = map.locationOf(target);

        Location destination = getLocationOneStep(actor, actorlocation, targetlocation);

        if (destination != null){
            return new MoveActorAction(destination, "to breed");
        }
        return null;
    }

    /**
     * Gets nearby dinosaur to breed by checking conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the nearby dinosaur which the actor can mate.
     */
    private Actor getNearbyDinosaurToBreed(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);

        int detection_squares = 4;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), detection_squares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), detection_squares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs){
            for (int y : ys){
                if (max_xs.contains(x) && max_ys.contains(y)){
                    Actor target_actor = map.at(x,y).getActor();
                    if (target_actor != null && checkAdult(target_actor) && checkNotPregnant(target_actor)){
                        if(checkOppositeGender(actor, target_actor) && checkSameSpecies(actor, target_actor)){
                            return target_actor;
                        }
                    }
                }
            }
        }
        return null;
    }

}
