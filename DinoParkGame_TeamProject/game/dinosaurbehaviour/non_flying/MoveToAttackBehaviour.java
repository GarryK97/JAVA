package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.dinosaurbehaviour.LocationMoveCompute;
import game.dinosaurs.Carnivore;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Herbivore;

/**
 * Behaviour for Carnivores moving to another dinosaur to attack
 * @see LocationMoveCompute
 * @see Dinosaur
 * @see Carnivore
 * @see Herbivore
 * @see MoveActorAction
 */
public class MoveToAttackBehaviour extends LocationMoveCompute {

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(). return null if there is no nearby dinosaur or cannot go close.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur target = getNearbyTarget(actor, map);
        if (target == null){
            return null;
        }

        Location onesteplocation = getLocationOneStep(actor, map.locationOf(actor), map.locationOf(target));
        if (onesteplocation == null){
            return null;
        }

        return new MoveActorAction(onesteplocation, "to attack " + target);
    }

    /**
     * Returns the victim of attack if there is a nearby herbivore.
     * @param actor the Actor attacking
     * @param map the GameMap containing the Actor
     * @return the Herbivore victim
     */
    public Dinosaur getNearbyTarget(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        int target_detectsquares = 5;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), target_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), target_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs) {
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    Actor target = map.at(x, y).getActor();
                    if (target != null && !(target instanceof Player)) {
                        if (((Dinosaur) target).can_CarnivoreAttack()) {
                            if (!((Carnivore)actor).attacked_Before((Dinosaur)target)){
                                return (Dinosaur)target;}
                        }
                    }
                }
            }
        }
        return null;
    }
}
