package FunctionLayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class SvgTest {

    Svg svg;

    @Before
    public void setUp() throws Exception {
        svg = new Svg(1000, 800, "0,0,1000,800", 0, 0);
    }

    @Test
    public void addRect1() {
        svg.addRect(0,0,300,600);
        String expected ="<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"800\" width=\"1000\" viewBox=\"0,0,1000,800\" preserveAspectRatio=\"xMinYMin\"><rect x=\"0\" y=\"0\" height=\"300\" width=\"600\" style=\"stroke:black; fill: #ffffff\" /></svg>";
        assertEquals(expected, svg.toString());
    }

    @Test
    public void addRect2() {
        svg.addRect(0,0,300,600);
        String expected ="<rect x=\"0\" y=\"0\" height=\"300\" width=\"600\" style=\"stroke:black; fill: #ffffff\" />";
        assertThat(svg.toString(), containsString(expected));
    }

    @Test
    public void addBand() {
        svg.addBand(0,30,246,270);
        String expected ="<line x1=\"0\"  y1=\"30\" x2=\"246\" y2=\"270\" style=\"stroke:black; stroke-dasharray: 10 5\"/>";;
        assertThat(svg.toString(), containsString(expected));
    }

    @Test
    public void addArrows() {
        svg.addArrows(-25, 0, -25, 300, -35, 150, -90, "Test Arrow");
        String expected = "<defs>\n" +
                "        <marker\n" +
                "            id=\"beginArrow\"\n" +
                "            markerWidth=\"8\"\n" +
                "            markerHeight=\"8\"\n" +
                "            refX=\"4\"\n" +
                "            refY=\"4\"\n" +
                "            orient=\"auto\">\n" +
                "            <path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: black;\" />\n" +
                "        </marker>\n" +
                "        <marker\n" +
                "                id=\"endArrow\"\n" +
                "                markerWidth=\"8\"\n" +
                "                markerHeight=\"8\"\n" +
                "                refX=\"8\"\n" +
                "                refY=\"4\"\n" +
                "                orient=\"auto\">\n" +
                "            <path d=\"M0,0 L8,4 L0,8 L0,0 \" style=\"fill: black;\" />\n" +
                "        </marker>\n" +
                "    </defs>\n" +
                "    <line x1=\"-25\"  y1=\"0\" x2=\"-25\"   y2=\"300\"\n" +
                "          style=\"stroke: black;\n" +
                "\tmarker-start: url(#beginArrow);\n" +
                "\tmarker-end: url(#endArrow);\"/>\n" +
                "    <text style=\"text-anchor: middle; font-size: 12;\" transform=\"translate(-35,150) rotate(-90)\">Test Arrow</text>\n";
        assertThat(svg.toString(), containsString(expected));
    }
}
