
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 * 
 * @author Mario Flores Vences
 * @version 020421
 *
 */

public class Skeleton extends Monster {
	
	/**
	 * Stores boolean value for Skeleton object's special.
	 */
	private boolean specialActive;
	
	/**
	 * Initializes array of names. To be randomly chosen for Skeleton object.
	 */
	private final String[] MY_NAMES = {"Jack Skellington", "Skeletor", "Funny Bones", "Rattles", "Bone Thug"};
	
	/**
	 * Skeleton object constructor. Calls on super class constructor to init fields.
	 * Initializes Skeleton class final fields.
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
	//TODO Greyed out, use or remove!
	protected Skeleton(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
			final double theAccuracy, final double theHealChance, final int theMinHeal, final int theMaxHeal) {
		
		super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal);
		this.setSpecialActive(false);
		
	}
	
	/**
	 * Constructor that has set values for Skeleton object. Calls on super constructor
	 * to init fields.
	 */
	protected Skeleton() {
		
		super("", 100, 3, 50, 30, .8, .3, 40, 80);
		
		this.setRandomName();
		this.setSpecialActive(false);
	}
	
	/**
	 * Sets random name for Skeleton object.
	 */
	final private void setRandomName() {
		
		this.setName( MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)] );
	}
	
	/**
	 * Sets special active field 
	 * @param theBool receives a boolean from this class.
	 */
	final private void setSpecialActive(final boolean theBool) {
		
		this.specialActive = theBool;
	}
	
	/**
	 * Get method for special active field. Used to check if object can execute special.
	 * @return returns special active boolean
	 */
	final private boolean getSpecialActive() {
		
		return this.specialActive;
	}
	
	/**
	 * Overrides super class setHealth. Checks to see if special is activated. If so,
	 * when the health field is going to be set to zero, sets it to max health instead.
	 */
	@Override
	final protected void setHealth(final int theHealth) {
		
		if(theHealth == 0 && this.getSpecialActive()) {
			
			super.setHealth(this.getMaxHealth());
			this.setSpecialActive(false);
			return;
		}
		else {
			super.setHealth(theHealth);
		}
	}
	
	/**
	 * Calls on set special method to active special.
	 */
	@Override
	final protected void special(final DungeonCharacter theChar) {
		
		System.out.println(this.getName() + " prepares to reassemble itself after death! \n\n");
		this.setSpecialActive(true);
		
	}
}
