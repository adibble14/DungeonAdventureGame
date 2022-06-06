/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for CareTaker/Originator pattern
 */
public class CareTakerTest {

    @Test
    public void testCareTaker() {
        CareTaker taker = new CareTaker();
        Hero h = new Archer("Alan");
        taker.saveState(h);
        h.setName("Ebony");
        Hero copy = taker.revertState();
        System.out.println(h.getName());
        System.out.println(copy.getName());
        assertFalse(h.getName() == copy.getName());
    }
}
