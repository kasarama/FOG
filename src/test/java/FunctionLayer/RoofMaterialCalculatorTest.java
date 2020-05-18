package FunctionLayer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class RoofMaterialCalculatorTest {
    Construction construction = new Construction();
    RoofMaterialCalculator rmc;
    Roof roof;
    Shed shed;

    @Before
    public void setUp() throws Exception {
        //Arrange
        roof = new RoofFlat(0, construction.getConstructionLength(), construction.getConstructionWidth(), 3);
        shed = new Shed(0,0,"");
    }

    @Test
    public void quantityOfT600ForRoofFlatRoof() {
        //Arrange
        construction.setShed(shed);
        construction.setCarportLength(6000);
        construction.setCarportWidth(3000);
        construction.setConstructionWidth();
        construction.setConstructionLength();
        RoofSizing rs = new RoofSizing(construction);
        rmc = new RoofMaterialCalculator(construction);
        construction.getRoof().setHeight(rs.roofHeight(construction.getRoof().getIsPitched(), construction.getConstructionLength(), construction.getConstructionWidth()));
        //Act
        int actual = rmc.quantityOfT600ForRoof(1090);
        int expected = 3;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void quantityOfT600ForRoofFlatRoofThirdCoincidenceSquareWithoutT300() {
        //Arrange
        construction.setCarportLength(4500);
        construction.setCarportWidth(3500);
        construction.setConstructionWidth();
        construction.setConstructionLength();
        RoofSizing rs = new RoofSizing(construction);
        rmc = new RoofMaterialCalculator(construction);
        construction.getRoof().setHeight(rs.roofHeight(construction.getRoof().getIsPitched(), construction.getConstructionLength(), construction.getConstructionWidth()));
        //Act
        int actual = rmc.quantityOfT600ForRoof(1090);
        int expected = 4;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void quantityOfT600ForRoofFlatRoofThirdCoincidenceSquareWithT300() {
        //Arrange
        construction.setCarportLength(8000);
        construction.setCarportWidth(3500);
        construction.setConstructionWidth();
        construction.setConstructionLength();
        construction.setRoof(roof);
        RoofSizing rs = new RoofSizing(construction);
        rmc = new RoofMaterialCalculator(construction);
        construction.getRoof().setHeight(rs.roofHeight(construction.getRoof().getIsPitched(), construction.getConstructionLength(), construction.getConstructionWidth()));
        //Act
        int actual = rmc.quantityOfT600ForRoof(1090);
        int expected = 4;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void quantityOfT300ForRoofFlatRoofAreNone() {
        //Arrange
        construction.setCarportWidth(4500);
        construction.setCarportLength(3500);
        construction.setConstructionWidth();
        construction.setConstructionLength();
        construction.setRoof(roof);
        RoofSizing rs = new RoofSizing(construction);
        rmc = new RoofMaterialCalculator(construction);
        construction.getRoof().setHeight(rs.roofHeight(construction.getRoof().getIsPitched(), construction.getConstructionLength(), construction.getConstructionWidth()));
        //Act
        int actual = rmc.quantityOfT300ForRoof(1090);
        int expected = 0;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void quantityOfT300ForRoofFlatRoofIsMoreThanZero() {
        //Arrange


        construction.setCarportLength(8000);
        construction.setCarportWidth(3500);
        construction.setConstructionWidth();
        construction.setConstructionLength();
        construction.setRoof(roof);
        RoofSizing rs = new RoofSizing(construction);
        rmc = new RoofMaterialCalculator(construction);
        construction.getRoof().setHeight(rs.roofHeight(construction.getRoof().getIsPitched(), construction.getConstructionLength(), construction.getConstructionWidth()));
        //Act
        int actual = rmc.quantityOfT300ForRoof(1090);
        int expected = 4;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void flatRoofMaterialsInsert() throws LoginSampleException {
        //Arrange
        RoofMaterialCalculator rf = new RoofMaterialCalculator(construction);
        //Act
        ArrayList<Material> materials = rf.flatRoofMaterialsTrapezPlades("Blåtonet");
        //Assert
        assertNotNull(materials);
    }
}
