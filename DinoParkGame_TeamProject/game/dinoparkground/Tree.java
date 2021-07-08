package game.dinoparkground;

import edu.monash.fit2099.engine.Location;
import game.Item.portableitem.Fruit;

/**
 * A class that represents a Tree
 * @see game.dinoparkground.Plant
 */
public class Tree extends Plant {

	/**
	 * The probability of dropping a fruit from a tree
	 */
	private int prob_dropFruit = 5;

	/**
	 * Constructor with the probability of producing a fruit
	 */
	public Tree() {
		super('+', 50);
	}

	/**
	 * A method to deal with Tree when a turn passes.
	 * @param location The location of the Tree
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		produceFruit();
		dropFruit(location);

		age++;
	}

	/**
	 * A method to deal with a Tree dropping a fruit
	 * @param location The location of this Tree
	 */
	public void dropFruit(Location location){
		if (random.nextInt(100) < prob_dropFruit){
			if(takeFruit(1) != -1){
				location.addItem(new Fruit());
			}
		}
	}

	/**
	 * Checks whether a plant can grow at Tree
	 * @return false
	 */
	@Override
	public Boolean isGrowable() {
		return false;
	}

	/**
	 * Checks whether a Stegosaur can eat Tree
	 * @return true
	 */
	@Override
	public Boolean canStegoEat() {
		return false;
	}

	/**
	 * Checks whether a Brachiosaur can eat Tree
	 * @return false
	 */
	@Override
	public Boolean canBrachioEat() {
		return true;
	}

	/**
	 * Checks whether Tree can drop a fruit or not
	 * @return false
	 */
	@Override
	public Boolean canDropFruit() {
		return true;
	}

	/**
	 * Checks whether Tree can be destroyed or not
	 * @return true
	 */
	@Override
	public Boolean isDestructible() {
		return false;
	}
}