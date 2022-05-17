/**
 * A special instance of a monster. A Boss Monster is a monster that guards one of four pillars.
 * A Boss Monster is more difficult to beat.
 */

public abstract class BossMonsters extends Monster {

    private Pillar myPillar;



    /**
     * Child constructor of Monster class. Calls super class constructor to init fields.
     * Inits Monster class exclusive fields.
     *
     * @param theName       value given by child class
     * @param theHealth     value given by child class
     * @param theSpeed      value given by child class
     * @param theMaxDamage  value given by child class
     * @param theMinDamage  value given by child class
     * @param theAccuracy   value that is static. Does not change.
     * @param theHealChance value given by child class
     * @param theMinHeal    value given by child class
     * @param theMaxHeal    value given by child class
     */
    protected BossMonsters(String theName, int theHealth, int theSpeed, int theMaxDamage, int theMinDamage, double theAccuracy, double theHealChance, int theMinHeal, int theMaxHeal, Pillar thePillar) {
        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal);
        this.myPillar = thePillar;
    }

    /**
     * Second phase of battling this monster. Stat changes, etc.
     */
    protected abstract void secondPhase();

    /**
     * Gives the player a Pillar when this bosss is defeated
     */
    protected abstract void defeated(Hero theHero);

}
