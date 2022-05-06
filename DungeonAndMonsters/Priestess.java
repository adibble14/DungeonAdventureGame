import javax.swing.*;

public class Priestess extends Hero{

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/priestess.jpeg");
    protected Priestess(final String theName) {

        super(theName, 75, 5, 45, 25, .7, .3);
    }


    /**
     * Special: Healing. For every point of damage dealt in next attack, priestess will heal 2/3 the amount.
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {
        double randAccuracy = MY_RAND.nextDouble();
        int specialMaxDamage = 45;
        int damage = MY_RAND.nextInt(specialMaxDamage) + this.getMaxDamage();

        System.out.println(this.getName() + " goes for a special healing attack!\n\n");

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {
            System.out.println("The healing attack misses!");
        } else if (specialAccuracy > randAccuracy) {

            int healing = (int) Math.ceil(damage*.66);

            System.out.println(this.getName() + " lands the attack! Dealt " + damage + " of damage and healed " + healing+"points of health\n");

            this.setHealth(this.getHealth() + healing);
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
        }


    }
    public static ImageIcon getImage(){
        return image;
    }
}
