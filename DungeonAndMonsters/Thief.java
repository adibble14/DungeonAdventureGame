
/**
 * This class inherits from the Hero abstract class. Creates a Thief character. Overrides special attack method.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Thief extends Hero{
	
	/**
	 * Stores final special maximum damage.
	 */
	private final int MY_SPECIAL_MAX_DAMAGE;
	
	/**
	 * Stores final special accuracy
	 */
	private final double MY_SPECIAL_ACCURACY;
	
	/**
	 * Thief object constructor. Calls on super class constructor to init fields.
	 * Initializes Thief class exclusive fields.
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
	protected Thief(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage, final double theAccuracy,
			final double theBlockChance) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theBlockChance);
		this.MY_SPECIAL_MAX_DAMAGE = 20;
		this.MY_SPECIAL_ACCURACY = .6;
	}
	
	/**
	 * Constructor with set values. Calls on super constructor to init fields.
	 * Initializes Thief class fields.
	 * @param theName value given by user input
	 */
	protected Thief(final String theName) {
		
		super(theName, 95, 6, 20, 10, .9, .4);
		this.MY_SPECIAL_MAX_DAMAGE = 20;
		this.MY_SPECIAL_ACCURACY = .6;
	}
	
	/**
	 * Surprise Attack special attack. This special deals more damage than a regular attack. It also
	 * allows the Thief to follow up an attack that does even more damage.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		//TODO delete this output once GUI is made, since this is VIEW
		int damage = MY_RAND.nextInt(MY_SPECIAL_MAX_DAMAGE * 2) + this.getMaxDamage();
		double randAccuracy = MY_RAND.nextDouble();
		
		System.out.println(this.getName() + " goes for a surprise attack!\n\n");
		
		if(MY_SPECIAL_ACCURACY < randAccuracy) {
			System.out.println(this.getName() + " almost gets caught! Swiftly escapes at the last second. Dealt No damage....\n\n");
		}
		else if(MY_SPECIAL_ACCURACY > randAccuracy) {
			
			System.out.println(this.getName() + " lands the backstab! Dealt " + damage + " of damage.\n");
			
			int result = theChar.getHealth() - damage;
			if (result < 0) {
				result = 0;
			}
			theChar.setHealth(result);
		}
		if((MY_SPECIAL_ACCURACY / 2) > randAccuracy && theChar.isAlive()) {
			
			damage += this.getMaxDamage();
			System.out.println(this.getName() + " connects another attack! Dealt " + damage + " more of damage.\n\n");
			
			int secondResult = theChar.getHealth() - damage;
			if(secondResult < 0) {
				secondResult = 0;
			}
			theChar.setHealth(secondResult);
		}
	}

}
