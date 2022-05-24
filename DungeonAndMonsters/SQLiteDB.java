import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;


public class SQLiteDB {

    public static void main(String[] args){
        createMonstersTable();
    }

    public static void createMonstersTable(){
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        //TODO add images somehow
        String createQuery = "CREATE TABLE IF NOT EXISTS monsters ("+
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
              Statement stmt = conn.createStatement(); ) {              //these variables will be forgot after the try block, consider making them instance variables or local
            int rv = stmt.executeUpdate( createQuery );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        //TODO add more monsters of same type with different names???
        String gremlinQuery = "INSERT INTO monsters (MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)"+
                            "VALUES ('Gizmo', 70, 5, 30, 15, .8, .4, 20, 40)";
        //TODO add other monsters

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( gremlinQuery );
            System.out.println( "1st executeUpdate() returned " + rv );

            /*rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );*/
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        System.out.println( "Selecting all rows from test table" );
        String query = "SELECT * FROM monsters";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                //(MONSTER_NAME, HEALTH, ATTACK_SPEED, MAX_DAMAGE, MIN_DAMAGE, ACCURACY, HEAL_CHANCE, MIN_HEAL, MAX_HEAL)
                String name = rs.getString("MONSTER_NAME");
                String health = rs.getString("HEALTH");
                String speed = rs.getString("ATTACK_SPEED");
                String maxDamage = rs.getString("MAX_DAMAGE");
                String minDamage = rs.getString("MIN_DAMAGE");
                String accuracy = rs.getString("ACCURACY");
                String healChance = rs.getString("HEAL_CHANCE");
                String minHeal = rs.getString("MIN_HEAL");
                String maxHeal = rs.getString("MAX_HEAL");

                System.out.println("Result: " + name + " " +  health + " " +speed +" " +maxDamage + " " +minDamage + " " +accuracy + " " +healChance +" " + minHeal +" " + maxHeal);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
