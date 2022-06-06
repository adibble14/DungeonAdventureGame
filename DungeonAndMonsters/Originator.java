/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import java.io.Serializable;

/**
 * Originator class used in Memento Pattern. Use is for
 * saving player Hero object when using Pillar of Polymorphism.
 * This pillar lets the player change into a monster. The Memento Pattern
 * will be used to revert to the players original character
 */

public class Originator implements Serializable {

    /**
     * hero instance
     */
    private Hero myHero;

    /**
     * Creates a deep clone of a Hero object
     * @param theHero sets the hero
     */
    protected final void set(Hero theHero) {

        String type = theHero.getClass().getSimpleName();
        switch(type) {
            case"Archer": this.myHero = new Archer(theHero.getName());
            case"Warrior": this.myHero = new Warrior(theHero.getName());
            case"Mage": this.myHero = new Mage(theHero.getName());
            case"Priestess": this.myHero = new Priestess(theHero.getName());
            case"Thief": this.myHero = new Thief(theHero.getName());
        }
        this.myHero.setHealth(theHero.getHealth());
    }

    /**
     * @return a new memento object
     */
    protected Memento saveToMemento() {
        return new Memento(myHero);
    }

    /**
     * @param theMemento restoring hero to saved state
     */
    void restoreFromMemento(Memento theMemento) {
        myHero = theMemento.getSavedState();
    }

    /**
     * getting the current state of the hero
     * @return hero
     */
    Hero getState() {
        return this.myHero;
    }


    /**
     * Memento pattern, used to save the player's original hero state when
     * using pillar of polymorphism
     */
 class Memento implements Serializable {

        private final Hero myHero;

        private Memento(Hero theHero) {
            myHero = theHero;
        }
        private Hero getSavedState() {
            return myHero;
        }
    }
}



