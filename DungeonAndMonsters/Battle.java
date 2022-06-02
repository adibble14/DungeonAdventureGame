
/**
 *Creates a battle between the hero and a random monster
 */
public class Battle {

	/**
	 * our hero
	 */
	private final Hero myHero;

	/**
	 * the monster to fight
	 */
	private final Monster myMonster;


	/**
	 * constructor for battle, used for all monsters except mimic
	 * @param theHero the hero
	 * @param theType whether it is a boss monster or not
	 */
	protected Battle(final Hero theHero, final String theType) {
		this.myHero = theHero;
		this.myMonster = createMonster(theType);
	}

	/**
	 * constructor for battle, used for mimic monster found in chests
	 * @param theHero the hero
	 * @param theMonster initiates the monster to the monster passed in
	 */
	protected Battle(final Hero theHero, final Monster theMonster) {
		this.myHero = theHero;
		this.myMonster = theMonster;
	}

	/**
	 * @return our hero
	 */
	protected Hero getMyHero() {
		return myHero;
	}

	/**
	 * @return the monster in battle
	 */
	protected Monster getMyMonster() {
		return myMonster;
	}

	/**
	 * the attack turn process for each character in the batter
	 * @param theSpecialCase if the special is activated
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
		Pillar[] p = this.myHero.getMyInventory().getPillars();			//checks to see if the player is still invincible from abstraction pillar
		for (Pillar i : p) {
			if (i.getMY_TYPE() == PillarType.ABSTRACTION) {
				PillarOfAbstraction a = (PillarOfAbstraction) i;
				if (a.isActive()) {
					a.tick();
				}
			}
		}

	}

	/**
	 * @return if the monster or hero won
	 */
	protected boolean checkWinner() {
		return myHero.getHealth() <= 0 || myMonster.getHealth() <= 0;
	}

	/**
	 * Creates a random Monster object.
	 * @return returns a Monster object.
	 */
	private Monster createMonster(String theType) {

		int numb = Tools.RANDOM.nextInt(256);

		Music.playSFX("monsterSpawn");

		if (numb > 200) {
			return new Skeleton(theType);
		} else if ((numb % 2) == 0 && numb > 50) {
			return new Ogre(theType);
		} else if (numb > 150) {
			return new Gremlin(theType);
		} else if (numb > 100) {
			return new Beast(theType);
		}else{
			return new Ogre(theType);
		}

	}
}