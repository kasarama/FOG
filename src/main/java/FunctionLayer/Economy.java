package FunctionLayer;

import java.util.ArrayList;

/**
 * @author Mia and Magdalena
 * The purpose of this class is to calculate prices and cost of different elements of a construction
 * and of the whole construction
 */
public class Economy {
    // Indkøbspris
    public static double ordersCostPrice(Order order) throws LoginSampleException {
        // Søg efter materiales pris via id
        // Hent prisen fra db og set pris i materialCalculator klasserne
        // Looper igennem arraylisten og får fat på hver materiales pris og antal

        ArrayList<Material> temp = new ArrayList<>();
        temp.addAll(order.getConstruction().getFundamentMaterials());
        temp.addAll(order.getConstruction().getRoof().getRoofMaterialList());
        temp.addAll(order.getConstruction().getShed().getMaterials());
//todo hvorfor tager det ikke material på index 0?  Den tager size af material,
// men på styklisten kan vi se at der er materials med size = 0
// her er alt du skriver i 3 linier:



        double[] totalPrices = new double[temp.size()];
        for (int i = 1; i < temp.size(); i++) {
            totalPrices[i] = temp.get(i).getPrice() * temp.get(i).getAmount();
        }


        double cost = 0;
        for (double i : totalPrices) {
            cost += i;
        }
        return cost;
    }

    // Salgsprisen
    public static double ordersSalePrice(Order order) throws LoginSampleException {

        // Salgspris = (Indkøbspris + Fragt)* 25 % skat

        double transport = order.getTransport(); // Transport udgift
        double tax = order.getTAX(); // 0,25

        double salesPriceNoTax = ordersCostPrice(order) + transport;
        double salesPrice = salesPriceNoTax + salesPriceNoTax * tax;

        return salesPrice;
    }

    // Dækningsbidrag og dækningsgrad i %
    public static double setCoverage(Order order) throws LoginSampleException {

        double transport = order.getTransport(); // Transport udgift
        double tax = order.getTAX(); // 0,25
        double cost = ordersCostPrice(order);
        double salesPrice = ordersSalePrice(order);

        // Dækningsbidrag = Salgspris - (Indkøbspris+Fragt) skat?
        double coverageContribution = ((salesPrice * tax) + cost + transport) - salesPrice;

        // Dækningsgrad = ( Dækningsbidrag / cost ) * 100
        double coverage = (coverageContribution / cost) * 100;

        return coverage;
    }



    public static void setSalePriceFromCoverage (Order order) throws LoginSampleException {
        //Dækningsgrad = ( Dækningsbidrag / Omsætning ) * 100
        //Dækningsbidrag=(Omsætning-Kost-Transport)
        double salePrice;
        double coverage = order.getCoverage();
        double cost = order.getCost() + order.getTransport();


        if (order.getCoverage()<=0){
            throw new LoginSampleException("Dækningsgrad ikke stor nok");
        }else
            salePrice = cost / (100 - coverage) *100;


        salePrice= salePrice*(1+order.getTAX());
        order.setSalePrice((Math.round(salePrice) * 100.0) / 100.0);
    }

    public static void setCoverageFromPrice (Order order) throws LoginSampleException {
        double coverage;
        double saleprice= order.getSalePrice()/(1+order.getTAX());
        double cost = order.getCost()+order.getTransport();
        if (order.getSalePrice()<=0){
            throw new LoginSampleException("Salgspris ikke stor nok");
        } else
            coverage = (saleprice-cost)/saleprice*100;
        order.setCoverage((Math.round(coverage) * 100.0) / 100.0);
    }


}
