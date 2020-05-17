package DBAccess;

import junit.framework.TestCase;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterialMapperTest extends TestCase {


    private static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "1909145380Hanna";
    private static String DBNAME = "fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public void setUp() throws Exception {
        try {
            // awoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                // Make mappers use test
                Connector.setConnection( testConnection );
            }
            // reset test database
            try ( Statement stmt = testConnection.createStatement() ) {
                stmt.execute( "drop table if exists Users" );
                stmt.execute( "create table Users like UsersTest" );
                stmt.execute( "insert into Users select * from UsersTest" );
            }

        } catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }
}