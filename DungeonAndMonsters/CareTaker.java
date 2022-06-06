/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Caretaker class for the Originator, memento classes.
 * Follows the Memento Pattern
 */
public class CareTaker implements Serializable {

    private final List<Originator.Memento> mySavedStates;
    private final Originator myOriginator;

    protected CareTaker() {
        this.mySavedStates = new ArrayList<>();
        this.myOriginator = new Originator();
    }
    protected void saveState(Hero theHero) {
        myOriginator.set(theHero);
        mySavedStates.add(myOriginator.saveToMemento());
    }

    protected Hero revertState() {
        myOriginator.restoreFromMemento(mySavedStates.get(mySavedStates.size()-1));
        return myOriginator.getState();
    }
}
