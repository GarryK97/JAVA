package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Item.DinoParkItem;
import game.action.StayEatAction;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Plant;

/**
 * Eating Behaviour for a Stegosaur
 * @see HerbiEatBehaviour
 * @see StayEatAction
 * @see Plant
 */
public class StegoEatBehaviour extends HerbiEatBehaviour {

    /**
     * Constructor
     * @param eat_hp_increase HP increase amount per food when a herbivore eats
     */
    public StegoEatBehaviour(int eat_hp_increase) {
        super(eat_hp_increase);
    }

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(). return null if there is no nearby food or cannot go close to food
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location actorlocation = map.locationOf(actor);

        for (Exit exit : actorlocation.getExits()){
            DinoParkGround ground = (DinoParkGround) exit.getDestination().getGround();
            if (ground.isPlant() && ((Plant)ground).canStegoEat()){
                if (((Plant)ground).takeFruit(1) != -1) {
                    return (new StayEatAction(eat_hp_increase));
                }
            }
            else{
                for (Item item : exit.getDestination().getItems()){
                    if (((DinoParkItem)item).isHerbivoreFood()){
                        // Remove the food when a Stegosaur eats
                        exit.getDestination().removeItem(item);
                        if (!((DinoParkItem)item).isMealKit()) {
                            return (new StayEatAction(eat_hp_increase));
                        }
                        else{
                            return (new StayEatAction(999));
                        }
                    }
                }
            }
        }
        return null;
    }
}
