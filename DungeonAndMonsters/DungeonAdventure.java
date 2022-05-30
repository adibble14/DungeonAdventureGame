
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Handles logic for game. Receives input from player. Interacts with Dungeon and Hero classes.
 */
public class DungeonAdventure implements Serializable {
	// Scene list
	private static MainGUI MAIN_GUI = new MainGUI();
	private static String myUserName;
	private static String myHeroChoice;
	private static Hero myHero;
	private static Dungeon myDungeon;
	private static double myItemRoomChance = .8;
	private static int numDungeonsPassed;
	private static int currentDungeonNum;

	/**
	 * main method of class. Creates instance of Hero, Dungeon objects.
	 * Prints useful information for the player.
	 * //TODO could be changed to simply create the Display, keeping things simple
	 *
	 * @param theArgs Command line arguments
	 */
	public static void main(String[] theArgs) {
	}

	// START METHOD
	protected static void setUPGame(){
		SQLiteDB.createMonstersTable(); //creating the monsters table
		SQLiteDB.createHeroesTable(); //creating the heroes table
		numDungeonsPassed = 0;
		currentDungeonNum = 1;
		createHero();
		MAIN_GUI.setTheHero(myHero);
		MAIN_GUI.setMyDungeon(myDungeon);
		createDungeon();
		// First run through updating the dungeonGUI scene
		//DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
	}
	protected static void loadUpGame() {
		//TODO: Just for testing purposes. Should load images from database later on.

		//TODO why are these coded in????
		myHero.setMyInGameSprite(myHero.getMyInGameSprite());
		myHero.setSprite(myHero.getMySprite());
		numDungeonsPassed = myDungeon.getMyCurrentDungeonNumber();
		MAIN_GUI.setTheHero(myHero);
		MAIN_GUI.setMyDungeon(myDungeon);
		DungeonGUI.setUpVisualDungeon(myHero,myDungeon);
		MAIN_GUI.getBackpackGui().loadPlayerInventory();
		DungeonGUI.setMyHeroImage(myHero);
	}


	/**
	 * Scene manager, pass in string representation of which scene you want
	 * and it will move you to that scene. I.E. "character" brings you to character scene
	 * @param theMenuChoice choice for which scene you want
	 */
	protected static void sceneController(final String theMenuChoice){
		MAIN_GUI.setCurrentCard(theMenuChoice);
		//setSaveLoad();
	}


