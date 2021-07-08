package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Corpse;
import game.Item.immovableitem.PteroCorpse;
import game.Item.portableitem.Egg;
import game.Item.portableitem.EggPtero;


/**
 * Class that represents a Pterodactyls which is a flying dinosaur
 * @see FlyingDinosaur
 * @see EggPtero
 * @see PteroCorpse
 */
public class Pterodactyls extends FlyingDinosaur{

    /**
     * Constructor
     * @param gender Gender of the Dinosaur
     */
    public Pterodactyls(Gender gender, int rest_turn) {
        super("Pterodactyls", 'p', 60, 50, 0, 15, 15, 30, gender, rest_turn);
    }

    /**
     * Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns the selected Action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        checkBaby('P');
        checkPregnant();

        check_OnTree(map);
        tick_FlyTurn(map);
        tick_RestTurn();

        Action action = getActionToFly(map);    // a Behaviour to fly again will be prioritized
        if (action != null)
            return action;

        return essential_PlayTurn(map);
    }

    /**
     * Gets the appropriate Egg object
     * @return Egg of Pterodactyls
     */
    @Override
    public Egg getNewEgg() {
        return new EggPtero();
    }

    /**
     * Gets the appropriate Corpse object
     * @return Corpse of Pterodactyls
     */
    @Override
    public Corpse getNewCorpse() {
        return new PteroCorpse();
    }

    /**
     * Checks whether this dinosaur is a carnivore or not.
     * @return true
     */
    @Override
    public Boolean isCarnivore() {
        return true;
    }
}
