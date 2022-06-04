/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Random;

/**
 * class that creates a SQLite database that contains the hero and monster's stats
 */
public class SQLiteDB {

    /**
     * data source connection
     */
    static SQLiteDataSource ds = new SQLiteDataSource();

    /**
     * random int
     */
    static Random random = new Random();
    static int num = random.nextInt(1,6);

    /**
     * creates the monsters table
     */
     static void createMonstersTable(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:characters.db");

        String createQuery = "CREATE TABLE IF NOT EXISTS monsters ("+
                            "MONSTER_TYPE TEXT NOT NULL," +
                            "MONSTER_NAME TEXT NOT NULL," +
                            "NUM INT NOT NULL,"+
                            "HEALTH INT NOT NULL,"+
                            "ATTACK_SPEED INT NOT NULL,"+
                            "MAX_DAMAGE INT NOT NULL,"+
                            "MIN_DAMAGE INT NOT NULL,"+
                            "ACCURACY DECIMAL NOT NULL,"+
                            "HEAL_CHANCE DECIMAL NOT NULL,"+
                            "MIN_HEAL INT NOT NULL,"+
                            "MAX_HEAL INT NOT NULL," +
                            "IMAGE TEXT)";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement()) {              //these variables will be forgotten after the try block, consider making them instance variables or local
              stmt.executeUpdate( createQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        int count = getRowCount("monsters");  //checks to see if the rows have already been created

        if(count < 20) {
            String gremlinQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Gremlin','Gizmo', 1,70, 5, 30, 15, .8, .4, 20, 40, 'DungeonAndMonsters/monster pics/GremlinInGame.png')";
            String gremlinQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Gremlin','Mogwai',2, 70, 5, 30, 15, .8, .4, 20, 40, 'DungeonAndMonsters/monster pics/GremlinInGame.png')";
            String gremlinQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Gremlin','Stripe',3, 70, 5, 30, 15, .8, .4, 20, 40, 'DungeonAndMonsters/monster pics/GremlinInGame.png')";
            String gremlinQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Gremlin','Greta', 4,70, 5, 30, 15, .8, .4, 20, 40, 'DungeonAndMonsters/monster pics/GremlinInGame.png')";
            String gremlinQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Gremlin','Daffy', 5,70, 5, 30, 15, .8, .4, 20, 40, 'DungeonAndMonsters/monster pics/GremlinInGame.png')";


            String beastQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Beast','Ravager', 1,450, 1, 50, 30, .3, .2, 50, 100, 'DungeonAndMonsters/monster pics/Beast.png')";
            String beastQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Beast','Owl Bear', 2,450, 1, 50, 30, .3, .2, 50, 100, 'DungeonAndMonsters/monster pics/Beast.png')";
            String beastQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Beast','Beholder', 3,450, 1, 50, 30, .3, .2, 50, 100, 'DungeonAndMonsters/monster pics/Beast.png')";
            String beastQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Beast','plankTon', 4,450, 1, 50, 30, .3, .2, 50, 100, 'DungeonAndMonsters/monster pics/Beast.png')";
            String beastQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Beast','Man Beast', 5,450, 1, 50, 30, .3, .2, 50, 100, 'DungeonAndMonsters/monster pics/Beast.png')";


            String ogreQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Ogre','Huge Ogre',1, 175, 2, 60, 30, .6, .1, 30, 60,'DungeonAndMonsters/monster pics/Ogre.png')";
            String ogreQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Ogre','Green Ogre',2, 175, 2, 60, 30, .6, .1, 30, 60,'DungeonAndMonsters/monster pics/Ogre.png')";
            String ogreQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Ogre','Passive Ogre',3, 175, 2, 60, 30, .6, .1, 30, 60,'DungeonAndMonsters/monster pics/Ogre.png')";
            String ogreQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Ogre','Strong Ogre', 4,175, 2, 60, 30, .6, .1, 30, 60,'DungeonAndMonsters/monster pics/Ogre.png')";
            String ogreQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Ogre','Mysterious Ogre',5, 175, 2, 60, 30, .6, .1, 30, 60,'DungeonAndMonsters/monster pics/Ogre.png')";


            String skeletonQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Skeleton','Skeletor',1, 100, 3, 50, 30, .8, .3, 40, 80, 'DungeonAndMonsters/monster pics/rpgCritterSkelly.png')";
            String skeletonQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Skeleton','Funny Bones',2, 100, 3, 50, 30, .8, .3, 40, 80, 'DungeonAndMonsters/monster pics/rpgCritterSkelly.png')";
            String skeletonQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Skeleton','Jack Skellington',3, 100, 3, 50, 30, .8, .3, 40, 80, 'DungeonAndMonsters/monster pics/rpgCritterSkelly.png')";
            String skeletonQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Skeleton','Rattles', 4,100, 3, 50, 30, .8, .3, 40, 80, 'DungeonAndMonsters/monster pics/rpgCritterSkelly.png')";
            String skeletonQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Skeleton','Bone Thug',5, 100, 3, 50, 30, .8, .3, 40, 80, 'DungeonAndMonsters/monster pics/rpgCritterSkelly.png')";

            String mimicQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Mimic','Surprise Mimic',1, 140, 5, 15, 5, .5, .07, 0, 0, 'DungeonAndMonsters/monster pics/Mimic UW Chest.png')";
            String mimicQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Mimic','Gift Mimic',2, 140, 5, 15, 5, .5, .07, 0, 0, 'DungeonAndMonsters/monster pics/Mimic UW Chest.png')";
            String mimicQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Mimic','Happy Mimic',3, 140, 5, 15, 5, .5, .07, 0, 0, 'DungeonAndMonsters/monster pics/Mimic UW Chest.png')";
            String mimicQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Mimic','Shock Mimic',4, 140, 5, 15, 5, .5, .07, 0, 0, 'DungeonAndMonsters/monster pics/Mimic UW Chest.png')";
            String mimicQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL, IMAGE)" +
                    "VALUES ('Mimic','Startling Mimic',5, 140, 5, 15, 5, .5, .07, 0, 0, 'DungeonAndMonsters/monster pics/Mimic UW Chest.png')";


            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(gremlinQuery);stmt.executeUpdate(gremlinQuery2);stmt.executeUpdate(gremlinQuery3);stmt.executeUpdate(gremlinQuery4);stmt.executeUpdate(gremlinQuery5);
                stmt.executeUpdate(beastQuery);stmt.executeUpdate(beastQuery2);stmt.executeUpdate(beastQuery3);stmt.executeUpdate(beastQuery4);stmt.executeUpdate(beastQuery5);
                stmt.executeUpdate(ogreQuery);stmt.executeUpdate(ogreQuery2);stmt.executeUpdate(ogreQuery3);stmt.executeUpdate(ogreQuery4);stmt.executeUpdate(ogreQuery5);
                stmt.executeUpdate(skeletonQuery);stmt.executeUpdate(skeletonQuery2);stmt.executeUpdate(skeletonQuery3);stmt.executeUpdate(skeletonQuery4);stmt.executeUpdate(skeletonQuery5);
                stmt.executeUpdate(mimicQuery);stmt.executeUpdate(mimicQuery2);stmt.executeUpdate(mimicQuery3);stmt.executeUpdate(mimicQuery4);stmt.executeUpdate(mimicQuery5);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }


    /**
     * creates the heroes table
     */
     static void createHeroesTable(){
        ds.setUrl("jdbc:sqlite:characters.db");

        String createQuery = "CREATE TABLE IF NOT EXISTS heroes ("+
                "HERO_TYPE TEXT NOT NULL," +
                "HEALTH INT NOT NULL,"+
                "ATTACK_SPEED INT NOT NULL,"+
                "MAX_DAMAGE INT NOT NULL,"+
                "MIN_DAMAGE INT NOT NULL,"+
                "ACCURACY DECIMAL NOT NULL,"+
                "BLOCK_CHANCE DECIMAL NOT NULL," +
                "IMAGE TEXT NOT NULL,"+
                "IN_GAME_IMAGE TEXT)";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {              //these variables will be forgotten after the try block, consider making them instance variables or local
            stmt.executeUpdate( createQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        int count = getRowCount("heroes"); //checks to see if the rows have already been created

        if(count < 5) {
            String archerQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE, IMAGE, IN_GAME_IMAGE)" +
                    "VALUES ('Archer', 125, 4, 30, 25, .7, .5, 'DungeonAndMonsters/character pics/archerpixel.png','DungeonAndMonsters/character pics/archerInGame.png')";
            String mageQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE, IMAGE, IN_GAME_IMAGE)" +
                    "VALUES ('Mage', 90, 4, 80, 50, .7, .3, 'DungeonAndMonsters/character pics/MageFaceSprite.png', 'DungeonAndMonsters/character pics/MageInGameSprite.png')";
            String warriorQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE, IMAGE, IN_GAME_IMAGE)" +
                    "VALUES ('Warrior', 125, 3, 50, 30, .8, .6,'DungeonAndMonsters/character pics/WarriorPixel.png','DungeonAndMonsters/character pics/WarriorInGame.png')";
            String priestessQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE, IMAGE, IN_GAME_IMAGE)" +
                    "VALUES ('Priestess', 75, 5, 45, 25, .7, .3,'DungeonAndMonsters/character pics/PriestessFace.png', 'DungeonAndMonsters/character pics/PriestessInGame.png')";
            String thiefQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE, IMAGE, IN_GAME_IMAGE)" +
                    "VALUES ('Thief', 115, 6, 30, 15, .9, .4,'DungeonAndMonsters/character pics/thiefpixel.png','DungeonAndMonsters/character pics/goblinthief.png')";


            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(archerQuery);
                stmt.executeUpdate(mageQuery);
                stmt.executeUpdate(warriorQuery);
                stmt.executeUpdate(priestessQuery);
                stmt.executeUpdate(thiefQuery);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    /**
     * returns how many rows there are in a table
     * @param tableName the name of the table
     * @return row count
     */
     static int getRowCount(String tableName){
        ds.setUrl("jdbc:sqlite:characters.db");
        String countQuery = "SELECT COUNT(*) FROM " + tableName;
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(countQuery);
            return rs.getInt("COUNT(*)");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @return name of character
     */
     static String getCharacterName(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MONSTER_NAME" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getString("MONSTER_NAME");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return null;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character health
     */
     static int getCharacterHealth(String theCharacterType, String theTable){ return getCharacterHealth(theCharacterType, theTable, "");}
     static int getCharacterHealth(String theCharacterType, String theTable, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT HEALTH" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT HEALTH" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
                ResultSet rs = stmt.executeQuery(getQuery);
                if(Objects.equals(theType, "boss"))
                    return rs.getInt("HEALTH") + 50;
                else
                    return rs.getInt("HEALTH");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character speed
     */
     static int getCharacterSpeed(String theCharacterType, String theTable){return getCharacterSpeed(theCharacterType, theTable, "");}
     static int getCharacterSpeed(String theCharacterType, String theTable, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT ATTACK_SPEED" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT ATTACK_SPEED" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            if(Objects.equals(theType, "boss"))
                return rs.getInt("ATTACK_SPEED")+1;
            else
                return rs.getInt("ATTACK_SPEED");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character max damage
     */
     static int getCharacterMaxDamage(String theCharacterType, String theTable){return getCharacterMaxDamage(theCharacterType, theTable,"");}
     static int getCharacterMaxDamage(String theCharacterType, String theTable, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT MAX_DAMAGE" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT MAX_DAMAGE" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            if(Objects.equals(theType, "boss"))
                return rs.getInt("MAX_DAMAGE") + 15;
            else
                return rs.getInt("MAX_DAMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character min damage
     */
     static int getCharacterMinDamage(String theCharacterType, String theTable){return getCharacterMinDamage(theCharacterType, theTable,"");}
     static int getCharacterMinDamage(String theCharacterType, String theTable, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT MIN_DAMAGE" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT MIN_DAMAGE" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);

            if(Objects.equals(theType, "boss"))
                return rs.getInt("MIN_DAMAGE") + 15;
            else
                return rs.getInt("MIN_DAMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character accuracy
     */
     static double getCharacterAccuracy(String theCharacterType, String theTable){return getCharacterAccuracy(theCharacterType, theTable, "");}
     static double getCharacterAccuracy(String theCharacterType, String theTable, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT ACCURACY" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT ACCURACY" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);

            if(Objects.equals(theType, "boss"))
                return rs.getDouble("ACCURACY") + 0.1;
            else
                return rs.getDouble("ACCURACY");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @return the heal chance for a certain monster
     */
     static double getCharacterHealChance(String theCharacterType){return getCharacterHealChance(theCharacterType, "");}
     static double getCharacterHealChance(String theCharacterType, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT HEAL_CHANCE" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getQuery);

            if(Objects.equals(theType, "boss"))
                return rs.getDouble("HEAL_CHANCE")+0.1;
            else
                return rs.getDouble("HEAL_CHANCE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @return the monster min heal
     */
     static int getCharacterMinHeal(String theCharacterType){return getCharacterMinHeal(theCharacterType, "");}
     static int getCharacterMinHeal(String theCharacterType, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MIN_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getQuery);

            if(Objects.equals(theType, "boss"))
                return rs.getInt("MIN_HEAL") + 15;
            else
                return rs.getInt("MIN_HEAL");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @return the monster max heal
     */
     static int getCharacterMaxHeal(String theCharacterType){return getCharacterMaxHeal(theCharacterType, "");}
     static int getCharacterMaxHeal(String theCharacterType, String theType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MAX_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getQuery);

            if(Objects.equals(theType, "boss"))
                return rs.getInt("MAX_HEAL") + 15;
            else
                return rs.getInt("MAX_HEAL");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @return the hero block chance
     */
     static double getCharacterBlockChance(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT BLOCK_CHANCE" +
                " FROM heroes WHERE HERO_TYPE = '" + theCharacterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getDouble("BLOCK_CHANCE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    /**
     * @param theCharacterType type of character
     * @param theTable which table to query
     * @return the character image
     */
     static String getCharacterImage(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery;
        if(Objects.equals(theTable, "monsters")){
            getQuery = "SELECT IMAGE" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT IMAGE" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getString("IMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return null;
    }

    /**
     * @param theCharacterType type of character
     * @return the hero in game image
     */
     static String getCharacterInGameImage(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT IN_GAME_IMAGE" +
                " FROM heroes WHERE HERO_TYPE = '" + theCharacterType+"'";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getString("IN_GAME_IMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return null;
    }




}
