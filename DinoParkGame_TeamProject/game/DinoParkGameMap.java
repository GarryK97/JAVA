package game;

import edu.monash.fit2099.demo.conwayslife.ConwayLocation;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

import java.util.List;
import java.util.Random;

/**
 * Main GameMap class for Jurassic Game
 * @see GameMap
 * @see DinoParkLocation
 */
public class DinoParkGameMap extends GameMap {

    private final int INITIAL_RAIN_TURN = 3;
    private float rain_turn = INITIAL_RAIN_TURN;
    private final int RAIN_PROBABILITY = 100;
    private Boolean isRaining = false;
    private float rainfall;
    private Random random = new Random();

    /**
     * Constructor
     * @param groundFactory Factory to create Ground objects
     * @param lines         List of Strings representing rows of the map
     */
    public DinoParkGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
        this.rainfall = (float) ((random.nextInt(50) + 10) * 0.01); // Initialize rainfall amount
    }

    /**
     * Creates a new Location.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return a new Location.
     */
    @Override
    protected Location makeNewLocation(int x, int y) {
        return new DinoParkLocation(this, x, y);
    }

    /**
     * Resets the rain_turn
     */
    private void reset_Rain_Turn(){
        rain_turn = INITIAL_RAIN_TURN;
    }

    /**
     * (Getter) Checks whether the map is raining or not.
     * @return isRaining
     */
    public Boolean get_IsRaining(){
        return isRaining;
    }

    /**
     * Getter for rainfall
     * @return rainfall
     */
    public float getRainfall(){
        return rainfall;
    }

    /**
     * A method to deal with the map when a turn passes
     */
    @Override
    public void tick() {
        super.tick();
        rain_turn -= 1;     // Every turn, rain turn --;

        // if rain turn reaches 0, checks condition and start to rain.
        if (rain_turn <= 0){
            if (random.nextInt(100) < RAIN_PROBABILITY){
                isRaining = true;
                rainfall = (float) ((random.nextInt(50) + 10) * 0.01);
                reset_Rain_Turn();
            }
        }
        else{
            isRaining = false;
        }

        if (isRaining){
            System.out.println("--The sky is dark and raining...--");
        }
    }
}
