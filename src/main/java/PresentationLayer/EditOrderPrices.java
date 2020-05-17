package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class EditOrderPrices extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        System.out.println("1");
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        System.out.println("2");
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));

        System.out.println("3");

        int angle = Integer.parseInt(request.getParameter("angle"));

        int tilt = Integer.parseInt(request.getParameter("tilt"));

        double transport = Double.parseDouble(request.getParameter("transport"));

        System.out.println("4");

        Order order = (Order) request.getServletContext().getAttribute("orderForValidation");

        order.getConstruction().setCarportLength(carportLength);
        order.getConstruction().setCarportWidth(carportWidth);

        System.out.println("5");
        if (order.getConstruction().getShed().getDepth() > 0) {
            String shedSide = request.getParameter("shedSide");
            int shedDepth = Integer.parseInt(request.getParameter("shedDepth"));
            order.getConstruction().getShed().setSide(shedSide);
            order.getConstruction().getShed().setDepth(shedDepth);
            ArrayList<Wall> shedWalls = WallBuilder.addShedWalls(order.getConstruction());
            order.getConstruction().getShed().setWalls(shedWalls);

        }

        System.out.println("6");
        order.getConstruction().setConstructionWidth();
        order.getConstruction().setConstructionLength();
        order.getConstruction().getRoof().setDegree(angle);
        order.getConstruction().getRoof().setTilt(tilt);
        order.setTransport(transport);

        ArrayList<Wall> costructionWalls = WallBuilder.createCarportWalls(order.getConstruction(), order.getConstruction().getWallSides());
        order.getConstruction().setWalls(costructionWalls);
        order.setCoverage(order.getDEFAULTCOVERAGE());

        System.out.println("is about to call for adding all  materials");

        LogicFacade.setMaterialsForOrder(order);
        System.out.println("Added materials to order - overlay has size: " + order.getConstruction().getShed().getMaterials().size());

        System.out.println("7");
        order.setCost(Math.round(Economy.ordersCostPrice(order) * 100.0) / 100.0);
        order.setSalePrice(Math.round(Economy.ordersSalePrice(order) * 100.0) / 100.0);
        order.setCoverage(Math.round(Economy.setCoverage(order) * 100.0) / 100.0);
        System.out.println("8");

        request.getServletContext().setAttribute("orderForValidation", order);
        System.out.println("9");
        return "prepareOffer";

    }
}