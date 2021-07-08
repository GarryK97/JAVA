package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action to quit game
 */

public class QuitGameAction extends Action {

    /**
     * Performs action to quit game by removing player from map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player has quit the game";
    }

    /**
     * Displays menu to quit game.
     * @param actor The actor performing the action.
     * @return a descriptive string of this action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit game";
    }
}
