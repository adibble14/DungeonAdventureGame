

import javax.swing.*;
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


	private Hero myHero;
	private Monster myMonster;

	protected Battle(final Hero theHero, final String theType) {
		this.myHero = theHero;
		this.myMonster = createMonster(theType);
	}

	protected Battle(final Hero theHero, final Monster theMonster) {
		this.myHero = theHero;
		this.myMonster = theMonster;
	}

	// Getter for our private fields - Hero and Monster
	protected Hero getMyHero() {
		return myHero;
	}

	protected Monster getMyMonster() {
		return myMonster;
	}

	/**
	 * the DungeonCharacter with the highest speed stat goes first
	 */
	protected void attackPhase(final boolean theSpecialCase) {
		if (this.myHero.getSpeed() < this.myMonster.getSpeed()) {
			this.myMonster.attack(this.myHero);
			if (checkWinner()) {
				BattleGUI.updateBattle();
				DungeonAdventure.gameOver();
				return;
			}
			if (theSpecialCase) {
				this.myHero.special(this.myMonster);
			} else {
				this.myHero.attack(this.myMonster);
			}
			if (checkWinner()) {
				BattleGUI.updateBattle();
				DungeonAdventure.battleWin(this.myMonster);
			}
		} else {
			if (theSpecialCase) {
				this.myHero.special(this.myMonster);
			} else {
				this.myHero.attack(this.myMonster);
			}
			if (checkWinner()) {
				BattleGUI.updateBattle();
				DungeonAdventure.battleWin(this.myMonster);
				return;
			}
			this.myMonster.attack(this.myHero);
			if (checkWinner()) {
				BattleGUI.updateBattle();
				DungeonAdventure.gameOver();
			}
		}
		Pillar[] p = this.myHero.getMyInventory().getPillars();
		for (Pillar i : p) {
			if (i.getMY_TYPE() == PillarType.ABSTRACTION) {
				PillarOfAbstraction a = (PillarOfAbstraction) i;
				if (a.isActive()) {
					a.tick();
				}
			}
		}

	}

	protected boolean checkWinner() {
		if (myHero.getHealth() <= 0 || myMonster.getHealth() <= 0) {
			return true;
		} else {
			return false;
		}
		/*if(myHero.getHealth() <= 0){
			BattleGUI.updateBattle();
			DungeonAdventure.gameOver();
		}else if(myMonster.getHealth() <= 0){
			BattleGUI.updateBattle();
			DungeonAdventure.battleWin();
		}*/
	}

	/**
	 * There may be other items that will be usable during battles?
	 */
	//TODO: Edit this method so it takes a parameter, if it is the case that other types of items may be used during battles
	public void useItem() {
		this.myHero.useHealthPotion();        //this now returns an integer
		this.myMonster.attack(this.myHero);
	}

	public void block() {
		if (!this.myHero.block()) {
			this.myMonster.attack(this.myHero);
		}
	}


	/**
	 * Creates a random Monster object.
	 * Sort of like a factory method to create a random Monster.
	 *
	 * @return returns a Monster object.
	 */

	final private Monster createMonster(String theType) {

		int numb = Tools.RANDOM.nextInt(256);

		Music.playSFX("monsterSpawn");
		if (numb > 200) {
			return new Skeleton(theType);
		}

			if ((numb % 2) == 0 && numb > 50) {
				return new Ogre(theType);
			} else if (numb > 150 && numb < 200) {
				return new Gremlin(theType);
			} else if (numb > 100 && numb < 150) {
				return new Beast(theType);
			}
			return new Ogre(theType);

	/*final private Monster createBossMonster(String theType) {

		int numb = Tools.RANDOM.nextInt(256);

		if(numb > 200) {
			return new Skeleton(theType);
		}
		else if(numb > 150 && numb < 200 ) {
			return new Gremlin(theType);
		}
		else if( numb > 100 && numb < 150) {
			return new Beast(theType);
		}
		return new Ogre(theType);
	}*/


	}
}