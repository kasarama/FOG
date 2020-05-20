package FunctionLayer;

import org.junit.Before;
import java.util.ArrayList;


public class OverlayMaterialCalculatorTest {

    Construction construction = new Construction();
    Shed shed = new Shed(3050, 2500, "left");
    Roof roof = new RoofFlat(0, 0, 0, 3);

    @Before
    public void setUp() throws Exception {
        construction.setRoof(roof);
        construction.setCarportWidth(6000);
        construction.setCarportLength(4200);
        construction.setConstructionHeight(2000);
        ArrayList<Wall> shedWalls = WallBuilder.addShedWalls(construction);
        shed.setWalls(shedWalls);
        ArrayList<String> wallsSides = new ArrayList<>();
        wallsSides.add("right");
        wallsSides.add("back");
        ArrayList<Wall> carportWalls = WallBuilder.createCarportWalls(construction, wallsSides);
        construction.setWalls(carportWalls);
        construction.setOverlay("SIBIRISK LÆRK KLINKBEKLÆDNING");
    }

}