/*
 * TCSS 143 B - Winter 2021
 * Instructor: Tom Capaul
 * Programming Assignment 2: Heroes vs Monsters
 * Due Date: 02/04/2021
 */

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Gremlin extends Monster {
	
	/**
	 * Stores final value for Gremlin object's special max damage.
	 */
	private final int MY_SPECIAL_MAX_DAMAGE;
	
	/**
	 * Stores final value for Gremlin object's special max turns.
	 */
	private final int MY_SPECIAL_MAX_TURNS;
	
	/**
	 * Initializes array of names. To be randomly chosen for Gremlin object.
	 */
	private final String[] MY_NAMES = {"Mogwai", "Gizmo", "Stripe", "Greta", "Daffy"};
	
	
	/**
	 * Gremlin Object constructor. Calls on super class constructor to init fields.
	 * Initializes Gremlin class final fields
	 * 
	 * @param theName value set in this class
	 * @param theHealth value set in this class
	 * @param theSpeed value set in this class
	 * @param theMaxDamage value set in this class
	 * @param theMinDamage value set in this class
	 * @param theAccuracy value set in this class
	 * @param theHealChance value set in this class
	 * @param theMinHeal value set in this class
	 * @param theMaxHeal value set in this class
	 */
	protected Gremlin(final String theName, final int theHealth,final int theSpeed, final int theMaxDamage, final int theMinDamage,
			final double theAccuracy, final double theHealChance, final int theMinHeal, final int theMaxHeal) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal);
		this.MY_SPECIAL_MAX_DAMAGE = 30;
		this.MY_SPECIAL_MAX_TURNS = 3;
	}
	
	/**
	 * Constructor that has set values for Gremlin object. Calls on super constructor to init fields.
	 */
	protected Gremlin() {
		
		super("", 70, 5, 30, 15, .8, .4, 20, 40);
		
		this.setRandomName();
		this.MY_SPECIAL_MAX_DAMAGE = 30;
		this.MY_SPECIAL_MAX_TURNS = 3;
	}
	
	/**
	 * Set random name for Gremlin object.
	 */
	final private void setRandomName() {
		
		this.setName( MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)] );
	}
	
	/**
	 * Gremlin Frenzy special attack. This method generates a random value influenced by
	 * special turn field. Gremlin gets to attack that many times. Damage is based on special damage
	 * plus a random value.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		
		System.out.println(this.getName() + " summons a Gremlin Frenzy!");
		int count = MY_RAND.nextInt(MY_SPECIAL_MAX_TURNS);
		
		if(count == 0) {
			
			System.out.println("No Gremlins showed up!\n\n");
		}
		while(count > 0 && theChar.isAlive()) {
			
			int damage = MY_RAND.nextInt(MY_SPECIAL_MAX_DAMAGE / 2) + MY_SPECIAL_MAX_DAMAGE;
			int result = theChar.getHealth() - damage;
			if(result < 0) {
				result = 0;
			}
			System.out.println(theChar.getName() + " gets hit by a frenzied Gremlin! Took " + damage + " of damage.\n\n");
			theChar.setHealth(result);
			count--;
		}
	}

}
