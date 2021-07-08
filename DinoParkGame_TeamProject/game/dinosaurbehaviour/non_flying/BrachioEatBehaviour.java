package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.action.StayEatAction;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Plant;
import game.dinosaurs.Brachiosaur;

/**
 * The eating behaviour for a Brachiosaur
 * @see Brachiosaur
 * @see game.dinoparkground.Plant
 * @see game.action.StayEatAction
 */
public class BrachioEatBehaviour extends HerbiEatBehaviour {

    /**
     * Constructor
     * @param eat_hp_increase HP increase amount per food when a herbivore eats
     */
    public BrachioEatBehaviour(int eat_hp_increase) {
        super(eat_hp_increase);
    }

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return AttackAction(). return null if there is no victim on next squares.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location actorlocation = map.locationOf(actor);

        for (Exit exit : actorlocation.getExits()){
            DinoParkGround ground = (DinoParkGround) exit.getDestination().getGround();
            if (ground.isPlant() && ((Plant)ground).canBrachioEat()){
                int maxeat_fruit = random.nextInt(((Plant)ground).getFruit());
                int eat_fruit = ((Plant)ground).takeFruit(maxeat_fruit);
                if (eat_fruit != -1) {
                    return (new StayEatAction(eat_fruit, eat_hp_increase));
                }
            }
        }
        return null;
    }
}
