package game.Item.immovableitem;

public class PteroCorpse extends Corpse{
    /***
     * Constructor.
     */
    public PteroCorpse() {
        super("Pterodactyls Corpse", 'x', 15);
        this.food_level = 30;   // Ptero Corpse has different food level
    }
}
