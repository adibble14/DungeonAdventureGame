
import java.awt.*;
import java.util.Scanner;

/**
 * Handles logic for game. Receives input from player. Interacts with Dungeon and Hero classes.
 *
 * @author Mario Flores Vences
 *
 */
public class DungeonAdventure {
	// Scene list
	private static final MainGUI MAIN_GUI = new MainGUI();
	private static String myUserName;
	private static String myHeroChoice;
	private static Hero myHero;
	private static Dungeon myDungeon;
	private static double myItemRoomChance = .35;
	/**
	 * main method of class. Creates instance of Hero, Dungeon objects.
	 * Prints useful information for the player.
	 * //TODO could be changed to simply create the Display, keeping things simple
	 *
	 * @param theArgs Command line arguments
	 */
	public static void main(String[] theArgs) {}

	// START METHOD
	public static void setUPGame(){
		createHero();
		createDungeon(getMyHero());
		// First run through updating the dungeonGUI scene
		DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
		//mainLoop(new Scanner(System.in),getMyDungeon(), getMyHero());
	}


	/**
	 * Scene manager, pass in string representation of which scene you want
	 * and it will move you to that scene. I.E. "character" brings you to character scene
	 * @param theMenuChoice choice for which scene you want
	 */
	protected static void sceneController(String theMenuChoice){
		MAIN_GUI.setCurrentCard(theMenuChoice);
	}


	/**
	 * Displays information about the Hero characters on the console screen.
	 */
	public static StringBuilder heroInfo(String characterType) {

		// When displaying a lot of information, using StringBuffer
		// Nice choice, however, we will need to change this when implementing GUI
		// TODO When implementing GUI, consider different menus for information
		StringBuilder heroInformation = new StringBuilder();
		switch(characterType){
			case "Warrior":
				heroInformation.append("Stats: \n125 hp\n3 attack speed\n30-50 damage\n80% accuracy\n60% block\n");
				heroInformation.append("Special: Crushing Blow\n75-175 damage\n40% accuracy");
				break;
			case "Mage":
				heroInformation.append("Stats: \n75 hp\n4 attack speed\n50-80 damage\n70% accuracy\n30% block\n");
				heroInformation.append("Special: Life Steal\nHalves enemies health and heals the damage taken\n100% accuracy");
				break;
			case "Thief":
				heroInformation.append("Stats: \n95 hp\n6 attack speed\n10-20 damage\n90% accuracy\n40% block\n");
				heroInformation.append("Special: Surprise!\n20-60 damage\nCan also land extra attack\n40-80 damage\n60% accuracy.");
				break;
			case "Archer":
				heroInformation.append("Stats: \n100 hp\n4 attack speed\n25-30 damage\n70% accuracy\n50% block\n");
				heroInformation.append("Special: Volley\nGenerates random number of attack turns\nmax number of attacks: 5\n30-50 damage");
				break;
			case "Priestess":
				heroInformation.append("Stats: \n75 hp\n5 attack speed\n25-45 damage\n70% accuracy\n30% block\n");
				heroInformation.append("Special: Revive\n45-90 damage\n50% accuracy for every point of damage dealt priestess heals 2/3 of the points");
				break;
			default:
				heroInformation.append("No info stored!");
				break;
		}

		return heroInformation;
	}



	/**
	 * Creates a Hero object depending on user's input name and user's choice.

	 * @return returns Hero Object
	 */
	public static void createHero() {
		switch(getMyHeroChoice()){
			case "w":
				myHero = new Warrior(getUserName());
				break;
			case "m":
				myHero = new Mage(getUserName());
				break;
			case "t":
				myHero = new Thief(getUserName());
				break;
			case "p":
				myHero = new Priestess(getUserName());
				break;
			case "a":
				myHero = new Archer(getUserName());
				break;
		}
	}

	public static String getUserName(){return myUserName;}
	public static void setMyUserName(final String theName){myUserName = theName;}

	public static String getMyHeroChoice(){return myHeroChoice;}
	public static void setMyHeroChoice(final String theChoice){myHeroChoice = theChoice;}

	public static void createDungeon(Hero theHero){
		myDungeon = new Dungeon(10, .15); //TODO: Dungeon now requires a size, chance of Item_Room spawn
	}
	public static Dungeon getMyDungeon(){return  myDungeon;}

	public static Hero getMyHero(){return  myHero;}

	/**
	 * Displays general information on the console screen.
	 */
	public static StringBuilder gamePlay() {

		StringBuilder generalInfo = new StringBuilder();
		generalInfo.append("Dungeons and Monsters - A Dungeon Crawling & Monster Fighting Game. \n\n");
		generalInfo.append("Game Play:\n\n- Choose a Hero to do battle with \n- Choose your character name \n");
		generalInfo.append("- Navigate through the dungeon. Find potions along the way to aid you.\n");
		generalInfo.append("- Find the exit to win. Four Pillars of OO are required to exit. They are somewhere in the dungeon. \n");
		generalInfo.append("- Traps and various monsters await in the Dungeon.\n");
		return generalInfo;

	}

	public static StringBuilder statInfo(){
		StringBuilder statInfo = new StringBuilder();
		statInfo.append("Stat Definitions\n");
		statInfo.append("Hit Points (hp): the number of health your hero has\n");
		statInfo.append("Attack Speed: how fast your hero is. \nEx) 4 attack speed fights monsters with 2 attack speed, hero gets two attacks per 1 monster attack \n");
		statInfo.append("Damage: the number of damage dealt to the enemy per attack\n");
		statInfo.append("Accuracy: the chance an attack lands a hit on enemy\n");
		statInfo.append("Block Rate: the chance your hero can defend an attack from an enemy\n");


		return statInfo;
	}


	/**
	 * Development options for testing game, cheats.
	 *
	 * @param theDungeon the dungeon the user is in
	 * @param theHero the hero the user has chosen
	 * @param theScanner scanner to look for user input
	 */
	/*public static void dev(final Dungeon theDungeon, final Hero theHero, final Scanner theScanner) {

		System.out.println("Enable Cheats: (a) Increase Health potions by five (b) Increase Vision Potions by five (c) Uncover all Rooms (d) Return");
		String input = "";
		while(!input.equalsIgnoreCase("d")) {

			//input = playerInput(theScanner);
			if(input.equalsIgnoreCase("a")) {
				theHero.setHealthPotions(5);
				break;
			}
			else if(input.equalsIgnoreCase("b")) {
				theHero.setVisionPotions(5);
				break;
			}
			else if(input.equalsIgnoreCase("c")) {
				theDungeon.revealRooms();
				break;
			}
		}
	}*/


}
	
