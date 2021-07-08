package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import game.dinoparkground.*;
import game.dinosaurs.Dinosaur;

import java.util.Random;

/**
 * Main Location class for Jurassic Game
 * @see Location
 * @see DinoParkGround
 * @see NumberRange
 */
public class DinoParkLocation  extends Location {

    /**
     * Random Variable Generator
     */
    private Random random = new Random();

    /**
     * Constructor.
     * <p>
     * Locations know which map they are part of, and where.
     *
     * @param map the map that contains this location
     * @param x   x coordinate of this location within the map
     * @param y   y coordinate of this location within the map
     */
    public DinoParkLocation(GameMap map, int x, int y) {
        super(map, x, y);
    }

    /**
     * A method to deal with Location when a turn passes
     */
    @Override
    public void tick() {
        super.tick();
        checkBushGrow();
        checkRaining();
    }

    /**
     * Checks whether the location ground is water-based and apply the changes.
     */
    private void checkRaining(){

        DinoParkGameMap gameMap = (DinoParkGameMap) map();
        DinoParkGround ground = (DinoParkGround) getGround();

        // if raining
        if (gameMap.get_IsRaining()){
            if (ground.isWater()){
                int add_amount = (int) (20 * gameMap.getRainfall());
                ((Lake)ground).addCapacity(add_amount);
            }
            // if there is unconscious dehydrated dinosaur at this location,
            if (getActor() != null && getActor() instanceof Dinosaur && ((Dinosaur)getActor()).isDehydrated()){
                ((Dinosaur)getActor()).drink(10);   // Increase 10 water level
            }
        }
    }


    /**
     * Sets this location's ground to Bush if conditions are met.
     */
    private void checkBushGrow() {
        int prob_nearbush = 5; // Probability of Bush Grow when this Location is near bush
        int prob_default = 5; // Probability of Bush Grow when this Location in default

        int tree_squares = 2; // Nearby squares to check for isNearTree()
        int bush_squares = 1; // Nearby squares to check for isNearBush()

        int prob = random.nextInt(1000); // Probability will be N / 1000

        if (isGrowable()) {
            if (!isNearTree(tree_squares)) {
                if (isNearBush(bush_squares)) {
                    if (prob < prob_nearbush) {
                        setGround(new Bush());
                    }
                } else {
                    if (prob < prob_default) {
                        setGround(new Bush());
                    }
                }
            }
        }
    }

    /**
     * Checks whether this Location is Near Tree or not
     * @param num_squares Nearby 'N' squares to check
     * @return true if location is next to Tree, else false
     */
    private Boolean isNearTree(int num_squares){
        NumberRange xs = getNearbyXSquares(num_squares);
        NumberRange ys = getNearbyYSquares(num_squares);

        for (int x : xs) {
            for (int y : ys){
                if (map().getXRange().contains(x) && map().getYRange().contains(y))
                    if (map().at(x,y).getGround() instanceof Tree) {
                        return true;
                    }
            }
        }
        return false;
    }

    /**
     * Checks whether this Location is Near Bush or not
     * @param num_squares Nearby 'N' squares to check
     * @return true if location is next to Bush, else false
     */
    private Boolean isNearBush(int num_squares) {
        NumberRange xs = getNearbyXSquares(num_squares);
        NumberRange ys = getNearbyYSquares(num_squares);

        for (int x : xs) {
            for (int y : ys) {
                if (map().getXRange().contains(x) && map().getYRange().contains(y))
                    if (map().at(x, y).getGround() instanceof Bush) {
                        if(!((Bush) map().at(x,y).getGround()).is_NewPlant())
                            return true;
                    }
            }
        }
        return false;
    }

    /**
     * Checks whether a plant can grow in this location or not
     * @return true if a plant can grow, else false
     */
    private Boolean isGrowable(){
        DinoParkGround ground = (DinoParkGround) getGround();
        return (ground.isGrowable());
    }

    /**
     * Gets all X positions which are nearby.
     * @param num_squares the width
     * @return NumberRange that contains all nearby X positions
     */
    private NumberRange getNearbyXSquares(int num_squares){
        NumberRange xs = new NumberRange(x() - num_squares, 2*num_squares +1);
        return xs;
    }

    /**
     * Gets all Y positions which are nearby.
     * @param num_squares the height
     * @return NumberRange that contains all nearby Y positions
     */
    private NumberRange getNearbyYSquares(int num_squares){
        NumberRange ys = new NumberRange(y() - num_squares, 2*num_squares +1);
        return ys;
    }
}
