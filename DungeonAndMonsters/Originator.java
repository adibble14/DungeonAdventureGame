/**
 * Originator class used in Memento Pattern. Use is for
 * saving player Hero object when using Pillar of Polymorphism.
 * This pillar lets the player change into a monster. The Memento Pattern
 * will be used to revert to the players original character
 */

public class Originator {

    private Hero myHero;

    protected final void set(Hero theHero) {
        this.myHero = theHero;
    }

    protected Memento saveToMemento() {
        return new Memento(myHero);
    }

    public void restoreFromMemento(Memento theMemento) {
        myHero = theMemento.getSavedState();
    }
    public Hero getState() {
        return this.myHero;
    }


    /**
     * Memento pattern, used to save the player's original hero state when
     * using pillar of polymorphism
     */
 class Memento {

        private final Hero myHero;

        private Memento(Hero theHero) {
            myHero = theHero;
        }
        private Hero getSavedState() {
            return myHero;
        }
    }
}



