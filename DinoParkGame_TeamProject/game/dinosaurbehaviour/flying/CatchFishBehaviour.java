package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.action.CatchFishAction;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Lake;
import game.dinosaurbehaviour.Behaviour;
import game.dinosaurs.Dinosaur;

import java.util.Random;

/**
 * Behaviour that represents a flying dinosaur catching fish from a lake.
 * @see Behaviour
 * @see Actor
 * @see GameMap
 * @see Lake
 * @see Dinosaur
 * @see CatchFishAction
 */
public class CatchFishBehaviour implements Behaviour{

    /**
     * Random number generator
     */
    private Random random = new Random();

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return CatchFishAction(). null if an error occurred
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        if (actorlocation.getGround() instanceof Lake){
            int catch_num = random.nextInt(3);
            return new CatchFishAction(catch_num, ((Dinosaur)actor).getDrink_Water_Increase());
        }

        return null;
    }
}
