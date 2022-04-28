
/**
 * This class inherits from the Hero abstract class. Creates a Warrior character. Overrides special attack method.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Warrior extends Hero {
	
	/**
	 * Stores object's special accuracy value
	 */
	private final double MY_SPECIAL_ACCURACY;
	
	/**
	 * Stores object's special minimum damage value
	 */
	private final int MY_SPECIAL_MIN_DAMAGE;
	
	/**
	 * Stores object's special maximum damage value
	 */
	private final int MY_SPECIAL_MAX_DAMAGE;
	
	
	/**
	 * Warrior object constructor. Calls on super class constructor to init fields.
	 * Initializes Warrior object fields.
	 * 
	 * @param theName value given by user input
	 * @param theHealth value set in this class
	 * @param theSpeed value set in this class
	 * @param theMaxDamage value set in this class
	 * @param theMinDamage value set in this class
	 * @param theAccuracy value set in this class
	 * @param theBlockChance value set in this class
	 */
	//TODO Greyed out, use or remove!
	protected Warrior(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
			final double theAccuracy, final double theBlockChance) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theBlockChance);
		this.MY_SPECIAL_ACCURACY = .4;
		this.MY_SPECIAL_MIN_DAMAGE = 75;
		this.MY_SPECIAL_MAX_DAMAGE = 175;
	}
	
	/**
	 * Class constructor with set values. Calls on super constructor to init fields.
	 * Initializes class specific fields.
	 * 
	 * @param theName value given by user input.
	 */
	protected Warrior(final String theName) {
		
		super(theName,125, 3, 50, 30, .8, .6);
		this.MY_SPECIAL_ACCURACY = .4;
		this.MY_SPECIAL_MIN_DAMAGE = 75;
		this.MY_SPECIAL_MAX_DAMAGE = 175;
	}
	
	
	/**
	 * Crushing blow special attack. this attack does much more damage than a regular attack. 
	 * If this attack misses, it deals a guaranteed minimum value of damage.
	 */
	@Override
	final protected  void special(final DungeonCharacter theChar) {
		//TODO delete this output once GUI is made, since this is VIEW
		int damage = MY_RAND.nextInt(MY_SPECIAL_MAX_DAMAGE - MY_SPECIAL_MIN_DAMAGE) + MY_SPECIAL_MIN_DAMAGE;
		double randAccuracy = MY_RAND.nextDouble();
		
		System.out.println(this.getName() + " activates Crushing Blow!\n\n");
		
		if( MY_SPECIAL_ACCURACY < randAccuracy ) {
			
			damage = MY_SPECIAL_MIN_DAMAGE;
			System.out.println(this.getName() + " stumbles the attack...\n");
		}
		
		int result = theChar.getHealth() - damage;
		
		 if(result < 0) {
			 result = 0;
		 }
		 
		 theChar.setHealth(result);
		 
		 System.out.println(this.getName() + " deals a Crushing Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");
		
	}
}
