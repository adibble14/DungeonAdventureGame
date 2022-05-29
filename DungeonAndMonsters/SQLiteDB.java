import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class SQLiteDB {

    static SQLiteDataSource ds = new SQLiteDataSource();
    static Random random = new Random();
    static int num = 0;

    public static void main(String[] args){   //TODO delete
        createMonstersTable();
        createHeroesTable();
    }

    public static void createMonstersTable(){
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
                            "MAX_HEAL INT NOT NULL)";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {              //these variables will be forgotten after the try block, consider making them instance variables or local
              stmt.executeUpdate( createQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        int count = getRowCount("monsters");  //checks to see if the rows have already been created

        if(count < 20) {
            String gremlinQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Gremlin','Gizmo', 1,70, 5, 30, 15, .8, .4, 20, 40)";
            String gremlinQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Gremlin','Mogwai',2, 70, 5, 30, 15, .8, .4, 20, 40)";
            String gremlinQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Gremlin','Stripe',3, 70, 5, 30, 15, .8, .4, 20, 40)";
            String gremlinQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Gremlin','Greta', 4,70, 5, 30, 15, .8, .4, 20, 40)";
            String gremlinQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Gremlin','Daffy', 5,70, 5, 30, 15, .8, .4, 20, 40)";


            String beastQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Beast','Ravager', 1,500, 1, 50, 30, .3, .2, 50, 100)";
            String beastQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Beast','Owl Bear', 2,500, 1, 50, 30, .3, .2, 50, 100)";
            String beastQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Beast','Beholder', 3,500, 1, 50, 30, .3, .2, 50, 100)";
            String beastQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Beast','plankTon', 4,500, 1, 50, 30, .3, .2, 50, 100)";
            String beastQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Beast','Man Beast', 5,500, 1, 50, 30, .3, .2, 50, 100)";


            String ogreQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Ogre','Huge Ogre',1, 200, 2, 60, 30, .6, .1, 30, 60)";
            String ogreQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Ogre','Green Ogre',2, 200, 2, 60, 30, .6, .1, 30, 60)";
            String ogreQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Ogre','Passive Ogre',3, 200, 2, 60, 30, .6, .1, 30, 60)";
            String ogreQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Ogre','Strong Ogre', 4,200, 2, 60, 30, .6, .1, 30, 60)";
            String ogreQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Ogre','Mysterious Ogre',5, 200, 2, 60, 30, .6, .1, 30, 60)";


            String skeletonQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Skeleton','Skeletor',1, 100, 3, 50, 30, .8, .3, 40, 80)";
            String skeletonQuery2 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Skeleton','Funny Bones',2, 100, 3, 50, 30, .8, .3, 40, 80)";
            String skeletonQuery3 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Skeleton','Jack Skellington',3, 100, 3, 50, 30, .8, .3, 40, 80)";
            String skeletonQuery4 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Skeleton','Rattles', 4,100, 3, 50, 30, .8, .3, 40, 80)";
            String skeletonQuery5 = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, NUM, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)" +
                    "VALUES ('Skeleton','Bone Thug',5, 100, 3, 50, 30, .8, .3, 40, 80)";


            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();) {
                stmt.executeUpdate(gremlinQuery);stmt.executeUpdate(gremlinQuery2);stmt.executeUpdate(gremlinQuery3);stmt.executeUpdate(gremlinQuery4);stmt.executeUpdate(gremlinQuery5);
                stmt.executeUpdate(beastQuery);stmt.executeUpdate(beastQuery2);stmt.executeUpdate(beastQuery3);stmt.executeUpdate(beastQuery4);stmt.executeUpdate(beastQuery5);
                stmt.executeUpdate(ogreQuery);stmt.executeUpdate(ogreQuery2);stmt.executeUpdate(ogreQuery3);stmt.executeUpdate(ogreQuery4);stmt.executeUpdate(ogreQuery5);
                stmt.executeUpdate(skeletonQuery);stmt.executeUpdate(skeletonQuery2);stmt.executeUpdate(skeletonQuery3);stmt.executeUpdate(skeletonQuery4);stmt.executeUpdate(skeletonQuery5);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }



    public static void createHeroesTable(){
        ds.setUrl("jdbc:sqlite:characters.db");

        String createQuery = "CREATE TABLE IF NOT EXISTS heroes ("+
                "HERO_TYPE TEXT NOT NULL," +
                "HEALTH INT NOT NULL,"+
                "ATTACK_SPEED INT NOT NULL,"+
                "MAX_DAMAGE INT NOT NULL,"+
                "MIN_DAMAGE INT NOT NULL,"+
                "ACCURACY DECIMAL NOT NULL,"+
                "BLOCK_CHANCE DECIMAL NOT NULL)";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {              //these variables will be forgotten after the try block, consider making them instance variables or local
            stmt.executeUpdate( createQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        int count = getRowCount("heroes"); //checks to see if the rows have already been created

        if(count < 5) {
            String archerQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)" +
                    "VALUES ('Archer', 500, 4, 30, 25, .7, .5)";
            String mageQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)" +
                    "VALUES ('Mage', 75, 4, 80, 50, .7, .3)";
            String warriorQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)" +
                    "VALUES ('Warrior', 125, 3, 50, 30, .8, .6)";
            String priestessQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)" +
                    "VALUES ('Priestess', 75, 5, 45, 25, .7, .3)";
            String thiefQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)" +
                    "VALUES ('Thief', 95, 6, 20, 10, .9, .4)";


            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();) {
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

    public static int getRowCount(String tableName){
        ds.setUrl("jdbc:sqlite:characters.db");
        num = random.nextInt(1,6);
        String countQuery = "SELECT COUNT(*) FROM " + tableName;
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(countQuery);
            return rs.getInt("COUNT(*)");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static String getCharacterName(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        num = random.nextInt(1,6);
        String getQuery = "SELECT MONSTER_NAME" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getString("MONSTER_NAME");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return null;
    }

    public static int getCharacterHealth(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "";
        if(theTable == "monsters"){
            getQuery = "SELECT HEALTH" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT HEALTH" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
                ResultSet rs = stmt.executeQuery(getQuery);
                return rs.getInt("HEALTH");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static int getCharacterSpeed(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "";
        if(theTable == "monsters"){
            getQuery = "SELECT ATTACK_SPEED" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT ATTACK_SPEED" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("ATTACK_SPEED");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static int getCharacterMaxDamage(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "";
        if(theTable == "monsters"){
            getQuery = "SELECT MAX_DAMAGE" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT MAX_DAMAGE" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MAX_DAMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static int getCharacterMinDamage(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "";
        if(theTable == "monsters"){
            getQuery = "SELECT MIN_DAMAGE" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT MIN_DAMAGE" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MIN_DAMAGE");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static double getCharacterAccuracy(String theCharacterType, String theTable){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "";
        if(theTable == "monsters"){
            getQuery = "SELECT ACCURACY" +
                    " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        }else{
            getQuery = "SELECT ACCURACY" +
                    " FROM "+theTable+ " WHERE HERO_TYPE = '" + theCharacterType+"'";
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getDouble("ACCURACY");

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return 0;
    }

    public static double getCharacterHealChance(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT HEAL_CHANCE" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getDouble("HEAL_CHANCE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static int getCharacterMinHeal(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MIN_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MIN_HEAL");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static int getCharacterMaxHeal(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MAX_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theCharacterType+"' AND NUM = "+num;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MAX_HEAL");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static double getCharacterBlockChange(String theCharacterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT BLOCK_CHANCE" +
                " FROM heroes WHERE HERO_TYPE = '" + theCharacterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getDouble("BLOCK_CHANCE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }




}
