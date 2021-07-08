package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.action.DrinkAction;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Lake;
import game.dinosaurbehaviour.Behaviour;
import game.dinosaurs.Dinosaur;

/**
 * Behaviour that represents a dinosaur drinks water from nearby water source
 * @see Behaviour
 * @see Actor
 * @see GameMap
 * @see DinoParkGround
 * @see Lake
 */
public class DrinkBehaviour implements Behaviour{

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        for (Exit exit : actorlocation.getExits()){
            DinoParkGround ground = (DinoParkGround) exit.getDestination().getGround();
            if (ground.isWater()){
                if (((Lake)ground).TakeOneSip()) {
                    return (new DrinkAction(((Dinosaur)actor).getDrink_Water_Increase()));
                }
            }
        }
        return null;
    }
}
