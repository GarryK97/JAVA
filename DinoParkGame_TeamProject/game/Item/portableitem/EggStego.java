package game.Item.portableitem;

import game.dinosaurs.Stegosaur;

/**
 * Class that represents an Egg of Stegosaur
 * @see Egg
 */
public class EggStego extends Egg {

    /**
     * Constructor
     */
    public EggStego() {
        super("Stegosaur Egg", new Stegosaur(), 30, 100);
    }

    /**
     * Gets the price of this Egg
     * @return 200
     */
    @Override
    public int getPrice() {
        return 200;
    }
}
