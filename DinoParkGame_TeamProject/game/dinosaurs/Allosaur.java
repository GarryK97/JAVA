package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.AllosaurCorpse;
import game.Item.immovableitem.Corpse;
import game.Item.portableitem.Egg;
import game.Item.portableitem.EggAllosaur;
import game.action.UnconsciousAction;
import game.dinosaurbehaviour.Behaviour;
import game.dinosaurs.Carnivore;
import game.dinosaurs.DinosaurCapability;
import game.dinosaurs.Gender;

/**
 * Class that represents an Allosaur
 * @see Carnivore
 * @see UnconsciousAction
 * @see DoNothingAction
 * @see EggAllosaur
 * @see AllosaurCorpse
 * @see IntrinsicWeapon
 */
public class Allosaur extends Carnivore{

    /**
     * Constructor without Gender
     *
     */
    public Allosaur() {
        super("Allosaur", 'a', 100, 20, 50, 20, 50, null);
    }

    /**
     * Constructor with Gender specified
     * @param gender gender of this Allosaur
     */
    public Allosaur(Gender gender){
        super("Allosaur", 'a', 100, 20, 50, 20, 50, gender);
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

        tick_AttackedDino();
        checkBaby('A');
        checkPregnant();

        return essential_PlayTurn(map);
    }

    /**
     * Gets the appropriate Egg object
     * @return Egg of Allosaur
     */
    @Override
    public Egg getNewEgg() {
        return (new EggAllosaur());
    }

    /**
     * Gets the appropriate Corpse object
     * @return Corpse of Allosaur
     */
    @Override
    public Corpse getNewCorpse() {
        return (new AllosaurCorpse());
    }

    /**
     * Returns the IntrinsicWeapon of the Allosaur
     * @return the IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        String verb = "attacks";
        if (this.hasCapability(DinosaurCapability.BABY))
            return new IntrinsicWeapon(10, verb);
        else
            return new IntrinsicWeapon(20, verb);
    }
}
