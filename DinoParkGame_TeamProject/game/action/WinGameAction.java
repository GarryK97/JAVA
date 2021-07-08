package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

/**
 * Action to decide whether player won challenge mode or not.
 */

public class WinGameAction extends Action {

    /**
     * Performs action to remove player from map when specified number of moved are reached.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string of result of game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Returns the result of the game.
     * @param actor The actor performing the action.
     * @return a descriptive string of result of game.
     */
    @Override
    public String menuDescription(Actor actor) {
        String result;
        if (Player.getEcoPoints() >= Player.getEcoPointsNeeded()){
            result = "Congratulations you won!";
        }
        else
            result = "You lost!";
        return result;
    }
}