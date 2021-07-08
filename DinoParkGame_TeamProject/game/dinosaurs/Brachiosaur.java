package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.BrachioCorpse;
import game.Item.immovableitem.Corpse;
import game.Item.portableitem.Egg;
import game.Item.portableitem.EggBrachio;
import game.action.UnconsciousAction;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Dirt;
import game.dinoparkground.Plant;
import game.dinosaurbehaviour.non_flying.BrachioEatBehaviour;
import game.dinosaurs.Gender;
import game.dinosaurs.Herbivore;

/**
 * Class that represents a Brachiosaur
 * @see Herbivore
 * @see BrachioEatBehaviour
 * @see UnconsciousAction
 * @see DoNothingAction
 * @see BrachioCorpse
 * @see EggBrachio
 */
public class Brachiosaur extends Herbivore{

    /**
     * The HP increase amount when Brachiosaur eats a fruit
     */
    protected final int FRUIT_HP_INCREASE = 5;

    /**
     * Constructor with Gender specified
     * @param gender gender of this Allosaur
     */
    public Brachiosaur(Gender gender) {
        super("Brachiosaur", 'b', 160, 100,140,15, 50, gender);

        hungrybehaviour.add(new BrachioEatBehaviour(FRUIT_HP_INCREASE));
        this.max_water_level = 200;
        this.drink_water_increase = 80;
    }

    /**
     * Constructor without Gender
     *
     */
    public Brachiosaur() {
        super("Brachiosaur", 'b', 160, 100,140,15, 50, null);

        hungrybehaviour.add(new BrachioEatBehaviour(FRUIT_HP_INCREASE));
        this.max_water_level = 200;
        this.drink_water_increase = 80;
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

        checkBaby('B');
        checkPregnant();
        checkBushDestroy(map);

        return essential_PlayTurn(map);
    }

    /**
     * Gets the appropriate Egg object
     * @return Egg of Brachiosaur
     */
    @Override
    public Egg getNewEgg() {
        return (new EggBrachio());
    }

    /**
     * Gets the appropriate Corpse object
     * @return Corpse of Brachiosaur
     */
    @Override
    public Corpse getNewCorpse() {
        return (new BrachioCorpse());
    }

    /**
     * A method to implement Brachiosaur destroying destructible plants
     * @param map current GameMap
     */
    private void checkBushDestroy(GameMap map){
        DinoParkGround ground = (DinoParkGround) map.locationOf(this).getGround();
        if (ground.isPlant()){
            if (((Plant)ground).isDestructible()){
                if (random.nextInt(100) < 50){
                    map.locationOf(this).setGround(new Dirt());
                }
            }
        }
    }
}
