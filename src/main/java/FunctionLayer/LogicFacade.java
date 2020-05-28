package FunctionLayer;

import DBAccess.MaterialMapper;
import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String name, String email, String password) throws LoginSampleException {
        User user = new User(name, email, password, "customer");
        UserMapper.createUser(user);
        return user;
    }


    public static Material getMaterialBySizeName(int size, String name) throws LoginSampleException {
        return MaterialMapper.getMaterialBySizeName(size, name);
    }

    public static Material getMaterialByID(int id) throws LoginSampleException {
        return MaterialMapper.getMaterialByID(id);
    }

    public static String getUnitByName(String name) throws LoginSampleException {
        String unit = MaterialMapper.getUnitByName(name);
        return unit;
    }

    public static int getWidthByID(int id, String name) throws LoginSampleException {
        int width = MaterialMapper.getWidthByID(id, name);
        return width;
    }

    public static int getThicknessByID(int id) throws LoginSampleException {
        int thickness = MaterialMapper.getThicknessByID(id);
        return thickness;
    }

    public static double getPrice(int id) throws LoginSampleException {
        double price = MaterialMapper.getPrices(id);
        return price;
    }


    public static ArrayList<Integer> getLengthForMaterials(String nameMaterial) throws LoginSampleException {
        ArrayList lengthForMaterials = MaterialMapper.getLengthForMaterials(nameMaterial);
        return lengthForMaterials;
    }

    public static String getANameFromMaterialID(int idMaterial) throws LoginSampleException {
        return MaterialMapper.getNameFromMaterialID(idMaterial);
    }

    public static String getColorByMaterialID(int materialID) throws LoginSampleException {
        return MaterialMapper.getColorByMaterialID(materialID);
    }

    public static String getColourByVariationID(int variationid) throws LoginSampleException {
        return MaterialMapper.getColorByID(variationid);
    }

    public static Material getMaterialByNameColourAndSize(String name, int size, String color) throws LoginSampleException {
        return MaterialMapper.getRoofFullMaterialBySizeColorAndName(name, size, color);
    }

    //call the static method that gets the ROOF materials data from DB - static = can be called without creating an obj.
    public static List<Material> getAllPitchedRoofMaterials() throws LoginSampleException {
        return MaterialMapper.getAllPitchedRoofMaterials(); //return 'getAllRoofMaterials()' method from MaterialMapper
    }

    public static List<Material> getAllFlatRoofMaterials() throws LoginSampleException {
        return MaterialMapper.getAllFlatRoofMaterials(); //return 'getAllRoofMaterials()' method from MaterialMapper
        /*if (isPitched) {
            return MaterialMapper.getAllRoofMaterialsByCategory("RejsningTag");//return 'getAllRoofMaterials()' method from MaterialMapper
        }
        return MaterialMapper.getAllRoofMaterialsByCategory("FladtTag");*/
    }

    /**
     * The purpose of this method is to get data from database throught MaterialMapper
     * that represent materials used for overlay for walls and return a list of them
     * @author Magdalena
     * @return ArrayList
     * @throws LoginSampleException that is thrown by MaterialMapper.getAllOverlay
     */
    public static List<Material> getAllOverlays() throws LoginSampleException {
        return MaterialMapper.getAllOverlays();
    }

    /**
     * The purpose of this method is to to save values of the Order object in databese throught OrderMapper
     * @author Magdalena
     * @param order that contains Construction designed by a customer
     * @throws LoginSampleException that is thrown by OrderMapper.saveNewRequest
     */
    public static void sendNewRequest(Order order) throws LoginSampleException {
        Date nowDate = new Date();
        long timestamp = nowDate.getTime();

        order.setTimestamp(timestamp);
        order.setStatus("newrequest");
        order.setSalePrice(0);
        order.setCost(0);
        OrderMapper.saveNewRequest(order);
    }

    /**
     * The purpose of this method is to create a list of orders that in database have given status
     * @author Magdalena
     * @param status String used to call a method that read data from database for given status
     * @return ArrayList filled up with Order objects
     * @throws LoginSampleException that is thrown by OrderMapper.ReadAllOrdersByStatus
     */
    public static ArrayList<Order> ReadOrders(String status) throws LoginSampleException {
        return OrderMapper.ReadAllOrdersByStatus(status);
    }


    /**
     * The purpose of setMaterialsForOrder is to generate ArrayLists for each element of Construction
     * objekt and to return an Order object with that Construction object
     * @param order object with not null attributter.
     * @return msg
     * @throws LoginSampleException that is thrown by OverlayMaterialCalculator.allOverlayMaterialList,
     * pRMCalculator.pitchedRoof(), rMCalculator.getflatRoofMaterials or ConstructionMaterialCalculator.constructionMaterialList
     */
    public static String setMaterialsForOrder(Order order) throws LoginSampleException {

        String msg = OverlayMaterialCalculator.allOverlayMaterialList(
                order.getConstruction(), order.getConstruction().getOverlay());
        ArrayList<Wall> newWalls=WallBuilder.addShedWalls(order.getConstruction());
        order.getConstruction().getShed().setWalls(newWalls);

        //................Materials for roof...........//
        boolean orderedCaportIsPitched = order.getConstruction().getRoof().getIsPitched();
        ArrayList<Material> roofMaterialList = new ArrayList();
        if (orderedCaportIsPitched) {
            PitchedRoofMaterialCalculator pRMCalculator = new PitchedRoofMaterialCalculator(order.getConstruction());
            roofMaterialList = pRMCalculator.pitchedRoof();

        } else {
            RoofMaterialCalculator rMCalculator = new RoofMaterialCalculator(order.getConstruction());
            roofMaterialList = rMCalculator.getflatRoofMaterials();

        }
        order.getConstruction().getRoof().setRoofMaterialList(roofMaterialList);


        //................Materials for construction...........//

        ArrayList<Material> constructionMaterialList = ConstructionMaterialCalculator.constructionMaterialList(order.getConstruction());
        order.getConstruction().setFundamentMaterials(constructionMaterialList);
        return msg;
    }

    /**
     * The purpose of this method is to update data in database for given order
     * @author Magdalena
     * @param order that contains a construction that has been validated/edited by en amployee
     * @throws LoginSampleException that ist thrown by  OrderMapper.saveNewOffer
     */
    public static void sendOffer(Order order) throws LoginSampleException {
        Date nowDate = new Date();
        long timestamp = nowDate.getTime();

        order.setTimestamp(timestamp);
        order.setStatus("validated");

        OrderMapper.saveNewOffer(order);
    }

}
