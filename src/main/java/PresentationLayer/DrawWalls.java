package PresentationLayer;

import FunctionLayer.Svg;
import FunctionLayer.Wall;
import FunctionLayer.WallDrawer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class DrawWalls extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String wallsFromAbove = WallDrawer.wallsDrawings();
        HashMap<String, Svg>drawings = WallDrawer.framings();
        String wallSide = request.getParameter("wallSideName");

        if (wallSide!=null){
            request.setAttribute(wallSide,drawings.get(wallSide));
        }

        request.setAttribute("wall1", drawings.get("carportright").toString());

        request.setAttribute("wallsFromAbove", wallsFromAbove);
        return "wallSVG";
    }
}
