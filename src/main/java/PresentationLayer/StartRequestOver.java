package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * The purpose of StarrtRequestOver is to delete construction object from session
 * @author Magdalena
 */
public class StartRequestOver extends Command {
    /**
     * @param request servlet request
     * @param response servlet response
     * @return string - name of JavaServer Page
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        //todo sletter alle data fra carportRequest som er gemt på session

        HttpSession session = request.getSession();
        session.setAttribute("carportBase", null);
        return "carportBase";
    }
}