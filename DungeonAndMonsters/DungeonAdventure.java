
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
	private static double myItemRoomChance = .7;
	private static int numDungeonsPassed;
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
		numDungeonsPassed = 0;
		createHero();
		MAIN_GUI.setTheHero(myHero);
		createDungeon();
		// First run through updating the dungeonGUI scene
		//DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
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

	public static void createDungeon(){
		myDungeon = new Dungeon( 10, .15);
		DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
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
	 * Used when a chest is a mimic. Need to make a battle with a mimic happen.
	 * @param theMonster
	 */
	public static void createBattle(Monster theMonster){
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero, theMonster));
	}
	/**
	 * Check rooms for monsters, boss, pits, and items and acts accordingly
	 */
	public static char checkRoom(){
		StringBuilder playerConsole = new StringBuilder();
		Room currentRoom = myDungeon.getCurrentRoom();

		if(currentRoom.getMyType() == RoomType.BOSS_ROOM || currentRoom.containsMonster()){
			DungeonAdventure.createBattle();
			return 'b';
		}else if(currentRoom.getMyType() == RoomType.PIT){
			myHero.takeDamage(10);
			DungeonGUI.setHealthLabel(myHero);
			playerConsole.append(myUserName + " has taken 10 damage from a pit trap!");
			DungeonGUI.setPlayerConsole(playerConsole);
			if(myHero.getHealth() <= 0){		//because the player died
				gameOver();
			}
			return 'p';
		} else if(currentRoom.getMyType() == RoomType.ITEM_ROOM) {
			currentRoom.addItemsToPlayerInventory(myHero);
			PlayerInventory inv = myHero.getMyInventory();
			while(MAIN_GUI.getBackpackGui().getMyActiveHealthPotions() < inv.getItemCount(ItemType.HEALTH_POTION)) {
				MAIN_GUI.getBackpackGui().addItemToBackpack(ItemType.HEALTH_POTION.toString());
				playerConsole.append(myUserName).append(" has obtained a Health Potion!\n");
			}
			while(MAIN_GUI.getBackpackGui().getMyActiveVisionPotions() < inv.getItemCount(ItemType.VISION_POTION)) {
				MAIN_GUI.getBackpackGui().addItemToBackpack(ItemType.VISION_POTION.toString());
				playerConsole.append(myUserName).append(" has obtained a Vision Potion!\n");
			}
			MAIN_GUI.getBackpackGui().refreshGoldValue();
			currentRoom.setEmpty();
			DungeonGUI.setPlayerConsole(playerConsole);
			return 'I';
		} else{
			DungeonGUI.setPlayerConsole(playerConsole);
			return '0';
		}

	}


	public static void gameOver(){
		int input = JOptionPane.showConfirmDialog(null, "GAME OVER.\nPLAY AGAIN?"); // 0=yes, 1=no, 2=cancel

		if(input == 0){		//play again
			playAgain();
		}else{		//close the game
			System.exit(0);
		}
	}
	public static void playAgain(){
		sceneController("menu");
	}

	public static void battleWin(){
		if(myDungeon.getCurrentRoom().getMyType() == RoomType.BOSS_ROOM){		//if the user defeated a boss monster, then advance to next dungeon
			numDungeonsPassed++;
			if(numDungeonsPassed == 1){		//TODO add corresponding pillar into backpack and also show image in the message dialog
				JOptionPane.showMessageDialog(null,"Congrats! You have defeated the Boss of Inheritance!\nYou have been awarded the Pillar of Inheritance!");
				nextDungeon();
			}else if(numDungeonsPassed == 2){
				JOptionPane.showMessageDialog(null,"Congrats! You have defeated the Boss of Encapsulation!\nYou have been awarded the Pillar of Encapsulation!");
				nextDungeon();
			}else if(numDungeonsPassed == 3){
				JOptionPane.showMessageDialog(null,"Congrats! You have defeated the Boss of Abstraction!\nYou have been awarded the Pillar of Abstraction!");
				nextDungeon();
			} else{	//beat all 4 dungeons
				JOptionPane.showMessageDialog(null,"Congrats! You have defeated the Boss of Polymorphism!\nYou have been awarded the Pillar of Polymorphism!");
				int input = JOptionPane.showConfirmDialog(null,"You have found all four Pillars of OO!\nYou have escaped the Dungeon!\nPLAY AGAIN?");
				if(input == 0){		//play again
					playAgain();
				}else{		//close the game
					System.exit(0);
				}
			}
		}else{
			JOptionPane.showMessageDialog(null,"Congrats! You won the battle!");
			DungeonAdventure.sceneController("dungeon");
			DungeonGUI.setHealthLabel(myHero);
		}
	}

	public static void nextDungeon(){
		createDungeon();	//creating new dungeon
		myHero.setHealth(myHero.getMaxHealth()); //setting health back to full  //TODO should we set the health back to full???
		sceneController("dungeon");
		DungeonGUI.setHealthLabel(myHero);
		DungeonGUI.enableButtons();
	}

	public static String getNameOfDungeon(){
		switch(numDungeonsPassed){
			case 1: return "Inheritance";
			case 2: return "Encapsulation";
			case 3: return "Abstraction";
			default:return "Polymorphism";
		}
	}







}
