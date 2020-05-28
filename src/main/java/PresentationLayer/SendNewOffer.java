package PresentationLayer;

import FunctionLayer.Construction;
import FunctionLayer.Economy;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The purpose of SendNewOffer class is to update values of an Order object, save its data in database through LogicFacade,
 * to set a String on request and to set Order object on null
 * @author Magdalena
 */
public class SendNewOffer extends Command {
    /**
     *
     * @param request
     * @param response
     * @return String - name of jsp
     * @throws LoginSampleException if an error occurs
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        double saleprice = Double.parseDouble(request.getParameter("saleprice"));

        double coverage = Double.parseDouble(request.getParameter("coverage"));

        Order order = (Order) request.getServletContext().getAttribute("orderForValidation");

        if (request.getParameter("byPrice")!=null){
            order.setSalePrice(saleprice);
            Economy.setCoverageFromPrice(order);
        } else

        if (request.getParameter("byCoverage")!=null){
            order.setCoverage(coverage);
            Economy.setSalePriceFromCoverage(order);
        }

        LogicFacade.sendOffer(order);

        request.setAttribute("orderMSG","Tilbud er blevet sendt til "+ order.getEmail()+"" +
                "Salgspris: "+ order.getSalePrice()+", dækningsgrad: "+order.getCoverage());

        //LogicFacade.setMaterialsForOrder(order) should not be used here
        try {
            LogicFacade.setMaterialsForOrder(order);
        } catch (LoginSampleException e) {
            e.printStackTrace();
            throw new LoginSampleException(e.getMessage());

        }

        request.getServletContext().setAttribute("orderForValidation",null);

        return "employeePage";
    }
}
