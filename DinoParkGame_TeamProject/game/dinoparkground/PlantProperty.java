package game.dinoparkground;

/**
 * Interface class that identifies the property of the plants
 * @see game.dinoparkground.Plant
 */
public interface PlantProperty {

    /**
     * Checks whether the plant can drop a fruit or not
     * @return true if the plant can drop a fruit, else false
     */
    public Boolean canDropFruit();

    /**
     * Checks whether the plant can be destroyed or not
     * @return true if the plant can be destroyed, else false
     */
    public Boolean isDestructible();

    /**
     * Checks whether a Stegosaur can eat this plant
     * @return true if a Stegosaur can eat this plant, else false
     */
    public Boolean canStegoEat();

    /**
     * Checks wether a Brachiosaur can eat this plant
     * @return true if a Brachiosaur can eat this plant, else false
     */
    public Boolean canBrachioEat();
}
