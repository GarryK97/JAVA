package game.Item.portableitem;

import game.dinosaurs.Allosaur;

/**
 * Class that represents an Egg of Allosaur
 * @see Egg
 */
public class EggAllosaur extends Egg{

    /**
     * Constructor
     */
    public EggAllosaur() {
        super("Allosaur Egg", new Allosaur(), 50, 1000);
    }

    /**
     * Gets the price of this Egg
     * @return 1000
     */
    @Override
    public int getPrice() {
        return 1000;
    }

    /**
     * Checks whether this Egg is Carnivore Food or not
     * @return false
     */
    @Override
    public Boolean isCarnivoreFood(){
        return false;
    }
}
