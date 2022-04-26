/*
 * TCSS 143 B - Winter 2021
 * Instructor: Tom Capaul
 * Programming Assignment 2: Heroes vs Monsters
 * Due Date: 02/04/2021
 */

/**
 * This class inherits from the Hero abstract class. Creates a Mage character. Overrides special attack method.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Mage extends Hero {
	
	/**
	 * Mage object constructor. Calls on super class constructor to init fields.
	 * 
	 * @param theName value set by user input
	 * @param theHealth value set in this class
	 * @param theSpeed value set in this class
	 * @param theMaxDamage value set in this class
	 * @param theMinDamage value set in this class
	 * @param theAccuracy value set in this class
	 * @param theBlockChance value set in this class
	 */
	protected Mage( final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage, final double theAccuracy,
			final double theBlockChance) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theBlockChance);
	}
	
	/**
	 * Constructor that has set values for object. Calls on super constructor to init fields.
	 * @param theName name value given by user input
	 */
	protected Mage(final String theName) {
		
		super(theName, 75, 4, 80, 50, .7, .3);
	}
	
	/**
	 * Life Steal super attack. This attack does not fail to land. It halves the enemies health value. It also
	 * adds that value to this object's health value.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		
		System.out.println(this.getName() + " activates life steal!\n\n");
		
		int damage = theChar.getHealth() / 2;
		int result =  this.getHealth() + damage;
		
		if( result > this.getMaxHealth() ) {
			
			this.setHealth(this.getMaxHealth());
		}
		else {
			this.setHealth(result);
		}
		
		theChar.setHealth(damage);
		
		System.out.println(this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health!!\n\n");
	}

}
