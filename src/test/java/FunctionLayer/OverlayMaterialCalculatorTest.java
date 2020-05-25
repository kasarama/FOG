package FunctionLayer;

import DBAccess.Connector;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Magdalena
 */

import java.util.ArrayList;


public class OverlayMaterialCalculatorTest {

    Construction construction = new Construction();
    Shed shed;
    Roof roof;
    Wall a;
    Wall b;
    Wall c;
    Wall d;

    @Before
    public void setUp() throws Exception {
        Connector.setConnection(null);
        construction.setCarportLength(6800);
        construction.setCarportWidth(6200);
        construction.setConstructionHeight(2000);
        shed = new Shed(construction.getCarportWidth()/2+50, 2400, "right");
        construction.setShed(shed);

        construction.setShed(shed);
        construction.setConstructionLength();
        construction.setConstructionWidth();
        roof = new RoofFlat(0, construction.getConstructionLength(), construction.getConstructionWidth(), 3);
        construction.setRoof(roof);
        ArrayList<Wall> shedWalls = WallBuilder.addShedWalls(construction);
        shed.setWalls(shedWalls);
        ArrayList<String> sides = new ArrayList<>();
        sides.add("back");
        sides.add("left");
        sides.add("right");

        ArrayList<Wall> carportWalls = WallBuilder.createCarportWalls(construction, sides);
        construction.setWalls(carportWalls);
        a=shed.getWalls().get(1);// shed left
        b=construction.getWalls().get(1); //carport left
        c=shed.getWalls().get(3); // shed back
    }


    @Test
    public void seeWal() {
        for (Wall wall : shed.getWalls()) {
            System.out.println(wall.getSide());
        }
        for (Wall wall: construction.getWalls()) {
            System.out.println(wall.getSide());
        }
    }

    @Test
    public void spaerOneWall() {
        System.out.println(b.getSide()+"l: "+b.getLength());
        Material sper = OverlayMaterialCalculator.spaerOneWall(b);
        assertEquals(6700/3,sper.getSize());
        assertEquals(9,sper.getAmount());



    }

    @Test
    public void screwSparOneWall() {
        int q=OverlayMaterialCalculator.screwSparOneWall(b).getSize();
        assertEquals(9*2*2,q);
    }

    @Test
    public void fyrOneWall() {
        int q=OverlayMaterialCalculator.fyrOneWall(b).size();
        int h=OverlayMaterialCalculator.fyrOneWall(b).get(OverlayMaterialCalculator.fyrOneWall(b).size()-1).getSize();
        assertEquals(13,q);
        assertEquals(2270,h,3);


    }

    @Test
    public void screwFYROneWall() {
        int q = OverlayMaterialCalculator.screwFYROneWall(b).getSize();
        assertEquals(13*9,q);
    }


@Test
    public  void allMat() throws LoginSampleException {
        String all = OverlayMaterialCalculator.allOverlayMaterialList(construction, "21X113 RU PROFILBRÆDDER");

    }
}