

import java.util.Random;
import java.util.Scanner;

/**
 * This class initializes the game. It takes input from the user. Creates hero and monster objects.
 *  Has the game loop logic.
 * 
 * @author Mario FLores Vences
 * @version 031221
 *
 */
public class Battle {


	Hero myHero;
	Monster myMonster;

	protected Battle(final Hero theHero) {
		this.myHero = theHero;
		this.myMonster = createMonster();
	}

	protected Battle(final Hero theHero, final Monster theMonster) {
		this.myHero = theHero;
		this.myMonster = theMonster;
	}

	/**
	 * the DungeonCharacter with the highest speed stat goes first
	 */
	public void attackPhase() {
		if(this.myHero.getSpeed() > this.myMonster.getSpeed()) {
			this.myHero.attack(this.myMonster);
			this.myMonster.attack(this.myHero);
		} else {
			this.myMonster.attack(this.myHero);
			this.myHero.attack(this.myMonster);
		}
	}

	/**
	 * There may be other items that will be usable during battles?
	 *
	 */
	//TODO: Edit this method so it takes a parameter, if it is the case that other types of items may be used during battles
	public void useItem() {
		this.myHero.useHealthPotion();
		this.myMonster.attack(this.myHero);
	}
	public void block() {
		if(!this.myHero.block()) {
			this.myMonster.attack(this.myHero);
		}
	}
	


	/**
	 * Creates a random Monster object.
	 * 
	 * @return returns a Monster object.
	 */
	final private Monster createMonster() {
		
		int numb = Tools.RANDOM.nextInt(256);
		
		if((numb % 2) == 0 && numb > 50) {
			return new Ogre();
		}
		else if( (numb % 3) == 0 && numb < 50 ) {
			return new Gremlin();
		}
		else if( (numb % 2) == 0 && numb < 50) {
			return new Beast();
		}
		return new Skeleton();	
	}



}
