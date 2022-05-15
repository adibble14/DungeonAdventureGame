
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Handles logic for game. Receives input from player. Interacts with Dungeon and Hero classes.
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
		myDungeon = new Dungeon( 10, .15);
	}
	public static Dungeon getMyDungeon(){return  myDungeon;}

	public static Hero getMyHero(){return  myHero;}


	public static Image changeRooms(Dungeon theDungeon, Image theCurrentImage, int theX, int theY){
		Room newCurrent = theDungeon.getRoom(theX, theY);
		if(newCurrent != null){
			theDungeon.setCurrentRoom(newCurrent);
			DungeonGUI.getMyRoomLabel().setText(getRoomLabel(theDungeon));
			return Dungeon.setMyDungeonRoom(theDungeon);
		}else{
			System.out.println("can't go that way!");
			return theCurrentImage;
		}

	}

	public static Image setRoomWindow(Dungeon theDungeon, int theX, int theY){
		Room newCurrent = theDungeon.getRoom(theX, theY);
		theDungeon.setCurrentRoom(newCurrent);

		return Dungeon.setMyDungeonRoom(theDungeon);
	}

	/**
	 * Sets the GUI room location, i.e. the 0th row and 0th column
	 * room would be 'Current room: [0,0]'
	 * @param theDungeon Dungeon created after CharacterSelect
	 */
	public static String getRoomLabel(final Dungeon theDungeon){
		return "Current room: [" + theDungeon.getCurrentRoom().getXCoord()
				+ "," + theDungeon.getCurrentRoom().getYCoord() + "]";
	}

	public static void createBattle(){
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero));
	}

	/**
	 * Check rooms for monsters, boss, pits, and items and acts accordingly
	 */
	public static char checkRoom(){
		Room currentRoom = myDungeon.getCurrentRoom();

		if(currentRoom.getMyType() == RoomType.BOSS_ROOM || currentRoom.containsMonster()){
			DungeonAdventure.createBattle();
			return 'b';
		}else if(currentRoom.getMyType() == RoomType.PIT){
			myHero.takeDamage(10);
			DungeonGUI.setHealthLabel(myHero);
			return 'p';
		}
		return '0';

	}





}
