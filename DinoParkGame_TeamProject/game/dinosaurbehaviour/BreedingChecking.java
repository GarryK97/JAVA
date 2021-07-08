package game.dinosaurbehaviour;

import edu.monash.fit2099.engine.Actor;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.DinosaurCapability;

/**
 * A class that contains methods to check conditions to breed
 * @see game.dinosaurbehaviour.LocationMoveCompute
 * @see game.dinosaurs.Dinosaur
 */
public abstract class BreedingChecking extends LocationMoveCompute{

    /**
     * Checks whether two dinosaurs have opposite gender or not
     * @param actor A Dinosaur
     * @param another Another Dinosaur
     * @return true if they have opposite gender, else false
     */
    protected Boolean checkOppositeGender(Actor actor, Actor another){
        Dinosaur dino1 = (Dinosaur) actor;
        Dinosaur dino2 = (Dinosaur) another;
        return (dino1.getGender() != dino2.getGender());
    }

    /**
     * Checks whether two dinosaurs are same specie or not
     * @param actor A Dinosaur
     * @param another Another Dinosaur
     * @return true if they are same specie, else false
     */
    protected Boolean checkSameSpecies(Actor actor, Actor another){
        Dinosaur dino1 = (Dinosaur) actor;
        Dinosaur dino2 = (Dinosaur) another;
        return (dino1.toString().equals(dino2.toString()));
    }

    /**
     * Checks whether a dinosaur is recently bred or not
     * @param actor A Dinosaur
     * @return true if recently bred, else false
     */
    protected Boolean checkNotPregnant(Actor actor){
        return (!actor.hasCapability(DinosaurCapability.PREGNANT));
    }

    /**
     * Checks whether a dinosaur is adult or not
     * @param actor A Dinosaur
     * @return true if adult, else false
     */
    protected Boolean checkAdult(Actor actor){
        return (!actor.hasCapability(DinosaurCapability.BABY));
    }
}
