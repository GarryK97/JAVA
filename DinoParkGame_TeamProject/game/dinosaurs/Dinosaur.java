package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Corpse;
import game.Item.portableitem.Egg;
import game.action.AttackAction;
import game.action.UnconsciousAction;
import game.dinosaurbehaviour.*;
import game.dinosaurbehaviour.non_flying.BreedingBehaviour;
import game.dinosaurbehaviour.non_flying.DrinkBehaviour;
import game.dinosaurbehaviour.non_flying.MoveToBreedBehaviour;
import game.dinosaurbehaviour.non_flying.MoveToWaterBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class that is used to create a Dinosaur type
 * @see DinosaurProperty
 * @see Actor
 * @see DinosaurCapability
 * @see WanderBehaviour
 * @see MoveToBreedBehaviour
 * @see BreedingBehaviour
 * @see AttackAction
 */
public abstract class Dinosaur extends Actor implements DinosaurProperty{

    /**
     * Age of the Dinosaur
     */
    protected int age = 100;

    /**
     * Stores the Maximum hungry turn left to die when Dinosaur becomes unconscious
     */
    protected int hungry_turn_reset = 0;

    /**
     * The hungry turn left to die when Dinosaur becomes unconscious
     */
    protected int hungry_turn_lefttodie = hungry_turn_reset;

    /**
     * Stores the Maximum thisry turn left to die when Dinosaur becomes unconscious
     */
    protected int thirsty_turn_reset = 15;

    /**
     * The thisry turn left to die when Dinosaur becomes unconscious
     */
    protected int thirsty_turn_lefttodie = thirsty_turn_reset;

    /**
     * The Adult age of the Dinosaur
     */
    protected int adult_age = 0;

    /**
     * The gender of the Dinosaur
     */
    protected Gender gender;

    /**
     * The Food level which the Dinosaur becomes hungry
     */
    protected int hungry_level = 1;

    /**
     * The Water level which the Dinosaur becomes thirsty
     */
    protected int water_level = 40;

    /**
     * The maximum water level
     */
    protected int max_water_level = 100;

    /**
     * The water level increase when Dinosaur drinks water
     */
    protected int drink_water_increase = 30;

    /**
     * The water level when the dinosaur becomes thirsty
     */
    protected int thirsty_level = 100;

    /**
     * Stores the age when the Dinosaur breeds.
     */
    protected int mated_age = 0;

    /**
     * Behaviours when Dinosaur beomces Thirsty
     */
    protected List<Behaviour> thirstybehaviour = new ArrayList<>();

    /**
     * Behaviours when Dinosaur becomes hungry
     */
    protected List<Behaviour> hungrybehaviour = new ArrayList<>();

    /**
     * Normal Behaviours
     */
    protected List<Behaviour> normalbehaviour = new ArrayList<>();

    /**
     * Behaviours when Dinosaur is well-fed (Not hungry)
     */
    protected List<Behaviour> wellfedbehaviour = new ArrayList<>();

    /**
     * Stores allowable actions that can be taken to the Dinosaur
     */
    protected Actions actions = new Actions();

    /**
     * Random Variable Generator
     */
    protected Random random = new Random();

    /**
     * Carnivores cannot attack the Dinosaur again for 'SAFE_TURN' turns
     */
    protected final int SAFE_TURN = 20;

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
    public Dinosaur(String name, char displayChar, int hitPoints, int start_hp, int hungry_level, int unconscious_turn, int adult_age, Gender gender) {
        super(name, displayChar, hitPoints);
        this.hungry_turn_reset = unconscious_turn;
        this.hungry_turn_lefttodie = hungry_turn_reset;
        this.adult_age = adult_age;
        this.hungry_level = hungry_level;

        this.hitPoints = start_hp;

        if (gender == null) {
            if (random.nextBoolean())
                this.gender = Gender.MALE;
            else
                this.gender = Gender.FEMALE;
        }
        else{
            this.gender = gender;
        }

        normalbehaviour.add(new WanderBehaviour());

        wellfedbehaviour.add(new BreedingBehaviour());
        wellfedbehaviour.add(new MoveToBreedBehaviour());

        thirstybehaviour.add(new DrinkBehaviour());
        thirstybehaviour.add(new MoveToWaterBehaviour());

        // All dinosaur starts from age 0
        this.addCapability(DinosaurCapability.BABY);
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
    public abstract Action playTurn(Actions actions, Action lastAction, GameMap map, Display display);

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
        return actions;
    }

    /**
     * Getter for gender
     * @return the Gender of the Dinosaur
     */
    public Gender getGender(){
        return gender;
    }

    public int getDrink_Water_Increase() {
        return drink_water_increase;
    }

    /**
     * Getter for SAFE_TURN
     * @return the SAFE_TURN of the dinosaur
     */
    public int getSafe_Turn(){
        return SAFE_TURN;
    }

    /**
     * Used to decrement the turn_lefttodie when the Dinosaur becomes unconscious
     */
    public void decrement_HungryDieTurn(){
        this.hungry_turn_lefttodie--;
    }

    /**
     * Resets the turn_lefttodie to its original value, which is stored in turn_reset
     */
    public void reset_ThirstyTurnLeftToDie(){
        this.thirsty_turn_lefttodie = thirsty_turn_reset;
    }

