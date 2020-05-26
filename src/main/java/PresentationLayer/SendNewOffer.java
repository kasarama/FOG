package PresentationLayer;

import FunctionLayer.Construction;
import FunctionLayer.Economy;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Magdalena
 */
public class SendNewOffer extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //todo read order from aplication scoop, call a method from FunctionLayer that will send it further to
        // OrderMapper and sæt Order on aplicationScoop as null


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
