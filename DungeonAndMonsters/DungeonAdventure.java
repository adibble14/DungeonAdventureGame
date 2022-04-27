
import java.util.Scanner;

/**
 * Handles logic for game. Receives input from player. Interacts with Dungeon and Hero classes.
 * 
 * @author Mario Flores Vences
 *
 */
public class DungeonAdventure {
	
	/**
	 * main method of class. Creates instance of Hero, Dungeon objects.
	 * Prints useful information for the player.
	 * 
	 * @param theArgs
	 */
	public static void main(String[] theArgs) {
		
		Scanner console = new Scanner(System.in);
		// Prints info to player
		gamePlay();
		heroInfo();
		// Assigning player name
		String playerName = chooseName(console);
		System.out.println("Choose your Hero: ");
		// Hero choice is a string that represents which hero they want
		String heroChoice = playerInput(console);
		// Spawn hero using hero choice
		Hero hero = createHero(heroChoice, playerName);
		// Instance of dungeon (which has rooms)
		Dungeon dungeon = new Dungeon(hero);
		// Starter prompt
		System.out.println(hero.getName() + " finds themself in a dark dungeon. Find a way out....");
		// Initiating main loop
		mainLoop(console, dungeon, hero);
		
	}
	
	/**
	 * Main loop of the game. Is active as long as player does not quit, die, or have not
	 * beat the game yet.
	 * 
	 * @param theScanner
	 * @param theDungeon
	 * @param theHero
	 */
	public static void mainLoop(final Scanner theScanner, final Dungeon theDungeon, final Hero theHero) {
		
		String input = "";
		// Battle object
		Battle battle = new Battle(theHero);
		// Setting result to true to start?? TODO better name
		boolean battleResult = true;
		// Main loop starts here, check if hero is alive
		// not sure why we check fo 'd'
		while(!input.equalsIgnoreCase("d") && theHero.isAlive() && battleResult)  {

			// Status of hero print
			System.out.println(theHero.toString());

			// Prints room in x and y coordinates
			System.out.println("Current room: " + theDungeon.getCurrentRoom().getXCoord()
					+ ", " + theDungeon.getCurrentRoom().getYCoord() + "\n\n");
			// Prompts user for what they want to do and returns it by updating
			// our input variable
			input = chooseOption(theScanner);
			/*
			 * Strange if else block, do we want the input to all be else
			 * or all be ifs - TODO fix
			 * -----------------------------------------------------------
			 */

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
				System.out.println(theDungeon.toString());
			}

			// Movement option, we ask for input again to choose *where* we move
			else if(input.equalsIgnoreCase("m")) {
				input = chooseDirection(theScanner);
				// Then we move
				theDungeon.movePlayer(input, theHero);
			}

			/*
			 * --------------------------------------------------------------------
			 *End of input if else
			 */

			// in the same loop - check if we are in a monster room
			if(theDungeon.getCurrentRoom().containsMonster()) {
				// Run battle separately, but keep the output here
				battleResult = battle.scene();
				// Remove monster
				theDungeon.getCurrentRoom().removeMonster();
			}
			// Check as an else to a monster room (monsters cannot be in the same room
			// as exits??) if we are at the exit and have enough keys we leave
			// else we remind the player how many keys they need.
			else if(theDungeon.getCurrentRoom().isExit()) {
				System.out.println(theHero.getName() + " found the exit!");
				if(theHero.getKeyCount() == 2) {
					System.out.println("All Keys retrieved. " + theHero.getName() + " exits the dungeon...");
					System.out.println("You have cleared the dungeon!");
					theDungeon.unhideRooms();
					System.out.println(theDungeon.toString());
					return;
				}
				else {
					System.out.println(theHero.getName() + " needs " + ( 2 -theHero.getKeyCount() ) + " key(s) to open the exit.");
				}
			}
			// ALL AROUND CHECK??
			// If at any point we stop being alive we break out of main loop
			// TODO may need to make things more concise
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
	public static void heroInfo() {

		// When displaying a lot of information, using StringBuffer
		// Nice choice, however, we will need to change this when implementing GUI
		// TODO When implementing GUI, consider different menus for information
		StringBuffer string = new StringBuffer();
		string.append("\n\nDungeon Heroes: \n\n");
		string.append("(a) Warrior: \n The Warrior has the highest health pool and block chance. With modest attack damage.\n");
		string.append("It has the Crushing Blow special attack. Which deals a maximum of 175 damage or a guaranteed 50 minimum damage.\n\n\n");
		string.append("(b) Mage: \n The Mage has the highest attack damage and health regeneration. However, the health pool is not the best.\n");
		string.append("It has the Life Steal special attack. Which absorbs half of the enemy's current health.\n\n\n");
		string.append("(c) Thief: \n The Thief has the highest accuracy and speed. However, the attack damage is not the best.\n");
		string.append("It has the Surprise Attack special attack. Which deals a minimum of 40 damage and a chance of a follow up attack.\n\n\n");
		string.append("(d) Archer: \n The Archer has modest stats.\n");
		string.append("It has the Arrow Volley special attack. Which shoots a maximum of five arrows.\n\n\n");
		System.out.println(string.toString());
	}
	
	/**
	 * Creates a Hero object depending on user's input name and user's choice.
	 * 
	 * @param theChoice user's preferred choice
	 * @param theName user's input name
	 * @return returns Hero Object
	 */
	public static Hero createHero(final String theChoice, final String theName) {
		// Naming choices for character represent the hero type?
		// aka "w" would be for warrior? etc.
		// Or, have picture representation of the characters which can be clicked on?
		//TODO make pictures for each character to be chosen

		if(theChoice.equalsIgnoreCase("a")) {
			
			return new Warrior(theName);
		}
		else if(theChoice.equalsIgnoreCase("b")) {
			
			return new Mage(theName);
		}
		else if(theChoice.equalsIgnoreCase("c")) {
			
			return new Thief(theName);
		}
		return new Archer(theName);
	}
	
	/**
	 * Offers options of the "Main menu" to player and receives input from player
	 * 
	 * @param theScanner
	 * @return
	 */
	public static String chooseOption(final Scanner theScanner) {
		boolean flag = true;
		String input = "";
		while(flag) {
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
	 * @param theScanner
	 * @return
	 */
	public static String chooseDirection(final Scanner theScanner) {
		
		boolean flag = true;
		String input = "";
		while(flag) {
			// TODO optionally, up, down, left, right (U, D, L, R) might work better
			//TODO maybe use enums?
			System.out.println("Choose a direction (N-W-S-E): ");
			System.out.print("> ");
			input = theScanner.next();
			if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("e") 
					|| input.equalsIgnoreCase("w") || input.equalsIgnoreCase("z")) {
				flag = false;
			}
			else {
				System.out.println("Input not recognized... try again!");
			}
		}
		return input;
	}
	
	/**
	 * Takes user input for character name. Checks to see if
	 * input name is valid. Must be one to ten character long.
	 * 
	 * @param theScanner scanner object used to get user input
	 * @return returns user input name
	 */
	public static String chooseName(final Scanner theScanner) {
		
		boolean flag = true;
		String name = "";
		
		while(flag) {
			
			System.out.println("Choose Your Name: ");
			System.out.print("> ");
			name = theScanner.next();
				if(name.length() >= 1 && name.length() <= 10) {
					flag = false;
				}
				else {
					System.out.println("Entered name is invalid. Name must be between 1 and 10 characters long.");
				}
			}
			return name;
		}
	
	/**
	 * Displays general information on the console screen.
	 */
	public static void gamePlay() {
		
		StringBuffer string = new StringBuffer();
		string.append("Gauntlet - A Dungeon Crawling and vs Monster game. \n\n");
		string.append("Game Play:\n\n- Choose a Hero to do battle with \n- Choose your character name \n");
		string.append("- Navigate through the dungeon. Find potions along the way to aid you.\n");
		string.append("- Find the exit to win. Two keys are required to exit. They are somewhere in the dungeon. \n");
		string.append("- Traps and various monsters await in the Dungeon.\n");
		System.out.println(string.toString());
		
	}
	
	/**
	 * Checks to see if user input is valid. Only for choices are valid: a b c d
	 * 
	 * @return returns user input
	 */
	public static String playerInput(final Scanner theScanner) {
		// TODO This looks similar to another method... See chooseDirection

		boolean flag = true;
		String input = "";
		
		while(flag) {
			System.out.print("> ");
			input = theScanner.next();
			if(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b") || input.equalsIgnoreCase("c") || input.equalsIgnoreCase("d") ) {
				
				flag = false;
			}
		
			else {
				
				System.out.println("Input not recognized! Try again....\n");
			}	
		}
				
		return input;
	}
	
	/**
	 * Development options for testing game, cheats.
	 * 
	 * @param theDungeon
	 * @param theHero
	 * @param theScanner
	 */
	public static void dev(final Dungeon theDungeon, final Hero theHero, final Scanner theScanner) {
		
		System.out.println("Enable Cheats: (a) Increase Health potions by five (b) Increase Vision Potions by five (c) Uncover all Rooms (d) Return");
		String input = "";
		while(!input.equalsIgnoreCase("d")) {
			
			input = playerInput(theScanner);
			if(input.equalsIgnoreCase("a")) {
				theHero.setHealthPotions(5);
			}
			else if(input.equalsIgnoreCase("b")) {
				theHero.setVisionPotions(5);
			}
			else if(input.equalsIgnoreCase("c")) {
				theDungeon.unhideRooms();
			}
		}
	}
	

}
