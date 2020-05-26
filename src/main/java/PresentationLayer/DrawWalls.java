package PresentationLayer;

import FunctionLayer.Order;
import FunctionLayer.Svg;
import FunctionLayer.Wall;
import FunctionLayer.WallDrawer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class DrawWalls extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Order order = (Order) request.getServletContext().getAttribute("orderForValidation");
        for (Wall wall :order.getConstruction().getWalls()) {
            System.out.println(wall.getSide()+ " length"+ wall.getLength()+", min height "+wall.getMinHeight()+", tilt: "+ wall.getRaising());
        }

        String wallsFromAbove = WallDrawer.allWallsFromAbove(order.getConstruction());
        HashMap<String, Svg>drawings = WallDrawer.framings(order.getConstruction());
         String showSVG = null;

         if (request.getParameter("constructionWall")!=null){
             showSVG= drawings.get(request.getParameter("constructionWall")).toString();
         } else
             if(request.getParameter("allFromAbove")!=null){
                 showSVG=wallsFromAbove;
             } else if (request.getParameter("shedWall")!=null){
                 showSVG=drawings.get(request.getParameter("shedWall")).toString();
             }


        request.setAttribute("wallsFromAbove", showSVG);
        return "wallSVG";
    }
}
