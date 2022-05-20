public class BossInheritance extends BossMonsters{
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
     * @param thePillar
     */
    protected BossInheritance(String theName, int theHealth, int theSpeed, int theMaxDamage, int theMinDamage, double theAccuracy, double theHealChance, int theMinHeal, int theMaxHeal, Pillar thePillar) {
        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal, thePillar);
    }

    /**
     * Activated when boss reaches a certain health value
     * Increases min damage and speed
     */
    @Override
    protected void secondPhase() {
        this.setMinDamage(this.getMaxDamage() - 15);
        this.setSpeed(this.getSpeed() + 5);
    }


    /**
     * Should give the player a Pillar of Inheritance
     * @param theHero
     */
    @Override
    protected void defeated(Hero theHero) {

    }

    /**
     * Special Move
     *
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {

    }

    @Override
    public String getSpecialInfo(){  //TODO
        return "";
    }
}
