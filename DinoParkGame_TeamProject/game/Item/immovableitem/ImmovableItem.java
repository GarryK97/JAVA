package game.Item.immovableitem;

import edu.monash.fit2099.engine.Item;
import game.Item.DinoParkItem;

/**
 *
 */
public abstract class ImmovableItem extends Item implements DinoParkItem {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ImmovableItem(String name, char displayChar) {
        super(name, displayChar, false);
    }
}
