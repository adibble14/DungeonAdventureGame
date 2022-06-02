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
        myOriginator.restoreFromMemento(mySavedStates.get(0));
        return myOriginator.getState();
    }
}
