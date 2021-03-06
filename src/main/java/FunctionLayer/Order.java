package FunctionLayer;

import java.util.Date;

/**
 * @author Magdalena
 * the purpose of this class is to create an Order object
 */

public class Order {
    private Construction construction;
    private int orderID;
    private int customerID;
    private String email;
    private long timestamp;
    private String status;
    private double cost;
    private double salePrice;
    private Date date;
    private double coverage;
    private double transport;
    final private double TAX=0.25;
    final private double DEFAULTCOVERAGE=50.0;

    public Order() {
    }

    public Order(Construction construction, int orderID, int customerID, String email, long timestamp, String status, double cost, double salePrice) {
        this.construction = construction;
        this.orderID = orderID;
        this.customerID = customerID;
        this.email = email;
        this.timestamp = timestamp;
        this.status = status;
        this.cost = cost;
        this.salePrice = salePrice;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void setConstruction(Construction construction) {
        this.construction = construction;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getTAX() {
        return TAX;
    }

    public double getDEFAULTCOVERAGE() {
        return DEFAULTCOVERAGE;
    }

    public String coverageToString() {
        return String.format("%.2",this.coverage);
    }
}
