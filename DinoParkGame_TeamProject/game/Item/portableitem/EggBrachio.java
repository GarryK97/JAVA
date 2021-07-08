package game.Item.portableitem;

import game.dinosaurs.Brachiosaur;

/**
 * Class that represents an Egg of Brachiosaur
 * @see Egg
 */
public class EggBrachio extends Egg{

    /**
     * Constructor
     */
    public EggBrachio() {
        super("Brachiosaur Egg", new Brachiosaur(), 50, 1000);
    }

    /**
     * Gets the price of this Egg
     * @return 500
     */
    @Override
    public int getPrice() {
        return 500;
    }
}