    /**
     * Used to decrement the turn_lefttodie when the Dinosaur becomes unconscious
     */
    public void decrement_ThirstyDieTurn(){
        this.thirsty_turn_lefttodie--;
    }

    /**
     * Resets the turn_lefttodie to its original value, which is stored in turn_reset
     */
    public void reset_HungryTurnLeftToDie(){
        this.hungry_turn_lefttodie = hungry_turn_reset;
    }

    public void reset_TurnLeftToDie(){
        reset_HungryTurnLeftToDie();
        reset_ThirstyTurnLeftToDie();
    }

    /**
     * Prints a description when the Dinosaur is hungry
     * @param map current GameMap
     */
    public void print_Hungry(GameMap map){
        if (hitPoints <= hungry_level){
            Location location = map.locationOf(this);
            String position_str = "(" + location.x() + ", " + location.y() + ")";
            System.out.println("The "+ this +" at " +position_str+" is getting hungry!");
        }
    }

    /**
     * Prints a description when the Dinosaur is thirsty
     * @param map current GameMap
     */
    public void print_Thirsty(GameMap map){
        if (water_level <= thirsty_level) {
            Location location = map.locationOf(this);
            String position_str = "(" + location.x() + ", " + location.y() + ")";
            System.out.println("The " + this + " at " + position_str + " is getting thirsty!");
        }
    }

    /**
     * Sets the Dinosaur to Pregnant and records the mated age
     */
    public void setPregnant(){
        this.addCapability(DinosaurCapability.PREGNANT);
        this.mated_age = age;
    }

    /**
     * Checks whether the Dinosaur can breed again.
     */
    protected void checkPregnant(){
        // After 20 turns passed from pregnancy,
        if (age >= mated_age + 20){
            this.removeCapability(DinosaurCapability.PREGNANT);
        }
    }

    /**
     * Checks whether the Dinosaur is baby or not
     * @param displaychar the display character to replace when the Dinosaur becomes adult.
     */
    protected void checkBaby(char displaychar){
        if (age >= adult_age){
            this.removeCapability(DinosaurCapability.BABY);
            this.displayChar = displaychar;
        }
    }

    /**
     * Increase the water level of the dinosaur
     * @param water_amount The increase amount of water level
     */
    public void drink(int water_amount){
        if (water_level + water_amount > max_water_level)
            this.water_level = max_water_level;
        else
            this.water_level += water_amount;
    }

    /**
     * Checks whether this dinosaur is dehydrated or not.
     * @return true if water level <= 0, else false.
     */
    public Boolean isDehydrated(){
        if (this.water_level <= 0){
            return true;
        }
        return false;
    }

    /**
     * Checks whether the dinosaur is unconscious or not and returns appropriate Action.
     * @return Appropriate UnconsciousAction. if not unconscious, return null.
     */
    protected Action checkUnconscious(){
        // if this Dinosaur is Unconscious
        if (hitPoints == 0){
            return new UnconsciousAction(hungry_turn_lefttodie, true);
        }
        else if (water_level <= 0){
            return new UnconsciousAction(thirsty_turn_lefttodie, false);
        }
        return null;
    }

    /**
     * The essential playTurn method for all dinosaurs
     * @param map current GameMap
     * @return Action that this dinosaur will perform
     */
    protected Action essential_PlayTurn(GameMap map){
        print_Hungry(map);
        print_Thirsty(map);

        if (checkUnconscious() != null){
            return checkUnconscious();
        }

        // Increment Age and decrement HP every turn
        age ++;
        hitPoints --;
        water_level --;
        reset_TurnLeftToDie(); // reset Turn Left to Die if the Dinosaur is not unconscious

        Action action;

        // If Dinosaur is thirsty
        if (water_level < thirsty_level){
            for (Behaviour behaviour : thirstybehaviour){
                action = behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
        }
        // If Dinosaur is hungry
        if (hitPoints < hungry_level){
            for (Behaviour behaviour : hungrybehaviour){
                action = behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
        }
        // If Dinosaur is well fed
        else{
            for (Behaviour behaviour : wellfedbehaviour){
                action = behaviour.getAction(this, map);
                if (action != null){
                    return action;
                }
            }
        }

        for (Behaviour behaviour : normalbehaviour){
            action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Gets the appropriate Egg object for the Dinosaur
     * @return Egg of the Dinosaur
     */
    public abstract Egg getNewEgg();

    /**
     * Gets the appropriate Corpse object for the Dinosaur
     * @return Corpse of the Dinosaur
     */
    public abstract Corpse getNewCorpse();

    /**
     * Checks whether the Dinosaur is a Carnivore or not
     * @return true if Carnivore, else false
     */
    public abstract Boolean isCarnivore();

    /**
     * Checks whether this Dinosaur can catch fish or not
     * @return by default, false. (Expect to change this by overriding)
     */
    public Boolean canCatchFish(){
        return false;
    }

    /**
     * Checks whether this Dinosaur can be attacked by Carnivores
     * @return by default, false.
     */
    public Boolean can_CarnivoreAttack(){
        return false;
    }

    /**
     * Checks whether this dinosaur can survive from a single attack
     * @return by default, true.
     */
    public Boolean canSurviveAttack(){
        return true;
    }
}
