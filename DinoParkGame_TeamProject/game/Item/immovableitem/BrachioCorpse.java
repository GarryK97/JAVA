package game.Item.immovableitem;

/**
 * Class that represents a Corpse of a Brachiosaur
 * @see Corpse
 */
public class BrachioCorpse extends Corpse {

    /**
     * Constructor
     */
    public BrachioCorpse() {
        super("Stegosaur Corpse", 'X', 40);
        this.food_level = 100;  // Brachio corpse has different food level
    }

}
