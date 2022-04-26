/*
 * TCSS 143 B - Winter 2021
 * Instructor: Tom Capaul
 * Programming Assignment 2: Heroes vs Monsters
 * Due Date: 02/04/2021
 */

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
	
	/**
	 * Hero Object field
	 */
	private Hero myHero;
	
	/**
	 * Monster Object field
	 */
	private Monster myMonster;
	
	/**
	 * Random Object field
	 */
	final private Random MY_RAND;
	
	/**
	 * Scanner object field
	 */
	final private Scanner MY_CONSOLE;
	
	/**
	 * Class constructor. Initializes fields.
	 * 
	 * @param theHero
	 */
	protected Battle(final Hero theHero) {
		
		this.MY_CONSOLE = new Scanner(System.in);
		this.MY_RAND = new Random();
		this.myHero = theHero;
		this.myMonster = null;
	}
	
	/**
	 * Game loop. Has game logic. Takes user input; then calls on DungeonCharacter methods to attack other DungeonCharacters or to heal.
	 * Creates a Monster Object.
	 * 
	 * @param theConsole scanner object used to get user input
	 * @param theHero hero object that is controlled by user
	 * @param theMonster monster object that is the opponent of user.
	 */
	final protected boolean scene() {
		// true == player is alive, false == player died or quit game
		this.myMonster = this.createMonster();
		String input = "";
		System.out.println("A " + this.myMonster.getName() + " has appeared!\n\n");
		while(!(input.equalsIgnoreCase("d")) && this.myMonster.isAlive() && this.myHero.isAlive()) {
			
			displayInfo();
			System.out.println("Choose Your Move: \n\n (a) attack   (b) special Move   (c) Health Potion  (d) Quit\n");
			input = this.playerInput();
			
			if(input.equalsIgnoreCase("d")) {
				System.out.println("Thanks for Playing!");
				return false;
			}
			else if(input.equalsIgnoreCase("a")) {
				this.myHero.attack(this.myMonster);
			}
			else if(input.equalsIgnoreCase("b")) {
				this.myHero.special(this.myMonster);
			}
			else if(input.equalsIgnoreCase("c")) {
				this.myHero.useHealthPotion();
			}
			
			if(!(this.myMonster.isAlive())) {
				System.out.println(this.myMonster.getName() + " has been defeated.\n\n");
				return true;
			} else {
				this.myMonster.attack(myHero);
			}
		
			if(!(this.myHero.isAlive())) {
				return false;
			}
		}
		System.out.println("Back to dungeon crawling.");
		return true;
	}

	/**
	 * Creates a random Monster object.
	 * 
	 * @return returns a Monster object.
	 */
	final private Monster createMonster() {
		
		int numb = this.MY_RAND.nextInt(256);
		
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

	/**
	 * Displays the health status of two DungeonCharacters on the console screen
	 * 
	 * @param theHero user's character choice
	 * @param theMonster random monster enemy
	 */
	final private void displayInfo() {
		
		StringBuffer string = new StringBuffer();
		string.append(this.myHero.getName() + "'s HP: " + this.myHero.getHealth() + "\n");
		string.append(this.myMonster.getName() + "'s HP: " + this.myMonster.getHealth() + "\n");
		System.out.println(string.toString());
	}
	
	/**
	 * Checks to see if user input is valid. Only for choices are valid: a b c d
	 * 
	 * @return returns user input
	 */
	final private String playerInput() {
		
		boolean flag = true;
		String input = "";
		
		while(flag) {
			System.out.print("> ");
			input = this.MY_CONSOLE.next();
			if(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b") || input.equalsIgnoreCase("c") || input.equalsIgnoreCase("d") ) {
				
				flag = false;
			}
		
			else {
				
				System.out.println("Input not recognized! Try again....\n");
			}	
		}
				
		return input;
	}
}
