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
 * Test class for the pillar of Polymorphism
 */

public class PillarOfPolymorphismTest {

    @Test
    public  void testReversion() {
        PillarOfPolymorphism p = new PillarOfPolymorphism(PillarType.POLYMORPHISM);
        Hero h = new Archer("A");
        p.use(h);
        String before = h.getMyInGameSprite().toString();
        p.use(h);
        String after = h.getMyInGameSprite().toString();
        assertFalse(before.equalsIgnoreCase(after));
    }
}
