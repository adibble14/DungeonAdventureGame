
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Ogre extends Monster {
	
	/**
	 * Stores final value for ogre object's special accuracy.
	 */
	private final double MY_SPECIAL_ACCURACY;
	/**
	 * Stores final value for ogre object's special max damage.
	 */
	
	private final int MY_SPECIAL_MAX_DAMAGE;
	
	/**
	 * Stores final value for ogre object's min special damage.
	 */
	private final int MY_SPECIAL_MIN_DAMAGE;
	
	/**
	 * Initializes array of names. To be randomly chosen for ogre object.
	 */
	private final String[] MY_NAMES = {"Green Ogre", "Huge Ogre", "Passive Ogre", "Mysterious Ogre", "Strong Ogre"};
	
	/**
	 * Ogre object constructor. Calls on super class constructor to init fields.
	 * Initializes Ogre class final fields.
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
	protected Ogre(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage, final double theAccuracy,
			final double theHealChance, final int theMinHeal, final int theMaxHeal) {
	
		super(theName, theMaxHeal, theMaxHeal, theMaxHeal, theMaxHeal, theHealChance, theHealChance, theMaxHeal, theMaxHeal);
		this.MY_SPECIAL_ACCURACY = .5;
		this.MY_SPECIAL_MAX_DAMAGE = 100;
		this.MY_SPECIAL_MIN_DAMAGE = 60;
	}
	
	/**
	 * Constructor that has set values for Ogre object. Calls on super constructor to init fields.
	 */
	protected Ogre() {
		
		super("", 200, 2, 60, 30, .6, .1, 30, 60);
		this.setRandomName();
		this.MY_SPECIAL_ACCURACY = .5;
		this.MY_SPECIAL_MAX_DAMAGE = 100;
		this.MY_SPECIAL_MIN_DAMAGE = 60;
	}
	
	/**
	 * Sets random name for Ogre class
	 */
	final private void setRandomName() {
		
		this.setName( MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)] );
	}
	
	/**
	 * Devastating Blow special method for Ogre object. This special has a guaranteed min value
	 * damage.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		
		int damage = MY_RAND.nextInt(MY_SPECIAL_MAX_DAMAGE) + MY_SPECIAL_MIN_DAMAGE;
		double randAccuracy = MY_RAND.nextDouble();
		
		System.out.println(this.getName() + " throws a Devastating Blow!\n\n");
		
		if( MY_SPECIAL_ACCURACY < randAccuracy) {
			
			damage = MY_SPECIAL_MIN_DAMAGE;
			System.out.println(theChar.getName() + " dodges the attack! Splash damage taken.\n");
		}
		
		int result = theChar.getHealth() - damage;
		
		if(result < 0) {
			
			result = 0;
		}
		theChar.setHealth(result);
		
		System.out.println(this.getName() + " deals a Devastating Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");
	}

	
}
