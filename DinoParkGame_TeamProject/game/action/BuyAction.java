package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Item.DinoParkItem;
import game.Player;

/**
 * Action for Buying Items
 * @see game.Item
 * @see game.Player
 */
public class BuyAction extends Action {

    /**
     * The item to buy
     */
    private DinoParkItem item;

    /**
     * The price of the item
     */
    private int price;

    /**
     * Constructor
     * @param item The item to buy
     */
    public BuyAction(DinoParkItem item) {
        this.item = item;
        this.price = item.getPrice();
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (Player.deduct_points(price)){
            actor.addItemToInventory((Item)item);
            return actor + " purchased " + item + " for " + price + "points.";
        }
        else{
            return actor + " has not enough Eco Points";
        }
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + item + " for " + price + " points.";
    }
}
