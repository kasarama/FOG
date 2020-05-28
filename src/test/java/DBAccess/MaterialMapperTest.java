package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterialMapperTest {


    private static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "12345ROOT!";
    private static String DBNAME = "fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void setUp() {
        try {
            // awoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
                System.out.println(url);
            }


        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Before
    public void beforeEachTest() {
        // reset test database
        System.out.println("beforeEach");
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists materials");
            stmt.execute("create table materials like fogdb.materials");
            stmt.execute("insert into materials values " +
                    "(1, 'dog', 15,5,'pk.','','animal',15.50,'',8.5)," +
                    "(2, 'Magda', 500,200,'stk','hungry','human',0.01,'magda.jpg',1)," +
                    "(3, 'cat',100,200,'stk','mew','animal',123.95,'',10)," +
                    "(4, 'bread',150,100,'pk.','fresh','animal',503,'',1)," +
                    "(5,'plank',20,18,'m','get','roof',15.95,'',5.7),"+
                    "(6,'loop',20,18,'m','get','roof',null,'',5.7)");
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
       // beforeEachTest();
        // double result = MaterialMapper.spending("15X95 MM FYR PANEL ROYAL");
        double result = MaterialMapper.spending("Magda");
        double exp = 1;
        assertEquals(exp, result, 0.01);
    }

    @Test
    public void testGetWidthByName() throws LoginSampleException {
        int result=MaterialMapper.getWidthByName("dog");
        int exp = 15;
        assertEquals(exp,result);
    }


    @Test (expected = LoginSampleException.class)
    public void testSetPriceFromDBNEGATIV () throws LoginSampleException {
        Material material = new Material();
        material.setName("pool");
        MaterialMapper.setPriceFromDB(material);
        System.out.println("result:" +material.getPrice());
    }

    @Test (expected = LoginSampleException.class)
    public void testSetPriceFromDBNEGATIV2 () throws LoginSampleException {
        Material material = new Material();
        material.setName("loop");
        MaterialMapper.setPriceFromDB(material);
        System.out.println("result:" +material.getPrice());
    }



}
