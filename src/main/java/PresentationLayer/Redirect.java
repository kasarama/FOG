package PresentationLayer;


import CarportUtil.Initializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of this class is that we can enter jsp pages from outside the WEB-INF package.
 * This is necessary because we have to go though the FrontController.
 * @author Mia
 */
public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {

        String destination = request.getParameter("destination");

        return destination;
    }
}