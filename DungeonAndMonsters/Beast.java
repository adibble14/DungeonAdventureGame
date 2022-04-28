
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Beast extends Monster{
	
	/**
	 * Initializes array of names. To be randomly chosen for Beast object.
	 */
	private final String[] MY_NAMES = {"Owl Bear", "Ravager", "Beholder", "plankTON", "Man Beast"};
	
	/**
	 * Beast object constructor. Calls on super class constructor to init fields.
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
	//TODO not used, delete or use
	protected Beast(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage, final double theAccuracy,
			final double theHealChance, final int theMinHeal, final int theMaxHeal) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal);
	}
	
	/**
	 * Constructor that has set values for Beast object. Calls on super constructor to init fields.
	 */
	protected Beast() {
		
		super("", 500, 1, 50, 30, .3, .2, 50, 100);
		
		this.setRandomName();
	}
	
	/**
	 * Sets random name for Beast object.
	 */
	final private void setRandomName() {
		
		this.setName( MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)] );
	}
	
	/**
	 * Feral Swipe special attack. This special always deals half the enemies' health value.
	 */
	@Override
	final protected void special(DungeonCharacter theChar) {
		//TODO delete this output once GUI is made, since this is VIEW
		System.out.println(this.getName() + " goes for a Feral Swipe!\n\n");
		
		int damage = theChar.getHealth() / 2;
		theChar.setHealth(damage);
		System.out.println(this.getName() + " halves " + theChar.getName() + "'s health!\n\n");	
	}

}
