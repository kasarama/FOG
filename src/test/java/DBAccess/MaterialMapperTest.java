package DBAccess;

import FunctionLayer.LoginSampleException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void setUp() {
        try {
            // awoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
            }


        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Before
    public void beforeEachTest() {
        // reset test database
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists materials");
            stmt.execute("create table materials like fogDB.materials");
            stmt.execute("insert into materials values " +
                    "(1, 'dog', 15,5,'pk.','','animal',15.50,'',8.5)," +
                    "(2, 'Magda', 500,200,'stk','hungry','human',0.01,'magda.jpg',1)," +
                    "(3, 'cat',100,200,'stk','mew','animal',123.95,'',10)," +
                    "(4, 'bread',150,100,'pk.','fresh','animal',503,'',1)," +
                    "(5,'plank',20,18,'m','get','roof',15.95,'',5.7)");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database. " + ex.getMessage());
        }
    }
    @Test
    public void testConnection() {
        assertNotNull(testConnection);
    }

    @Test
    public void testSpending() throws LoginSampleException {

        double result=MaterialMapper.spending("Magda");
        double exp=1;
        assertEquals(exp,result,0.01);
    }

    public void testGetWidthByName() {
    }

    public void testGetPackageSize() {
    }

    public void testSetUnitFromDB() {
    }

    public void testSetPriceFromDB() {
    }

    public void testSetID() {
    }
}
