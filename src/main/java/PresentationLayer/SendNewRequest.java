package PresentationLayer;

import FunctionLayer.Construction;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of this class is to create new order object with customer's chosen Construction,
 * save its data in database throuhgh LogicFacade and set String message on request
 * @author Magdalena
 */
public class SendNewRequest extends Command {

    /**
     * @param request servlet request
     * @param response servlet response
     * @return String - name of JavaServer Page
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {



        HttpSession session = request.getSession();
        Construction construction = (Construction) session.getAttribute("carportBase");

        String email = (String) session.getAttribute("email");
        Order order = new Order();
        order.setConstruction(construction);
        order.setEmail(email);

        LogicFacade.sendNewRequest(order);
        session.setAttribute("carportBase", null);

        request.setAttribute("newRequestMSG", "Dine forespørgelse er blevet sendt til validering");

        return "customerPage";
    }
}