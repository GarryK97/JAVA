package game.Item.portableitem;

import edu.monash.fit2099.engine.Item;
import game.Item.DinoParkItem;

/**
 * Base class for any item that can be picked up and dropped.
 * @see Item
 * @see DinoParkItem
 */
public abstract class PortableItem extends Item implements DinoParkItem {

	/**
	 * Constructor
	 * @param name the name of this Item
	 * @param displayChar the character to use to represent this item if it is on the ground
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
