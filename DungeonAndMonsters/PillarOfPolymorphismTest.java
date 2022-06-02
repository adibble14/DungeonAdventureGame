import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the pillar of Polymorphism
 */

public class PillarOfPolymorphismTest {

    @Test
    public  void testReversion() {
        PillarOfPolymorphism p = new PillarOfPolymorphism(PillarType.POLYMORPHISM);
        Hero h = new Archer("A");
        p.use(h);
        System.out.println(h.getMyInGameSprite());
        p.use(h);
        System.out.println(h.getMyInGameSprite());
    }
}
