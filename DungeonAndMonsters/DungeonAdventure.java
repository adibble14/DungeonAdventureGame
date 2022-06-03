import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * The controller for the project
 * Handles logic for game. Receives input from player. Interacts with Dungeon and Hero classes.
 */
public class DungeonAdventure implements Serializable {

	/**
	 * instance of MainGUI, has all the different panels of the GUI
	 */
	//private static final MainGUI MAIN_GUI = new MainGUI();
	private static MainGUI MAIN_GUI;

	/**
	 * hero's name
	 */
	private static String myUserName;

	/**
	 * the choice of the hero
	 */
	private static String myHeroChoice;

	/**
	 * the hero instance
	 */
	private static Hero myHero;

	/**
	 * the dungeon instance
	 */
	private static Dungeon myDungeon;

	/**
	 * number of dungeons completed
	 */
	private static int myNumDungeonsPassed;

	/**
	 * which dungeon hero is currently on
	 */
	private static int myCurrentDungeonNum;

	/**
	 * main method of class. Starts the game
	 * @param theArgs Command line arguments
	 */
	public static void main(String[] theArgs) {
		SQLiteDB.createMonstersTable(); //creating the monsters table
		SQLiteDB.createHeroesTable(); //creating the heroes table
		MAIN_GUI = new MainGUI();
	}

	/**
	 * method that initiates the game after character is chosen
	 */
	protected static void setUPGame(){
		//SQLiteDB.createMonstersTable(); //creating the monsters table
		//SQLiteDB.createHeroesTable(); //creating the heroes table
		myNumDungeonsPassed = 0;
		myCurrentDungeonNum = 1;
		createHero();
		refreshBackPackGoldValue();
		MAIN_GUI.setMyDungeon(myDungeon);
		createDungeon();
	}

	/**
	 * method that initiates the game after loading up a previous game
	 */
	protected static void loadUpGame() {
		myCurrentDungeonNum = myDungeon.getMyCurrentDungeonNumber();
		myNumDungeonsPassed = myDungeon.getMyDungeonsPassed();
		refreshBackPackGoldValue();
		MAIN_GUI.setMyDungeon(myDungeon);
		DungeonGUI.setUpVisualDungeon(myHero,myDungeon);
		MAIN_GUI.getBackpackGui().loadPlayerInventory();
		DungeonGUI.setMyHeroImage(myHero);
	}


	/**
	 * Scene manager, pass in string representation of which scene you want,
	 * and it will move you to that scene. I.E. "character" brings you to character scene
	 * @param theMenuChoice choice for which scene you want
	 */
	protected static void sceneController(final String theMenuChoice){
		MAIN_GUI.setCurrentCard(theMenuChoice);
	}


	/**
	 * Creates a Hero object depending on user's input name and user's choice.
	 */
	protected static void createHero() {
		switch (getMyHeroChoice()) {
			case "w" -> myHero = new Warrior(getUserName());
			case "m" -> myHero = new Mage(getUserName());
			case "t" -> myHero = new Thief(getUserName());
			case "p" -> myHero = new Priestess(getUserName());
			case "a" -> myHero = new Archer(getUserName());
		}
	}

	/**
	 * Set method used when loading save file
	 * @param theHero what to set hero to
	 */
	protected static void setMyHero(final Hero theHero) {
		myHero = theHero;
	}

	/**
	 * Set method used when loading in save file
	 * @param theDungeon what to set the dungeon to
	 */
	protected static void setMyDungeon(final Dungeon theDungeon) {
		myDungeon = theDungeon;
	}

	/**
	 * @return the username
	 */
	protected static String getUserName(){return myUserName;}

	/**
	 * @param theName what to set the username to
	 */
	protected static void setMyUserName(final String theName){
		myUserName = theName;
	}


	/**
	 * @return the hero choice
	 */
	protected static String getMyHeroChoice(){return myHeroChoice;}

	/**
	 * @param theChoice what hero the user chooses
	 */
	protected static void setMyHeroChoice(final String theChoice){myHeroChoice = theChoice;}

	/**
	 * creates a dungeon
	 */
	protected static void createDungeon(){
		myDungeon = new Dungeon( 10, .23, getNumDungeonsPassed(), getCurrentDungeonNum());
		DungeonGUI.setUpVisualDungeon(getMyHero(), getMyDungeon());
	}

	/**
	 * @return the current dungeon
	 */
	protected static Dungeon getMyDungeon(){return  myDungeon;}

