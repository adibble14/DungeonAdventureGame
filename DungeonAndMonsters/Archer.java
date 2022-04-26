/*
 * TCSS 143 B - Winter 2021
 * Instructor: Tom Capaul
 * Programming Assignment 2: Heroes vs Monsters
 * Due Date: 02/04/2021
 */

/**
 * This class inherits from the Hero abstract class. Creates a Archer character. Overrides special attack method.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Archer extends Hero{
	
	/**
	 * Stores Archer object's special maximum damage
	 */
	private final int MY_SPECIAL_MAX_DAMAGE;
	
	/**
	 * Stores Arches object's special maximum turns
	 */
	private final int MY_SPECIAL_MAX_TURNS;
	
	/**
	 * Archer Class constructor. Calls on super class constructor to init fields.
	 * Initializes Archer class specific fields.
	 * 
	 * @param theName value given by user input
	 * @param theHealth value set in this class
	 * @param theSpeed value set in this class
	 * @param theMaxDamage value set in this class
	 * @param theMinDamage value set in this class
	 * @param theAccuracy value set in this class
	 * @param theBlockChance value set in this class
	 */
	protected Archer(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
			final double theAccuracy, final double theBlockChance) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theBlockChance);
		this.MY_SPECIAL_MAX_DAMAGE = 20;
		this.MY_SPECIAL_MAX_TURNS = 5;
	}
	
	/**
	 * Constructor with set values. Calls on super constructor to init fields
	 * Initializes class specific fields
	 * @param theName value given by user input
	 */
	protected Archer(final String theName) {
		
		super(theName, 100, 4, 30, 25, .7, .5);
		this.MY_SPECIAL_MAX_DAMAGE = 20;
		this.MY_SPECIAL_MAX_TURNS = 5;
	}
	
	/**
	 * Volley special attack. Generates a random number of attack turns, depending on special max turn field.
	 * Damage dealt is more than regular attack.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		
		System.out.println(this.getName() + " shoots a volley of arrows!\n");
		int count = MY_RAND.nextInt(MY_SPECIAL_MAX_TURNS);
		
		if(count == 0) {
			
			System.out.println("The attack misses!");
		}
		while(count > 0 && theChar.isAlive()) {
			
			int damage = MY_RAND.nextInt(MY_SPECIAL_MAX_DAMAGE) + this.getMaxDamage();
			int result = theChar.getHealth() - damage;
			if(result < 0) {
				result = 0;
			}
			System.out.println(theChar.getName() + " gets pierced by an arrow. Took " + damage + " of damage.\n\n");
			theChar.setHealth(result);
			count--;
		}
		
		
		
	}

}
