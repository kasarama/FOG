package FunctionLayer;

import PresentationLayer.FlatRoof;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RoofSizingTest {

    Construction con = new Construction();
    RoofSizing rs = new RoofSizing(con);
    Roof roof;

    @Before
    public void setUp() throws Exception {
        //Arrange
        con.setConstructionWidth();
        con.getConstructionLength();
    }

    //TODO - Husk Negative Test

    @Test
    public void roofFlatHeight() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofFlat(0,con.getConstructionLength(), con.getConstructionWidth(), 3);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofHeight(con.getRoof().getIsPitched(), con.getConstructionLength(), con.getConstructionWidth());
        int expected = 272;
        //Assert
        assertEquals(expected, actuel);

    }

    @Test
    public void roofPitchedHeight() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofPitched(0, con.getConstructionLength(), con.getConstructionWidth(), 30);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofHeight(con.getRoof().getIsPitched(), con.getConstructionLength(), con.getConstructionWidth());
        int expected = 1039;
        //Assert
        assertEquals(expected, actuel);

    }

    //Er denne nødvendig?
    @Test
    public void roofWidthSurfaceFlatRoof() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofFlat(0,con.getConstructionLength(), con.getConstructionWidth(), 3);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofWidthSurface();
        int expected = 3600;
        //Assert
        assertEquals(expected, actuel);
    }


    @Test
    public void roofWidthSurfacePitchedRoof() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofPitched(0, con.getConstructionLength(), con.getConstructionWidth(), 30);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofWidthSurface();
        int expected = 2078;
        //Assert
        assertEquals(expected, actuel);
    }


    @Test
    public void roofLengthSurfaceFlatRoof() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofFlat(0,con.getConstructionLength(), con.getConstructionWidth(), 3);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofLengthSurface();
        int expected = 7804;
        //Assert
        assertEquals(expected, actuel);
    }

    //Er denne nødvendig?
    @Test
    public void roofLengthSurfacePitchedRoof() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofPitched(0, con.getConstructionLength(), con.getConstructionWidth(), 30);
        con.setRoof(roof);
        //Act
        int actuel = rs.roofLengthSurface();
        int expected = 7800;
        //Assert
        assertEquals(expected, actuel);
    }

    @Test
    public void flatRoofCalcutatedLength() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofFlat(0,con.getConstructionLength(), con.getConstructionWidth(), 3);
        con.setRoof(roof);
        //Act
        int actuel = rs.flatRoofCalcutatedLength();
        int expected = 7804;
        //Assert
        assertEquals(expected, actuel);
    }

    @Test
    public void pitchedRoofCalcutatedWidth() {
        //Arrange
        con.setCarportLength(7800);
        con.setCarportWidth(3600);
        roof = new RoofPitched(0, con.getConstructionLength(), con.getConstructionWidth(), 30);
        con.setRoof(roof);
        //Act
        int actuel = rs.pitchedRoofCalcutatedWidth();
        int expected = 2078;
        //Assert
        assertEquals(expected, actuel);
    }
}