	/**
	 * Creates a Hero object depending on user's input name and user's choice.

	 * @return returns Hero Object
	 */
	protected static void createHero() {
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

	/**
	 * Set method used when loading save file
	 * @param theHero
	 */
	protected static void setMyHero(final Hero theHero) {
		myHero = theHero;
	}

	/**
	 * Set method used when loading in save file
	 * @param theDungeon
	 */
	protected static void setMyDungeon(final Dungeon theDungeon) {
		myDungeon = theDungeon;
	}

	protected static String getUserName(){return myUserName;}
	protected static void setMyUserName(final String theName){
		myUserName = theName;
	}

	protected static String getMyHeroChoice(){return myHeroChoice;}
	protected static void setMyHeroChoice(final String theChoice){myHeroChoice = theChoice;}

	protected static void createDungeon(){
		myDungeon = new Dungeon( 10, .23, getNumDungeonsPassed(), getCurrentDungeonNum());
		DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
	}
	protected static Dungeon getMyDungeon(){return  myDungeon;}

	protected static Hero getMyHero(){return  myHero;}


	protected static Image changeRooms(final Dungeon theDungeon, final Image theCurrentImage, final int theX, final int theY){
		Room newCurrent = theDungeon.getRoom(theX, theY);
		if(newCurrent != null){
			newCurrent.setMyDiscovery();
			theDungeon.setCurrentRoom(newCurrent);
			MAIN_GUI.getMapGui().repaint();
			DungeonGUI.getMyRoomLabel().setText(getRoomLabel(theDungeon));
			return Dungeon.setMyDungeonRoom(theDungeon);
		}else{
			return theCurrentImage;
		}

	}
	protected static void refreshBackPackGoldValue(){MAIN_GUI.getBackpackGui().refreshGoldValue();}
	protected static void refreshDungeonHealthValue(){DungeonGUI.setHealthLabel(myHero);}
	protected static void refreshMap(){
		MAIN_GUI.getMapGui().repaint();
	}
	protected static Image setRoomWindow(final Dungeon theDungeon, final int theX, final int theY){
		Room newCurrent = theDungeon.getRoom(theX, theY);
		theDungeon.setCurrentRoom(newCurrent);

		return Dungeon.setMyDungeonRoom(theDungeon);
	}

	/**
	 * Sets the GUI room location, i.e. the 0th row and 0th column
	 * room would be 'Current room: [0,0]'
	 * @param theDungeon Dungeon created after CharacterSelect
	 */
	protected static String getRoomLabel(final Dungeon theDungeon){
		return "Current room: [" + theDungeon.getCurrentRoom().getXCoord()
				+ "," + theDungeon.getCurrentRoom().getYCoord() + "]";
	}

	protected static void createBattle(){
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero));
	}

	/**
	 * Used when a chest is a mimic. Need to make a battle with a mimic happen.
	 * @param theMonster
	 */
	protected static void createBattle(final Monster theMonster){
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero, theMonster));
	}
	/**
	 * Check rooms for monsters, boss, pits, and items and acts accordingly
	 */
	protected static void checkRoom(){
		StringBuilder playerConsole = new StringBuilder();
		Room currentRoom = myDungeon.getCurrentRoom();

		if(currentRoom.getMyType() == RoomType.BOSS_ROOM || currentRoom.containsMonster()){
			DungeonAdventure.createBattle();
		}else if(currentRoom.getMyType() == RoomType.PIT){
			DungeonGUI.addPit(new GridBagConstraints());
			myHero.takeDamage(10);
			DungeonGUI.setHealthLabel(myHero);
			playerConsole.append(myUserName + " has taken 10 damage from a pit trap!");
			DungeonGUI.setPlayerConsole(playerConsole);
			if(myHero.getHealth() <= 0){		//because the player died
				gameOver();
			}
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
		}else if(currentRoom.getMyType() == RoomType.EXIT) {
			if(numDungeonsPassed == currentDungeonNum){
				JOptionPane.showMessageDialog(null,"Oh No! Looks like the exit leads to another Dungeon....");
				currentDungeonNum++;
				nextDungeon();
			}
		} else{
			DungeonGUI.setPlayerConsole(playerConsole);
		}

	}


	protected static void gameOver(){
		int input = JOptionPane.showConfirmDialog(null, "GAME OVER.\nPLAY AGAIN?"); // 0=yes, 1=no, 2=cancel

		if(input == 0){		//play again
			playAgain();
		}else{		//close the game
			System.exit(0);
		}
	}
	protected static void playAgain(){
		sceneController("menu");
		BackpackGUI.removeAllItems();
		MAIN_GUI.closeMap();
		MAIN_GUI.closeBackPack();
	}

	protected static void battleWin(){
		if(myDungeon.getCurrentRoom().getMyType() == RoomType.BOSS_ROOM){		//if the user defeated a boss monster, then advance to next dungeon
			numDungeonsPassed++;
			myDungeon.getCurrentRoom().setEmpty();
			myDungeon.getCurrentRoom().removeMonster();
			if(numDungeonsPassed == 4){		//beat all 4 dungeons
				int input = JOptionPane.showConfirmDialog(null,"You have found all four Pillars of OO!\nYou have escaped the Dungeon!\nPLAY AGAIN?");
				if(input == 0){		//play again
					playAgain();
				}else{		//close the game
					System.exit(0);
				}
			}else{			//adding a pillar to the heroes inventory
				PlayerInventory inv = myHero.getMyInventory();
				Pillar[] pillars = inv.getPillars();
				boolean addedPillar = false;
				while(!addedPillar){
					PillarType pillarType = Pillar.getRandomPillar();
					boolean duplicate = false;
					for(int i = 0; i<pillars.length; i++){
						if(pillars[i].getMY_TYPE() == pillarType){
							duplicate = true;
						}
					}
					if(!duplicate) {
						Pillar pillar = null;
						if (pillarType == PillarType.ABSTRACTION) {
							pillar = new PillarOfAbstraction(pillarType);
							MAIN_GUI.getBackpackGui().addItemToBackpack("abstract", pillar.getMyImage(), pillar);
						} else if (pillarType == PillarType.ENCAPSULATION) {
							pillar = new PillarOfEncapsulation(pillarType);
							MAIN_GUI.getBackpackGui().addItemToBackpack("encap",pillar.getMyImage(), pillar);
						} else if (pillarType == PillarType.INHERITANCE) {
							pillar = new PillarOfInheritance(pillarType);
							MAIN_GUI.getBackpackGui().addItemToBackpack("inher",pillar.getMyImage(), pillar);
						} else {
							pillar = new PillarOfPolymorphism(pillarType);
							MAIN_GUI.getBackpackGui().addItemToBackpack("poly",pillar.getMyImage(), pillar);
						}
						inv.addPillar(pillar);		//TODO add image to backpack

						addedPillar = true;
						JOptionPane.showMessageDialog(null,"Congrats! You have defeated the Boss of "+pillarType+"!\nYou have been awarded the Pillar of "+pillarType+"!\n" +
								"Find the exit to escape!");
						DungeonGUI.resetDungeonImage();
						DungeonGUI.enableButtons();
						DungeonGUI.disableButtons(Dungeon.availableRooms(DungeonGUI.getDungeon()));
						DungeonAdventure.sceneController("dungeon");
					}
				}
			}
		}else{
			myDungeon.getCurrentRoom().setEmpty();
			myDungeon.getCurrentRoom().removeMonster();
			myHero.setGoldAmount(Tools.RANDOM.nextInt(25, 75));
			JOptionPane.showMessageDialog(null, "Congrats! You won the battle!");
			DungeonAdventure.sceneController("dungeon");
			DungeonGUI.setHealthLabel(myHero);
		}
	}

	protected static void nextDungeon(){
		MAIN_GUI.closeMap();
		MAIN_GUI.closeBackPack();
		createDungeon();	//creating new dungeon
		myHero.setHealth(myHero.getMaxHealth()); //setting health back to full  //TODO should we set the health back to full???
		sceneController("dungeon");
		DungeonGUI.setHealthLabel(myHero);
		DungeonGUI.enableButtons();
		checkRoom();
	}

	// TODO not used decide if needed
	protected static MainGUI getMainGui() {
		return MAIN_GUI;
	}

	/**
	 * Methods to use for loading in a save file
	 */
	// TODO not used decide if needed
	protected static void setNumDungeonsPassed(final int theVal) {
		numDungeonsPassed = theVal;
	}
	protected static int getNumDungeonsPassed() {
		return numDungeonsPassed;
	}
	protected static int getCurrentDungeonNum(){
		return currentDungeonNum;
	}



}
