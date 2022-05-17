public class BossPolymorphism extends BossMonsters{
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
     * @param thePillar     Pillar object
     */
    protected BossPolymorphism(String theName, int theHealth, int theSpeed, int theMaxDamage, int theMinDamage, double theAccuracy, double theHealChance, int theMinHeal, int theMaxHeal, Pillar thePillar) {
        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal, thePillar);
    }

    /**
     * Activated when Boss gets to a certain health amount
     * Modifies stats to become more powerful
     */
    @Override
    protected void secondPhase() {
        this.setHealth(this.getHealth() + (this.getHealth()/2));
        this.setMaxDamage(this.getMaxDamage() + 25); //TODO: Magic number
        this.setAccuracy(this.getMyAccuracy() - .05);
    }

    /**
     * Brings down theChar's health to 1
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {
        theChar.setHealth(1);
    }

    /**
     * Adds Pillar to player inventory
     * @param theHero
     */
    @Override
    protected void defeated(Hero theHero) {
        //theHero.getMyInventory().addPillar(new Pillar());
    }
}
