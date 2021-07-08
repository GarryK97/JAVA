package game.dinosaurs;

/**
 * Interface class to set the properties of dinosaurs
 * @see game.dinosaurs
 */
public interface DinosaurProperty {

    /**
     * Checks whether the dinosaur is a Carnivore or not
     * @return true if Carnivore, else false.
     */
    public Boolean isCarnivore();

    /**
     * Checks whether this dinosaur can catch fish from lakes or not
     * @return true if can catch, else false
     */
    public Boolean canCatchFish();

    /**
     * Checks whether this dinosaur can survive from a single attack
     * @return true if it can survive, else false.
     */
    public Boolean canSurviveAttack();
}
