package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.DinoParkItem;
import game.action.AttackAction;
import game.action.FeedAction;
import game.dinosaurbehaviour.non_flying.AttackBehaviour;
import game.dinosaurbehaviour.non_flying.CarnivoreEatBehaviour;
import game.dinosaurbehaviour.non_flying.MoveToAttackBehaviour;

import java.util.ArrayList;

/**
 * Class that represents a Carnivore dinosaur
 * @see Dinosaur
 * @see AttackBehaviour
 * @see CarnivoreEatBehaviour
 * @see MoveToAttackBehaviour
 * @see DinoParkItem
 */
public abstract class Carnivore extends Dinosaur{

    /**
     * Stores the Dinosaurs that are recently attacked by this Carnivore
     */
    protected ArrayList<AttackedDinosaur> attacked_dinos = new ArrayList<>();

    /**
     * Constructor
     * @param name Name(Type) of the Dinosaur
     * @param displayChar The Character that is displayed in the game
     * @param hitPoints The Maximum HP (Food level)
     * @param start_hp Starting HP (Food level)
     * @param hungry_level The Food level which the Dinosaur becomes hungry
     * @param unconscious_turn Turn to die when Dinosaur becomes unconscious
     * @param adult_age The Adult age of the Dinosaur
     * @param gender Gender of the Dinosaur
     */
    public Carnivore(String name, char displayChar, int hitPoints, int start_hp, int hungry_level, int unconscious_turn, int adult_age, Gender gender) {
        super(name, displayChar, hitPoints, start_hp, hungry_level, unconscious_turn, adult_age, gender);

        hungrybehaviour.add(new AttackBehaviour());
        hungrybehaviour.add(new CarnivoreEatBehaviour());
        hungrybehaviour.add(new MoveToAttackBehaviour());
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Dinosaur.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the Actions object which is the collection of actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        actions.clear();
        actions.add(new AttackAction(this, otherActor.getWeapon().damage()));

        // If the other actor has Carnivore foods, adds feed action of them.
        for (Item item : otherActor.getInventory()){
            if (((DinoParkItem)item).isCarnivoreFood()){
                actions.add(new FeedAction(this, (DinoParkItem)item));
            }
        }
        return actions;
    }

    /**
     * Checks whether a Dinosaur is attacked by this Carnivore recently.
     * @param dinosaur the Dinosaur that is being attacked
     * @return true if the dinosaur is recently attacked, else false
     */
    public Boolean attacked_Before(Dinosaur dinosaur){
        for (AttackedDinosaur victim : attacked_dinos){
            if (victim.getDinosaur() == dinosaur){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Dinosaur in the attacked_dinos.
     * @param dinosaur the Dinosaur that is attacked
     */
    public void add_Victim(Dinosaur dinosaur){
        attacked_dinos.add(new AttackedDinosaur(dinosaur));
    }

    /**
     * A method to check and remove victim in attacked_dino list
     */
    public void tick_AttackedDino() {
        ArrayList<AttackedDinosaur> victim_toremove = new ArrayList<>();

        for (AttackedDinosaur victim : attacked_dinos) {
            victim.decrement_SafeTurn(); // Decrements the Safe Turn

            if (victim.getSafeTurn() < 0){
                victim_toremove.add(victim);
            }
        }

        for (AttackedDinosaur victim : victim_toremove){
            attacked_dinos.remove(victim);
        }
    }

    /**
     * Checks whether this Dinosaur is Carnivore or not
     * @return true
     */
    @Override
    public Boolean isCarnivore() {
        return true;
    }

    /**
     * Returns the IntrinsicWeapon of this Carnivore
     * @return the IntrinsicWeapon of this Carnivore
     */
    @Override
    protected abstract IntrinsicWeapon getIntrinsicWeapon();

    /**
     * Class to store the Attacked Dinosaurs
     */
    private class AttackedDinosaur {
        /**
         * The Attacked Dinosaur
         */
        private Dinosaur dinosaur;

        /**
         * The safe turn of the dinosaur
         */
        private int safe_turn;

        /**
         * Constructor
         * @param dinosaur The Attacked Dinosaur
         */
        public AttackedDinosaur(Dinosaur dinosaur) {
            this.dinosaur = dinosaur;
            this.safe_turn = dinosaur.getSafe_Turn();
        }

        /**
         * Getter for Attacked Dinosaur
         * @return the Dinosaur
         */
        public Dinosaur getDinosaur(){
            return dinosaur;
        }

        /**
         * Getter for the safe turn of the Attacked Dinosaur
         * @return the safe turn
         */
        public int getSafeTurn() {
            return safe_turn;
        }

        /**
         * A method to decrement Safe Turn
         */
        public void decrement_SafeTurn(){
            safe_turn--;
        }
    }
}