	/**
	 * @return the instance of hero
	 */
	protected static Hero getMyHero(){return  myHero;}


	/**
	 * changes rooms, happens when user hits arrow buttons
	 * @param theDungeon the instance of dungeon
	 * @param theCurrentImage the current room image
	 * @param theX x coordinate to move to
	 * @param theY y coordinate to move to
	 * @return a new image of the new room
	 */
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

	/**
	 * refreshes the JLabel containing the amount of gold
	 */
	protected static void refreshBackPackGoldValue(){MAIN_GUI.getBackpackGui().refreshGoldValue();}

	/**
	 * refreshes the JLabel containing the amount of health of the hero
	 */
	protected static void refreshDungeonHealthValue(){DungeonGUI.setHealthLabel(myHero);}

	/**
	 * repaints the map
	 */
	protected static void refreshMap(){
		MAIN_GUI.getMapGui().repaint();
	}

	/**
	 * setting the room in the dungeon
	 * @param theDungeon the instance of dungeon
	 * @param theX the x coordinate
	 * @param theY the y coordinate
	 * @return the image of the room
	 */
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

	/**
	 * called when the user encounters a monster
	 * @param theType whether it is a boss monster
	 */
	protected static void createBattle(String theType){
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero, theType));

	}

	/**
	 * Used when a chest is a mimic. Need to make a battle with a mimic happen.
	 * @param theMonster the type of monster
	 */
	protected static void createBattle(final Monster theMonster){
		Music.playMusic("battle");
		DungeonAdventure.sceneController("battle");
		BattleGUI.setBattle(new Battle(myHero, theMonster));
	}

	/**
	 * Check rooms for monsters, boss, pits, and items and acts accordingly
	 */
	protected static void checkRoom(){
		StringBuilder playerConsole = new StringBuilder();
		Room currentRoom = myDungeon.getCurrentRoom();

		if(currentRoom.getMyType() == RoomType.BOSS_ROOM){
			Music.playMusic("battle");
			DungeonAdventure.createBattle("boss");
		}else if(currentRoom.containsMonster()){
			Music.playMusic("battle");
			DungeonAdventure.createBattle("monster");
		} else if(currentRoom.getMyType() == RoomType.PIT){
			DungeonGUI.addPit(new GridBagConstraints());
			Music.playSFX("pitTrap");
			myHero.takeDamage(10);
			DungeonGUI.setHealthLabel(myHero);
			playerConsole.append(myUserName).append(" has taken 10 damage from a pit trap!");
			DungeonGUI.setPlayerConsole(playerConsole);
			if(myHero.getHealth() <= 0){		//because the player died
				gameOver();
			}
		} else if(currentRoom.getMyType() == RoomType.ITEM_ROOM) {
			Music.playSFX("itemPickup");
			currentRoom.addItemsToPlayerInventory(myHero);
			PlayerInventory inv = myHero.getMyInventory();
			while(MAIN_GUI.getBackpackGui().getMyActiveHealthPotions() < inv.getItemCount(ItemType.HEALTH_POTION)) {
				MAIN_GUI.getBackpackGui().addPotionToBackpack(ItemType.HEALTH_POTION.toString());
				playerConsole.append(myUserName).append(" has obtained a Health Potion!\n");
			}
			while(MAIN_GUI.getBackpackGui().getMyActiveVisionPotions() < inv.getItemCount(ItemType.VISION_POTION)) {
				MAIN_GUI.getBackpackGui().addPotionToBackpack(ItemType.VISION_POTION.toString());
				playerConsole.append(myUserName).append(" has obtained a Vision Potion!\n");
			}

			MAIN_GUI.getBackpackGui().refreshGoldValue();
			currentRoom.setEmpty();
			DungeonGUI.setPlayerConsole(playerConsole);
		}else if(currentRoom.getMyType() == RoomType.EXIT) {
			if(myNumDungeonsPassed == 4){
				int input = JOptionPane.showConfirmDialog(null,"You have found all four Pillars of OO!\nYou have escaped the Dungeon!\nPLAY AGAIN?");
				if(input == 0){		//play again
					playAgain();
				}else{		//close the game
					System.exit(0);
				}
			} else if(myNumDungeonsPassed == myCurrentDungeonNum){
				JOptionPane.showMessageDialog(null,"Oh No! Looks like the exit leads to another Dungeon....");
				myCurrentDungeonNum++;
				nextDungeon();
			}
		} else{
			DungeonGUI.setPlayerConsole(playerConsole);
		}

	}


	/**
	 * if the player died
	 */
	protected static void gameOver(){
		int input = JOptionPane.showConfirmDialog(null, "GAME OVER.\nPLAY AGAIN?"); // 0=yes, 1=no, 2=cancel

		if(input == 0){		//play again
			playAgain();
		}else{		//close the game
			System.exit(0);
		}
	}

	/**
	 * asking the user if they want to play again
	 */
	protected static void playAgain(){
		sceneController("menu");
		BackpackGUI.removeAllItems();
		MAIN_GUI.closeMap();
		MAIN_GUI.closeBackPack();
	}

	/**
	 * if the hero won the battle
	 * @param theMonster the monster they defeated
	 */
	protected static void battleWin(Monster theMonster) {
		if (myDungeon.getCurrentRoom().getMyType() == RoomType.BOSS_ROOM) {        //if the user defeated a boss monster, then advance to next dungeon
			myNumDungeonsPassed++;
			myDungeon.getCurrentRoom().setEmpty();
			myDungeon.getCurrentRoom().removeMonster();
			PlayerInventory inv = myHero.getMyInventory();
			Pillar[] pillars = inv.getPillars();
			boolean addedPillar = false;
			while (!addedPillar) {
				PillarType pillarType = Pillar.getRandomPillar();
				boolean duplicate = false;
				for (Pillar value : pillars) {
					if (value.getMY_TYPE() == pillarType) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					Pillar pillar;
					if (pillarType == PillarType.ABSTRACTION) {
						pillar = new PillarOfAbstraction(pillarType);
						MAIN_GUI.getBackpackGui().addPillarToBackpack("abstract", pillar.getMyImage(), pillar);
					} else if (pillarType == PillarType.ENCAPSULATION) {
						pillar = new PillarOfEncapsulation(pillarType);
						MAIN_GUI.getBackpackGui().addPillarToBackpack("encapsulation", pillar.getMyImage(), pillar);
					} else if (pillarType == PillarType.INHERITANCE) {
						pillar = new PillarOfInheritance(pillarType);
						MAIN_GUI.getBackpackGui().addPillarToBackpack("inheritance", pillar.getMyImage(), pillar);
					} else {
						pillar = new PillarOfPolymorphism(pillarType);
						MAIN_GUI.getBackpackGui().addPillarToBackpack("polymorphism", pillar.getMyImage(), pillar);
					}
					inv.addPillar(pillar);

					addedPillar = true;
					JOptionPane.showMessageDialog(null, "Congrats! You have defeated the Boss of " + pillarType + "!\nYou have been awarded the Pillar of " + pillarType + "!\n" +
							"Find the exit to escape!");
					DungeonGUI.resetDungeonImage();
					DungeonGUI.enableButtons();
					DungeonGUI.disableButtons(Dungeon.availableRooms(DungeonGUI.getDungeon()));
					DungeonAdventure.sceneController("dungeon");
				}
			}
		} else {
			if(theMonster.getName().contains("Mimic")){  //adding Gold to backpack if they defeated Mimic
				myHero.setGoldAmount(250);
				refreshBackPackGoldValue();
			}
			myDungeon.getCurrentRoom().setEmpty();
			myDungeon.getCurrentRoom().removeMonster();
			myHero.setGoldAmount(Tools.RANDOM.nextInt(25, 75));
			JOptionPane.showMessageDialog(null, "Congrats! You won the battle!");
			DungeonAdventure.sceneController("dungeon");
			DungeonGUI.setHealthLabel(myHero);
		}
	}

	/**
	 * goes to next dungeon, creates a new dungeon and sets the appropriate labels
	 */
	protected static void nextDungeon(){
		MAIN_GUI.closeMap();
		MAIN_GUI.closeBackPack();
		createDungeon();	//creating new dungeon
		myHero.setHealth(myHero.getMaxHealth()); //setting health back to full
		sceneController("dungeon");
		DungeonGUI.setHealthLabel(myHero);
		DungeonGUI.enableButtons();
		Music.playSFX("switchDungeon");
		checkRoom();
	}

	/**
	 * @return the main GUI instance
	 */
	protected static MainGUI getMainGui() {
		return MAIN_GUI;
	}

	/**
	 * @return the number of dungeons completed
	 */
	protected static int getNumDungeonsPassed() {
		return myNumDungeonsPassed;
	}

	/**
	 * @return the current dungeon number
	 */
	protected static int getCurrentDungeonNum(){
		return myCurrentDungeonNum;
	}



}
