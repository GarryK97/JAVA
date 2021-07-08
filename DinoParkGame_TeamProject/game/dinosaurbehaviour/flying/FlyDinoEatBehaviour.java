package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Corpse;
import game.action.StayEatAction;
import game.dinosaurbehaviour.LocationMoveCompute;
import game.dinosaurs.FlyingDinosaur;

/**
 * Behaviour that represents flying dinosaurs eating corpse when ther is no dinosaur nearby
 * @see LocationMoveCompute
 * @see Actor
 * @see GameMap
 * @see FlyingDinosaur
 * @see StayEatAction
 * @see Corpse
 */
public class FlyDinoEatBehaviour extends LocationMoveCompute{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return StayEatAction(). return null if there is no nearby food or there is a dinosaur nearby
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);
        FlyingDinosaur fly_dino = (FlyingDinosaur) actor;
        for (Item item : actorlocation.getItems()) {
            if (item instanceof Corpse){
                Corpse corpse = (Corpse) item;
                if (!isDinosaurNearby(actor, map)){
                    fly_dino.land(map);
                    int eat_amount = fly_dino.getEat_Amount();
                    int result_foodlevel = corpse.eatPartial(eat_amount);
                    return new StayEatAction(result_foodlevel);
                }
            }
        }
        return null;
    }

    /**
     * Checks whether there is a dinosaur nearby
     * @param actor the Actor acting
     * @param map the gameMap containing the actor
     * @return
     */
    private Boolean isDinosaurNearby(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        int dino_detectsquares = 2;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), dino_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), dino_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs) {
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y) && actorlocation != map.at(x,y)) {
                    if (map.at(x,y).getActor() != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
