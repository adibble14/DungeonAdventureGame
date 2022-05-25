import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLiteDB {

    static SQLiteDataSource ds = new SQLiteDataSource();

    /*public static void main(String[] args){
       //createMonstersTable();
        createHeroesTable();
    }*/

    public static void createMonstersTable(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:characters.db");

        //TODO add images somehow
        String createQuery = "CREATE TABLE IF NOT EXISTS monsters ("+
                            "MONSTER_TYPE TEXT NOT NULL," +
                            "MONSTER_NAME TEXT NOT NULL,"+
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

        String deleteQuery = "DELETE FROM monsters";        //resetting the columns in monster
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            stmt.executeUpdate( deleteQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        //TODO add more monsters of same type with different names???
        String gremlinQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)"+
                            "VALUES ('Gremlin','Gizmo', 70, 5, 30, 15, .8, .4, 20, 40)";
        String beastQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)"+
                "VALUES ('Beast','Ravager', 500, 1, 50, 30, .3, .2, 50, 100)";
        String ogreQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)"+
                "VALUES ('Ogre','Huge Ogre', 200, 2, 60, 30, .6, .1, 30, 60)";
        String skeletonQuery = "INSERT INTO monsters (MONSTER_TYPE, MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)"+
                "VALUES ('Skeleton','Jack Skellington', 100, 3, 50, 30, .8, .3, 40, 80)";
        //TODO add other monsters

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            stmt.executeUpdate( gremlinQuery );
            stmt.executeUpdate( beastQuery );
            stmt.executeUpdate( ogreQuery );
            stmt.executeUpdate( skeletonQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
    /*public static void createHeroesTable(){
        ds.setUrl("jdbc:sqlite:characters.db");

        //TODO add images somehow
        String createQuery = "CREATE TABLE IF NOT EXISTS heroes ("+
                "HERO_TYPE TEXT NOT NULL," +
                "HEALTH INT NOT NULL,"+
                "ATTACK_SPEED INT NOT NULL,"+
                "MAX_DAMAGE INT NOT NULL,"+
                "MIN_DAMAGE INT NOT NULL,"+
                "ACCURACY DECIMAL NOT NULL,"+
                "BLOCK_CHANCE DECIMAL NOT NULL";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {              //these variables will be forgotten after the try block, consider making them instance variables or local
            stmt.executeUpdate( createQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        String deleteQuery = "DELETE FROM heroes";        //resetting the columns in monster
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            stmt.executeUpdate( deleteQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        String archerQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)"+
                "VALUES ('Archer', 500, 4, 30, 25, .7, .5)";
        String mageQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)"+
                "VALUES ('Mage', 75, 4, 80, 50, .7, .3)";
        String warriorQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)"+
                "VALUES ('Warrior', 125, 3, 50, 30, .8, .6)";
        String priestessQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)"+
                "VALUES ('Priestess', 75, 5, 45, 25, .7, .3)";
        String thiefQuery = "INSERT INTO heroes (HERO_TYPE, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, BLOCK_CHANCE)"+
                "VALUES ('Thief', 95, 6, 20, 10, .9, .4)";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            stmt.executeUpdate( archerQuery );
            stmt.executeUpdate( mageQuery );
            stmt.executeUpdate( warriorQuery );
            stmt.executeUpdate( priestessQuery );
            stmt.executeUpdate( thiefQuery );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }*/

    public static String getMonsterName(String theMonsterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MONSTER_NAME" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
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

    public static int getMonsterHealth(String theMonsterType){
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT HEALTH" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
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

    public static int getMonsterSpeed(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT ATTACK_SPEED" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("ATTACK_SPEED");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static int getMonsterMaxDamage(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MAX_DAMAGE" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MAX_DAMAGE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static int getMonsterMinDamage(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MIN_DAMAGE" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getInt("MIN_DAMAGE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static double getMonsterAccuracy(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT ACCURACY" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(getQuery);
            return rs.getDouble("ACCURACY");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static double getMonsterHealChance(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT HEAL_CHANCE" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
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

    public static int getMonsterMinHeal(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MIN_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
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

    public static int getMonsterMaxHeal(String theMonsterType) {
        ds.setUrl("jdbc:sqlite:characters.db");
        String getQuery = "SELECT MAX_HEAL" +
                " FROM monsters WHERE MONSTER_TYPE = '" + theMonsterType+"'";
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




}