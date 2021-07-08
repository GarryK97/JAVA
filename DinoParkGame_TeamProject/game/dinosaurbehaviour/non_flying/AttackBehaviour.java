package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.action.AttackAction;
import game.dinosaurbehaviour.Behaviour;
import game.dinosaurs.Carnivore;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Herbivore;

/**
 * The attack behaviour for carnivore dinosaurs
 * @see game.dinosaurbehaviour.Behaviour
 * @see game.action.AttackAction
 * @see game.dinosaurs.Carnivore
 * @see game.dinosaurs.Herbivore
 */
public class AttackBehaviour implements Behaviour {

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
            Actor target = exit.getDestination().getActor();
            if (target != null && !(target instanceof Player)){
                Carnivore attacker = (Carnivore) actor;
                Dinosaur victim = (Dinosaur) target;
                if (victim.can_CarnivoreAttack() && !attacker.attacked_Before(victim)) {
                    attacker.add_Victim(victim);
                    return new AttackAction(target, actor.getWeapon().damage());
                }
            }
        }
        return null;
    }
}
