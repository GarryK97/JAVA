package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.dinoparkground.Tree;
import game.dinosaurbehaviour.BreedingChecking;
import game.dinosaurs.FlyingDinosaur;

/**
 * Behaviour that represents breeding of flying dinosaurs
 * @see BreedingChecking
 * @see FlyingDinosaur
 * @see Actor
 * @see GameMap
 * @see MoveActorAction
 */
public class FlyDinoMoveToBreedBehaviour extends BreedingChecking{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(), null if there is an error
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // if actor is not adult, cannot breed
        if (!checkAdult(actor)){
            return null;
        }

        Location targetlocation = getTargetTreeLocation(actor, map);
        if (targetlocation == null){
            return null;
        }

        Location actorlocation = map.locationOf(actor);
        Location destination = getLocationOneStep(actor, actorlocation, targetlocation);

        if (destination != null){
            return new MoveActorAction(destination, "to tree to breed");
        }
        return null;
    }

    /**
     * Gets the location of a tree that is next to the breedable dinosaur
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the tree location, null if there is no breedable dinosaur or there is no tree to land.
     */
    private Location getTargetTreeLocation(Actor actor, GameMap map){
        Location targetlocation = map.locationOf(getBreedableDinoOnTree(actor, map));
        if (targetlocation == null){
            return null;
        }

        for (Exit exit : targetlocation.getExits()){
            Ground ground = exit.getDestination().getGround();
            if (ground instanceof Tree && ground.canActorEnter(actor)){
                return exit.getDestination();
            }
        }
        return null;
    }

    /**
     * Gets nearby flying dinosaur to breed by checking conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the nearby dinosaur which the actor can mate.
     */
    private Actor getBreedableDinoOnTree(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);

        int detection_squares = 30;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), detection_squares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), detection_squares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs){
            for (int y : ys){
                if (max_xs.contains(x) && max_ys.contains(y)){
                    Actor target_actor = map.at(x,y).getActor();
                    if (target_actor != null && !(target_actor instanceof Player) && checkAdult(target_actor) && checkNotPregnant(target_actor)){
                        if(checkOppositeGender(actor, target_actor) && checkSameSpecies(actor, target_actor)){
                            if (((FlyingDinosaur)target_actor).getOn_Tree()){
                                return target_actor;}
                        }
                    }
                }
            }
        }
        return null;
    }
}
