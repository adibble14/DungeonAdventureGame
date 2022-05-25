import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Caretaker class for the Originator, memento classes.
 * Follows the Memento Pattern
 */
public class CareTaker implements Serializable {

    private List<Originator.Memento> mySavedStates;
    private Originator myOriginator;

    protected CareTaker(Hero theHero) {
        this.mySavedStates = new ArrayList<>();
        Originator myOriginator = new Originator();
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
