package CarportUtil;

import DBAccess.MaterialMapper;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;

import java.util.List;

/**
 * The purpose of this class is to initialize the material lists, in case they are null.
 */

public class Initializer {

    private static List<Material> pitchedRoofMateriallist = null;
    private static List<Material> flatRoofMateriallist = null;
    private static List<Material> overlayList = null;
    private static List<Material> overlayMaterialsList = null;


    //Getters
    public static List<Material> getPitchedRoofMateriallist() {
        if (pitchedRoofMateriallist == null)
        {
            try {
                pitchedRoofMateriallist = LogicFacade.getAllPitchedRoofMaterials();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pitchedRoofMateriallist;
    }

    public static List<Material> getFlatRoofMateriallist() {
        if (flatRoofMateriallist == null)
        {
            try {
                flatRoofMateriallist = LogicFacade.getAllFlatRoofMaterials();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flatRoofMateriallist;
    }


    public static List<Material> getOverlayList() throws LoginSampleException {
        if (overlayList == null){
            try {
                overlayList = LogicFacade.getAllOverlays();
            } catch (LoginSampleException e) {
                throw new LoginSampleException("Overlay list could not load");
            }
        }
        return overlayList;
    }



}