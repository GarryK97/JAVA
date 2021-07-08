package game.dinosaurbehaviour.non_flying;

import game.dinosaurbehaviour.Behaviour;

import java.util.Random;

/**
 * Abstract class to implement eating behaviour for herbivores
 * @see game.dinosaurbehaviour.Behaviour
 */
public abstract class HerbiEatBehaviour implements Behaviour {

    /**
     * Random variable Generator
     */
    protected Random random = new Random();

    /**
     * HP increase amount per food when a herbivore eats
     */
    protected int eat_hp_increase = 0;

    /**
     * Constructor
     * @param eat_hp_increase HP increase amount per food when a herbivore eats
     */
    public HerbiEatBehaviour(int eat_hp_increase) {
        this.eat_hp_increase = eat_hp_increase;
    }
}
