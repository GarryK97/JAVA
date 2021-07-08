package game.Item.portableitem;

import edu.monash.fit2099.engine.Location;
import game.dinoparkground.Tree;
import game.dinosaurs.Pterodactyls;

/**
 * Class that represents an egg of Pterodactyls
 * @see Egg
 */
public class EggPtero extends Egg{

    private Boolean on_tree = true;

    /**
     * Constructor
     */
    public EggPtero() {
        super("Pterodactyls Egg", new Pterodactyls(null, 5), 15, 100);
    }

    /**
     * A method to deal with Egg when a turn passes when the Egg is on the ground.
     * @param location The location of this Egg
     */
    @Override
    public void tick(Location location){
        super.tick(location);

        // if egg is not on tree ground, set on_tree false
        if (!(location.getGround() instanceof Tree)){
            on_tree = false;
        }
        else
            on_tree = true;
    }

    @Override
    public int getPrice() {
        return 200;
    }

    @Override
    public Boolean isCarnivoreFood() {
        if (on_tree)
            return false;
        else
            return true;
    }
}
