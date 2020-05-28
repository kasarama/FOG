package PresentationLayer;


import FunctionLayer.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * The purpose of this class is to read user input to edit Construction object
 * @author Magdalena
 */
public class Overlay extends Command {

    /**
     *
     * @param request
     * @param response
     * @return name of jsp
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Construction construction = (Construction) session.getAttribute("carportBase");

        String overlayComponents = request.getParameter("overlayName");
        String right = request.getParameter("right");
        String left = request.getParameter("left");
        String back = request.getParameter("back");
        String noWalls = request.getParameter("noWalls");
        String coverWalls = request.getParameter("coverWalls");
        ArrayList<String> wallsToCover = new ArrayList<>();
        String overlayName=null;
        String color=null;
        if (overlayComponents!=null){
            String[] components= overlayComponents.split(";");
            System.out.println("Data om beklædning fra request: "+overlayComponents);
            overlayName=components[0];
            color=components[1];

        }

        if (right != null) {
            wallsToCover.add(right);
        }
        if (left != null) {
            wallsToCover.add(left);

        }
        if (back != null) {
            wallsToCover.add(back);
        }
        construction.setWallSides(wallsToCover);

        int shedDepth = construction.getShedDepth();
        String overlayMSG = "Prøv igen";
        String targetPage = "customerChoiceResult";


        if (shedDepth == 0 && noWalls != null) {
            return targetPage;
        }


        if (shedDepth == 0 && coverWalls != null) {
            if (overlayComponents == null || wallsToCover.size() == 0) {
                overlayMSG = "Vælg beklædning og de ønskede vægger ";
            } else {
                construction.setOverlay(overlayName);
                construction.setColor(color);
                construction.setWallSides(wallsToCover);
                construction.setWalls(WallBuilder.createCarportWalls(construction, wallsToCover));

                return targetPage;
            }
        } else

        if (shedDepth>0 && noWalls!=null){
            if (overlayComponents == null) {
                overlayMSG = "Vælg beklædning for at fortsætte";
            } else {
                construction.setOverlay(overlayName);
                construction.setColor(color);

                return targetPage;
            }

        } else
        if (shedDepth>0 && coverWalls!=null){
            if (overlayComponents == null || wallsToCover.size() == 0) {
                overlayMSG = "Vælg beklædning og de ønskede vægger ";
            } else {
                construction.setOverlay(overlayName);
                construction.setColor(color);
                construction.setWalls(WallBuilder.createCarportWalls(construction, wallsToCover));

                return targetPage;
            }

        }

        request.setAttribute("overlayMSG", overlayMSG);
        return "overlay";
    }
}
