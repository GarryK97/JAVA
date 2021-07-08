package game.dinosaurs;


import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Corpse;
import game.Item.immovableitem.StegoCorpse;
import game.Item.portableitem.Egg;
import game.Item.portableitem.EggStego;
import game.action.UnconsciousAction;
import game.dinosaurbehaviour.non_flying.StegoEatBehaviour;
import game.dinosaurbehaviour.non_flying.StegoMoveToFoodBehaviour;
import game.dinosaurs.Gender;
import game.dinosaurs.Herbivore;

/**
 * Class that represents a Stegosaur
 * @see Herbivore
 * @see StegoEatBehaviour
 * @see StegoMoveToFoodBehaviour
 * @see UnconsciousAction
 * @see DoNothingAction
 * @see StegoCorpse
 * @see EggStego
 */
public class Stegosaur extends Herbivore {

	/**
	 * Constructor with Gender specified
	 * @param gender gender of this Allosaur
	 */
	public Stegosaur(Gender gender) {
		super("Stegosaur", 's', 100, 50,0, 20, 30, gender);

		hungrybehaviour.add(new StegoEatBehaviour(FRUIT_HP_INCREASE));
		hungrybehaviour.add(new StegoMoveToFoodBehaviour());
	}

	/**
	 * Constructor without Gender
	 *
	 */
	public Stegosaur() {
		super("Stegosaur", 's', 100, 50,90, 20, 30, null);

		hungrybehaviour.add(new StegoEatBehaviour(FRUIT_HP_INCREASE));
		hungrybehaviour.add(new StegoMoveToFoodBehaviour());
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
		checkBaby('S');
		checkPregnant();

		return essential_PlayTurn(map);
	}

	/**
	 * Gets the appropriate Egg object
	 * @return Egg of Stegosaur
	 */
	@Override
	public Egg getNewEgg() {
		return (new EggStego());
	}

	/**
	 * Gets the appropriate Corpse object
	 * @return Corpse of Stegosaur
	 */
	@Override
	public Corpse getNewCorpse() {
		return (new StegoCorpse());
	}

	/**
	 * Checks whether this Stegosaur can be attacked by Carnivores or not
	 * @return true
	 */
	@Override
	public Boolean can_CarnivoreAttack() {
		return true;
	}

}
