package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * The purpose of this class is to read user's input and to set an ArrayList with Orders on servletContext object
 * @author Magdalena
 */
public class ShowRequests extends Command {
    /**
     * @param request servlet request
     * @param response servlet response
     * @return string - name of JavaServer Page
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String page = "employeePage";

        String status = "status";
        if (request.getParameter("newrequests") != null) {
            status = "newrequest";
            page = "newRequests";
            ArrayList<Order> orders = LogicFacade.ReadOrders(status);
            request.getServletContext().setAttribute("newRequestsList", orders);

        } else if (request.getParameter("sentoffers") != null) {
            status = "validated";
            page = "sentOffers";
            ArrayList<Order> orders = LogicFacade.ReadOrders(status);
            request.getServletContext().setAttribute("sentOffersList", orders);


        } else if (request.getParameter("orders") != null) {
            status = "paid";
            page = "paidOrders";
            ArrayList<Order> orders = LogicFacade.ReadOrders(status);
            request.getServletContext().setAttribute("paidOrdersList", orders);


        }


        return page;


    }
}