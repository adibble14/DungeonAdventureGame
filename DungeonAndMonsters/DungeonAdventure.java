
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
	/**
	 * main method of class. Creates instance of Hero, Dungeon objects.
	 * Prints useful information for the player.
	 * //TODO could be changed to simply create the Display, keeping things simple
	 *
	 * @param theArgs Command line arguments
	 */
	public static void main(String[] theArgs) {}

	public static void setUPGame(){
		createHero();
		createDungeon(getMyHero());
		mainLoop(new Scanner(System.in), getMyDungeon(), getMyHero());
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
	 * Main loop of the game. Is active as long as player does not quit, die, or have not
	 * beat the game yet.
	 *
	 * @param theScanner Scanner that looks for user input
	 * @param theDungeon Dungeon the user is currently playing in
	 * @param theHero Hero that the user controls
	 */
	//This is the main Controller method
	public static void mainLoop(final Scanner theScanner, final Dungeon theDungeon, final Hero theHero) {

		String input;
		// Battle object
		Battle battle = new Battle(theHero);
		// As long as we continue to win battles we are still in the main loop
		// If we lose a battle the loop ends
		// Main loop starts here, check if hero is alive
		// not sure why we check fo 'd'
		while(theHero.isAlive())  {

			// Status of hero print
			//TODO delete this output once GUI is made, since this is VIEW
			System.out.println(theHero);

			// Prints room in x and y coordinates
			//TODO delete this output once GUI is made, since this is VIEW
			System.out.println("Current room: " + theDungeon.getCurrentRoom().getXCoord()
					+ ", " + theDungeon.getCurrentRoom().getYCoord() + "\n\n");
			// Prompts user for what they want to do and returns it by updating
			// our input variable
			input = chooseOption(theScanner);

			// -------------------dev options aka hacks
			if(input.equalsIgnoreCase("dev")) {
				dev(theDungeon, theHero, theScanner);
			}

			// Health potion option
			if(input.equalsIgnoreCase("h")) {
				theHero.useHealthPotion();
			}

			// Vision potion option
			else if(input.equalsIgnoreCase("v")) {
				theHero.useVisionPotion(theDungeon);
			}

			// Prints out dungeon (map) |note, why 'i'?|
			else if(input.equalsIgnoreCase("i")) {
				//TODO delete this output once GUI is made, since this is VIEW
				System.out.println(theDungeon);
			}

			// Movement option, we ask for input again to choose *where* we move
			else if(input.equalsIgnoreCase("m")) {
				input = chooseDirection(theScanner);
				// Then we move
				theDungeon.movePlayer(input, theHero);
			}

			// in the same loop - check if we are in a monster room
			if(theDungeon.getCurrentRoom().containsMonster()) {
				// Run battle separately, but keep the output here
				battle.scene();
				// Remove monster
				theDungeon.getCurrentRoom().removeMonster();
			}
			// Check as an else to a monster room (monsters cannot be in the same room
			// as exits??) if we are at the exit and have enough keys we leave
			// else we remind the player how many keys they need.
			else if(theDungeon.getCurrentRoom().isExit()) {
				//TODO delete this output once GUI is made, since this is VIEW
				System.out.println(theHero.getName() + " found the exit!");
				if(theHero.getKeyCount() == 2) {
					//TODO delete this output once GUI is made, since this is VIEW
					System.out.println("All Keys retrieved. " + theHero.getName() + " exits the dungeon...");
					System.out.println("You have cleared the dungeon!");
					theDungeon.revealRooms();
					System.out.println(theDungeon);
					return;
				}
				else {
					//TODO delete this output once GUI is made, since this is VIEW
					System.out.println(theHero.getName() + " needs " + ( 2 -theHero.getKeyCount() ) + " key(s) to open the exit.");
				}
			}
			//TODO delete this output once GUI is made, since this is VIEW
			//TODO this check is unnecessary! Our loop works by checking if the player is alive, it breaks if not
			if(!theHero.isAlive()) {
				System.out.println(theHero.getName() + " took too much damage. They close their eyes for the last time...");
				System.out.println("Game Over..");
				return;
			}
		}
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
				/*heroInformation.append("(a) Warrior: \n The Warrior has the highest health pool and block chance. With modest attack damage.\n");
				heroInformation.append("It has the Crushing Blow special attack. Which deals a maximum of 175 damage or a guaranteed 50 minimum damage.\n\n\n");*/
				heroInformation.append("Stats: \n125 hp\n3 attack speed\n30-50 damage\n80% accuracy\n60% block\n");
				heroInformation.append("Special: Crushing Blow\n75-175 damage\n40% accuracy");
				break;
			case "Mage":
				/*heroInformation.append("(b) Mage: \n The Mage has the highest attack damage and health regeneration. However, the health pool is not the best.\n");
				heroInformation.append("It has the Life Steal special attack. Which absorbs half of the enemy's current health.\n\n\n");*/
				heroInformation.append("Stats: \n75 hp\n4 attack speed\n50-80 damage\n70% accuracy\n30% block\n");
				heroInformation.append("Special: Life Steal\nHalves enemies health and heals the damage taken\n100% accuracy");
				break;
			case "Thief":
				/*heroInformation.append("(c) Thief: \n The Thief has the highest accuracy and speed. However, the attack damage is not the best.\n");
				heroInformation.append("It has the Surprise Attack special attack. Which deals a minimum of 40 damage and a chance of a follow up attack.\n\n\n");*/
				heroInformation.append("Stats: \n95 hp\n6 attack speed\n10-20 damage\n90% accuracy\n40% block\n");
				heroInformation.append("Special: Surprise!\n20-60 damage\nCan also land extra attack\n40-80 damage\n60% accuracy.");
				break;
			case "Archer":
				/*heroInformation.append("(d) Archer: \n The Archer has modest stats.\n");
				heroInformation.append("It has the Arrow Volley special attack. Which shoots a maximum of five arrows.\n\n\n");*/
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
		// Naming choices for character represent the hero type?
		// aka "w" would be for warrior? etc.
		// Or, have picture representation of the characters which can be clicked on?
		
		if(getMyHeroChoice().equalsIgnoreCase("w")) {
			myHero = new Warrior(getUserName());
			//return new Warrior(getUserName());
		}
		else if(getMyHeroChoice().equalsIgnoreCase("m")) {
			myHero = new Mage(getUserName());
			//return new Mage(getUserName());
		}
		else if(getMyHeroChoice().equalsIgnoreCase("t")) {
			myHero = new Thief(getUserName());
			//return new Thief(getUserName());
		}
		else if(getMyHeroChoice().equalsIgnoreCase("p")){
			myHero = new Priestess(getUserName());
			//return new Priestess(getUserName());
		}
		myHero = new Archer(getUserName());
		//return new Archer(getUserName());
	}

	/**
	 * Offers options of the "Main menu" to player and receives input from player
	 *
	 * @param theScanner Scanner for user input
	 * @return String representation of the option the user chose from the menu, can be 'h', 'v', 'm', 'i', and 'dev'
	 */
	public static String chooseOption(final Scanner theScanner) {
		boolean flag = true;
		String input = "";
		while(flag) {
			//TODO delete this output once GUI is made, since this is VIEW
			System.out.println("Move to another Room (M) | Use Health Potion (H) | Use Vision Potion (V) | See Map (I): ");
			System.out.print("> ");
			input = theScanner.next();
			if(input.equalsIgnoreCase("h") || input.equalsIgnoreCase("v") || input.equalsIgnoreCase("m") || input.equalsIgnoreCase("i") || input.equalsIgnoreCase("dev")) {
				flag = false;
			}
			else {
				System.out.println("Input not recognized... try again!!");
			}
		}
		return input;
	}

	/**
	 * Offers directions for player to choose from and handles the input
	 *
	 * @param theScanner Scanner to take in user input
	 * @return direction (N, W, S, E)
	 */
	public static String chooseDirection(final Scanner theScanner) {

		boolean flag = true;
		String input = "";
		while(flag) {
			// TODO optionally, up, down, left, right (U, D, L, R) might work better
			//TODO maybe use enums?
			//TODO delete this output once GUI is made, since this is VIEW
			System.out.println("Choose a direction (N-W-S-E): ");
			System.out.print("> ");
			input = theScanner.next();
			if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("e")
					|| input.equalsIgnoreCase("w") || input.equalsIgnoreCase("z")) {
				flag = false;
			}
			else {
				//TODO delete these errors once GUI made
				System.out.println("Input not recognized... try again!");
			}
		}
		return input;
	}


	public static String getUserName(){return myUserName;}
	public static void setMyUserName(final String theName){myUserName = theName;}

	public static String getMyHeroChoice(){return myHeroChoice;}
	public static void setMyHeroChoice(final String theChoice){myHeroChoice = theChoice;}

	public static void createDungeon(Hero theHero){
		myDungeon = new Dungeon(theHero);
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
	public static void dev(final Dungeon theDungeon, final Hero theHero, final Scanner theScanner) {

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
	}


}
	
