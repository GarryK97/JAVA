package game.dinosaurs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.dinoparkground.Tree;
import game.dinosaurbehaviour.flying.*;
import game.dinosaurbehaviour.non_flying.MoveToWaterBehaviour;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Gender;


/**
 * Abstract class to represent a flying dinosaur
 * @see Dinosaur
 * @see FlyDinoBreedingBehaviour
 * @see FlyDinoEatBehaviour
 * @see FlyDinoMoveToBreedBehaviour
 * @see FlyDinoMoveToFoodBehaviour
 * @see FlyDinoMoveToTreeBehaviour
 */
public abstract class FlyingDinosaur extends Dinosaur{

    /**
     * checks whether this dinosaur is flying or not
     */
    protected Boolean flying = false;

    /**
     * checks whether this dinosaur is on tree or not
     */
    protected Boolean on_tree = false;

    /**
     * The amount that the dinosaur can eat in a turn
     */
    protected final int EAT_AMOUNT = 10;

    /**
     * The initial value of fly_turn
     */
    protected final int INITIAL_FLY_TURN;

    /**
     * Turn left that this dinosaur can continue to fly
     */
    protected int fly_turn;

    /**
     * The initial value of rest_turn
     */
    protected final int INITINAL_REST_TURN = 5;

    /**
     * Turn left until this dinosaur can fly again when it reached on top of the tree.
     */
    protected int rest_turn = INITINAL_REST_TURN;

    /**
     * Constructor
     *
     * @param name             Name(Type) of the Dinosaur
     * @param displayChar      The Character that is displayed in the game
     * @param hitPoints        The Maximum HP (Food level)
     * @param start_hp         Starting HP (Food level)
     * @param hungry_level     The Food level which the Dinosaur becomes hungry
     * @param unconscious_turn Turn to die when Dinosaur becomes unconscious
     * @param adult_age        The Adult age of the Dinosaur
     * @param fly_turn         The Fly Turn of the Dinosaur
     * @param gender           Gender of the Dinosaur
     */
    public FlyingDinosaur(String name, char displayChar, int hitPoints, int start_hp, int hungry_level, int unconscious_turn, int adult_age, int fly_turn, Gender gender, int rest_turn) {
        super(name, displayChar, hitPoints, start_hp, hungry_level, unconscious_turn, adult_age, gender);
        this.INITIAL_FLY_TURN = fly_turn;
        this.fly_turn = INITIAL_FLY_TURN;
        this.rest_turn = rest_turn;

        thirstybehaviour.remove(0);     // Removes Drink Behaviour of non-flying dinosaurs.
        thirstybehaviour.add(0, new CatchFishBehaviour());  // Replace it

        hungrybehaviour.add(new FlyDinoEatBehaviour());
        hungrybehaviour.add(new CatchFishBehaviour());
        hungrybehaviour.add(new FlyDinoMoveToFoodBehaviour());
        hungrybehaviour.add(new MoveToWaterBehaviour());

        wellfedbehaviour.clear();   // Clears the original wellfedbehaviour of non-flying dinosaurs
        wellfedbehaviour.add(new FlyDinoBreedingBehaviour());
        wellfedbehaviour.add(new FlyDinoMoveToBreedBehaviour());
    }

    /**
     * Resets the fly_turn
     */
    protected void reset_FlyTurn(){
        this.fly_turn = INITIAL_FLY_TURN;
    }

    /**
     * Resets the rest_turn
     */
    protected void reset_RestTurn(){ this.rest_turn = INITINAL_REST_TURN;}

    /**
     * Getter for flying
     * @return flying
     */
    public Boolean getFlying(){
        return flying;
    }

    /**
     * Getter for on_tree
     * @return on_tree
     */
    public Boolean getOn_Tree() {
        return on_tree;
    }

    /**
     * Method to represent flying dinosaur landing.
     * @param map the GameMap containing the actor
     */
    public void land(GameMap map){
        this.flying = false;
        reset_FlyTurn();
        reset_RestTurn();
        System.out.println(this + " is landed.");
    }

    /**
     * Method to represent flying dinosaur flying.
     */
    public void fly(){
        this.flying = true;
        reset_FlyTurn();
        reset_RestTurn();
        System.out.println(this + " flies again.");
    }

    /**
     * ticks the fly_turn and makes this dinosaur lands.
     * @param map the GameMap containing the actor
     */
    protected void tick_FlyTurn(GameMap map){
        if (flying){
            if (fly_turn <= 0){
                if (map.locationOf(this).getGround().canActorEnter(this)){
                    land(map);
                }
            }
            fly_turn -= 1;
        }
    }

    /**
     * ticks the rest_turn and makes this dinosaur fly again.
     */
    protected void tick_RestTurn(){
        if (!flying && on_tree){
            if (rest_turn <= 0 ){
                fly();
            }
            else {
                rest_turn -= 1;
            }
        }
    }

    /**
     * checks whether the dinosaur is on top of the tree or not.
     * @param map the GameMap containing the actor
     * @return true, if dinosaur is on tree. else, false.
     */
    public Boolean check_OnTree(GameMap map){
        if (!flying) {
            if (map.locationOf(this).getGround() instanceof Tree) {
                on_tree = true;
            } else
                on_tree = false;
        }
        else{
            on_tree = false;
        }
        return on_tree;
    }

    /**
     * Gets the appropriate action to make this dinosaur fly again.
     * @param map the GameMap containing the actor
     * @return MoveAction to tree if this dinosaur is not on tree, DoNothingAction() if this dinosaur is resting.
     */
    public Action getActionToFly(GameMap map){
        if (!flying && !on_tree){
            Action action = new FlyDinoMoveToTreeBehaviour().getAction(this, map);
            if (action != null)
                return action;
        }
        if (!flying && on_tree && rest_turn >= 0){
            System.out.println(this + " is resting on the tree. Left Turn: " + (rest_turn+1));
            return new DoNothingAction();   // Rests on the tree
        }
        return null;
    }

    /**
     * Checks whether this dinosaur can catch a fish or not
     * @return true
     */
    @Override
    public Boolean canCatchFish() {
        return true;
    }

    /**
     * Getter for EAT_AMOUNT
     * @return EAT_AMOUNT
     */
    public int getEat_Amount() {
        return EAT_AMOUNT;
    }

    /**
     * Checks whether this dinosaur can be attacked by Carnivores
     * @return false if this dinosaur is flying or on top of a tree. else, false
     */
    @Override
    public Boolean can_CarnivoreAttack() {
        if (flying || on_tree)
            return false;
        else
            return true;
    }

    /**
     * Checks whether this dinosaur can survive from a single attack
     * @return false
     */
    @Override
    public Boolean canSurviveAttack() {
        return false;
    }
}
