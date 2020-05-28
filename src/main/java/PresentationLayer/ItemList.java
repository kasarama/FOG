package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * the purpose of this class is to fill up the ArrayLists with Material object for Order object
 * @author Magdalena
 */
public class ItemList  extends Command{

    /**
     *
     * @param request
     * @param response
     * @return String - nae of jsp
     * @throws LoginSampleException if an error occures
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        Order order = (Order) request.getServletContext().getAttribute("orderForValidation");

        String msg =LogicFacade.setMaterialsForOrder(order);
        request.setAttribute("overlayMAterialMSG", msg);

        request.getServletContext().setAttribute("orderForValidation", order);
        return "itemList";
    }
}