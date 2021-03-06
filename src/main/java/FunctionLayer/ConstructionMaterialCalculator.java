package FunctionLayer;

// Materiale type

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.*;

import static FunctionLayer.ConstructionSizeCalculator.*;
import static FunctionLayer.LogicFacade.getLengthForMaterials;
import static FunctionLayer.LogicFacade.sendOffer;

public class ConstructionMaterialCalculator {
    public static ConstructionSizeCalculator constructionSizeCalculator = new ConstructionSizeCalculator();
    //public Construction construction = new Construction();
    private static int INGROUND = 900;


    //.......................All the materials for construction.............................//
    public static ArrayList<Material> constructionMaterialList(Construction construction) throws LoginSampleException {
        System.out.println("is about to fill up the fundament list ");
        ArrayList<Material> woodMaterials = woodMaterials(construction);
        ArrayList<Material> metalMaterials = metalMaterials(construction);
        ArrayList <Material> posts = postsQuatity(construction);
        ArrayList<Material> constructionMaterials = new ArrayList<>();
        constructionMaterials.addAll(woodMaterials);
        constructionMaterials.addAll(metalMaterials);
        constructionMaterials.addAll(posts);
        System.out.println("is about to return fundament materials : "+constructionMaterials.size());
        return constructionMaterials;
    }


    public static ArrayList<Material> postsQuatity(Construction construction) throws LoginSampleException {

        // Stolper (uden skur)
        ArrayList<Material> posts = new ArrayList();

        int roofHeight = construction.getRoof().getHeight();
        double constructionMinHeight = construction.getConstructionHeight() - construction.getRoof().getHeight();
        ArrayList<Integer> actualHeightsOfPostsForConstruction = new ArrayList();

        int carportMinHeight = carportMinHeight((int) constructionMinHeight, construction.getShed().getDepth(),
                construction.getRoof().getTilt());
        int quantityOfCarportPostsRows = postRows(construction.getCarportWidth());
        Integer[] heightsOfPostsPerRow = postsHeights(carportMinHeight, construction.getRoof().getDegree(),
                construction.getCarportWidth());
        for (int i = 0; i < quantityOfCarportPostsRows - 1; i++) {
            for (int postHeight : heightsOfPostsPerRow) {
                actualHeightsOfPostsForConstruction.add(postHeight+INGROUND);
            }
        }
// todo tjek det: OverlayMaterialCalculator.fyrOneWall(Wall wall)
        ArrayList<Integer> postsMaterialsAvalibleLenghts = getLengthForMaterials("TRYKIMPRENERET STOLPE");
        ArrayList<Material> tempPostsMaterails = new ArrayList<>();
        int restOfAvalibleMaterial = 0;
        int countPosts = 1;
        Material post;

        for (int avaliblePostMaterialLength : postsMaterialsAvalibleLenghts) {
            //avaliblePostMaterialLength + 900;
            post = LogicFacade.getMaterialBySizeName(avaliblePostMaterialLength, "TRYKIMPRENERET STOLPE");
            int avLength = post.getSize();
            post.setAvailablesize(avLength*10);
            avaliblePostMaterialLength = avLength*10;
            post.setWidth(LogicFacade.getWidthByID(post.getId(), post.getName()));
            post.setThickness(LogicFacade.getThicknessByID(post.getId()));
            post.setName("TRYKIMPRENERET STOLPE" + post.getThickness() + "x" + post.getWidth());
            for (int postHeightForConstruction : actualHeightsOfPostsForConstruction) {
                if (postHeightForConstruction <= avaliblePostMaterialLength) {
                    restOfAvalibleMaterial = avaliblePostMaterialLength % postHeightForConstruction;
                }

                if (restOfAvalibleMaterial != 0)
                    for (int i = postsMaterialsAvalibleLenghts.indexOf(post); i > 0; i--) {
                        Material tempPost = tempPostsMaterails.get(i);
                        int tempQuatityPostsTypePlusOneEkstra = tempPost.getAmount() + 1;
                        tempPost.setAmount(tempQuatityPostsTypePlusOneEkstra);
                    }

                countPosts++;
                post.setAmount(countPosts);
                post.setComment("Træ til stolper - skal 90 cm i jorden");

            }
            tempPostsMaterails.add(post);
        }
        posts.addAll(tempPostsMaterails);

        return posts;
    }



    public static ArrayList<Material> postForShed (Construction construction) {
        ArrayList<Material> shedPost =new ArrayList<>();

        //todo find amount of post for shed
        return shedPost;
    }
    

    //................................wood materials............................//
    public static ArrayList<Material> woodMaterials(Construction construction) throws LoginSampleException {
        ArrayList<Material> woodMaterials = new ArrayList<>();
        // Rem
        int[] remPieces = ConstructionSizeCalculator.remPieces(construction);
        int counter = 0;
        Material rem = null;
        for (int remPiece : remPieces) {
            // TODO find material mapper
            rem = LogicFacade.getMaterialBySizeName(remPiece, "SPÆRTRÆ UBEHANDLET");
            rem.setName("SPÆRTRÆ UBEHANDLET");
            rem.setUnit(LogicFacade.getUnitByName(rem.getName()));
            rem.setWidth(LogicFacade.getWidthByID(rem.getId(), rem.getName()));
            rem.setThickness(LogicFacade.getThicknessByID(rem.getId()));
            rem.setName("SPÆRTRÆ UBEHANDLET " + rem.getThickness() + "x" + rem.getWidth());
            rem.setSize(remPiece);
            rem.setPrice(LogicFacade.getPrice(rem.getId()));
            if (remPiece == rem.getSize()) {
                counter++;
            }
            rem.setAmount(counter);
            rem.setComment("Remme skal monteres på stolper");
        }
        woodMaterials.add(rem);
        // Spær
        int amount = ConstructionSizeCalculator.roofSpaerAmount(construction);
        int size = ConstructionSizeCalculator.roofSpaerLength(construction);
        Material spær = null;
        for (int i = 0; i < amount; i++) {
            spær = LogicFacade.getMaterialBySizeName(size, "SPÆRTRÆ UBEHANDLET");
            spær.setName("SPÆRTRÆ UBEHANDLET");
            spær.setUnit(LogicFacade.getUnitByName(spær.getName()));
            spær.setWidth(LogicFacade.getWidthByID(spær.getId(), spær.getName()));
            spær.setThickness(LogicFacade.getThicknessByID(spær.getId()));
            spær.setName("SPÆRTRÆ UBEHANDLET " + spær.getThickness() + "x" + spær.getWidth());
            spær.setSize(size);
            spær.setAmount(amount);
            spær.setPrice(LogicFacade.getPrice(spær.getId()));
            spær.setComment("Spær skal moteres på remme");
        }
        woodMaterials.add(spær);
        // Understern
        int[] understernPieces = ConstructionSizeCalculator.underSternPieces(construction);
        int counter2 = 0;
        Material underStern = null;
        for (int underSternObject : understernPieces) {
            underStern = LogicFacade.getMaterialBySizeName(underSternObject, "TRYKIMPRENERET BRÆDT 200");
            underStern.setName("TRYKIMPRENERET BRÆDT 200");
            underStern.setUnit(LogicFacade.getUnitByName(underStern.getName()));
            underStern.setId(2);
            underStern.setWidth(LogicFacade.getWidthByID(underStern.getId(), underStern.getName()));
            underStern.setThickness(LogicFacade.getThicknessByID(underStern.getId()));
            underStern.setName("TRYKIMPRENERET BRÆDT " + underStern.getThickness() + "x" + underStern.getWidth());
            underStern.setSize(underSternObject);
            underStern.setPrice(LogicFacade.getPrice(underStern.getId()));
            if (underSternObject == underStern.getSize()) {
                counter2++;
            }
            underStern.setAmount(counter2);
            underStern.setComment("Understern skal monteres på spær");
        }
        woodMaterials.add(underStern);
        // Overstern
        int[] oversternPieces = ConstructionSizeCalculator.overSternPieces(construction);
        int counter3 = 0;
        Material overStern = null;
        for (int overSternObject : oversternPieces) {
            overStern = LogicFacade.getMaterialBySizeName(overSternObject, "TRYKIMPRENERET BRÆDT 125");
            overStern.setName("TRYKIMPRENERET BRÆDT 125");
            overStern.setUnit(LogicFacade.getUnitByName(overStern.getName()));
            overStern.setId(3);
            overStern.setWidth(LogicFacade.getWidthByID(overStern.getId(), overStern.getName()));
            overStern.setThickness(LogicFacade.getThicknessByID(overStern.getId()));
            overStern.setName("TRYKIMPRENERET BRÆDT " + overStern.getThickness() + "x" + overStern.getWidth());
            overStern.setSize(overSternObject);
            overStern.setPrice(LogicFacade.getPrice(overStern.getId()));
            if (overSternObject == overStern.getSize()) {
                counter3++;
            }
            overStern.setAmount(counter2);
            overStern.setComment("Overstern skal monteres på understern");

        }
        woodMaterials.add(overStern);
        return woodMaterials;
    }

    //................................metal materials............................//
    public static ArrayList<Material> metalMaterials(Construction construction) throws LoginSampleException {
        ArrayList<Material> metalMaterials = new ArrayList<>();

        // Bræddebolte
        Material bræddebolt = LogicFacade.getMaterialByID(16);
        bræddebolt.setName("BRÆDDEBOLT");
        bræddebolt.setAmount(ConstructionSizeCalculator.remBoltAmount(construction));
        bræddebolt.setUnit(LogicFacade.getUnitByName(bræddebolt.getName()));
        bræddebolt.setId(16);
        bræddebolt.setWidth(LogicFacade.getWidthByID(bræddebolt.getId(), bræddebolt.getName()));
        bræddebolt.setThickness(LogicFacade.getThicknessByID(bræddebolt.getId()));
        bræddebolt.setName("BRÆDDEBOLT " + bræddebolt.getThickness() + "x" + bræddebolt.getWidth());
        bræddebolt.setComment("Til montering af rem på stolper");
        bræddebolt.setPrice(LogicFacade.getPrice(bræddebolt.getId()));
        metalMaterials.add(bræddebolt);
        // Firkantskriver
        Material firkantskriver = LogicFacade.getMaterialByID(17);
        firkantskriver.setName("FIRKANTSKIVER");
        firkantskriver.setAmount(ConstructionSizeCalculator.remSquaresAmount(construction));
        firkantskriver.setUnit(LogicFacade.getUnitByName(firkantskriver.getName()));
        firkantskriver.setId(17);
        firkantskriver.setWidth(LogicFacade.getWidthByID(firkantskriver.getId(), firkantskriver.getName()));
        firkantskriver.setThickness(LogicFacade.getThicknessByID(firkantskriver.getId()));
        firkantskriver.setName("FIRKANTSKIVER " + firkantskriver.getThickness() + "x" + firkantskriver.getWidth());
        firkantskriver.setComment("Til montering af rem på stolper");
        firkantskriver.setPrice(LogicFacade.getPrice(firkantskriver.getId()));
        metalMaterials.add(firkantskriver);
        // Hulbånd
        Material hulbånd = LogicFacade.getMaterialByID(12);
        hulbånd.setName("HULBÅND");
        hulbånd.setAmount(ConstructionSizeCalculator.perforatedBandRolls(construction));
        hulbånd.setUnit(LogicFacade.getUnitByName(hulbånd.getName()));
        hulbånd.setId(12);
        hulbånd.setWidth(LogicFacade.getWidthByID(hulbånd.getId(), hulbånd.getName()));
        hulbånd.setThickness(LogicFacade.getThicknessByID(hulbånd.getId()));
        hulbånd.setName("HULBÅND 10 M " + hulbånd.getThickness() + "x" + hulbånd.getWidth());
        hulbånd.setComment("Til vindkryds på spær");
        hulbånd.setPrice(LogicFacade.getPrice(hulbånd.getId()));
        metalMaterials.add(hulbånd);
        // Beslagskruer til hulbånd og spær
        Material beslagskruer = LogicFacade.getMaterialByID(15);
        beslagskruer.setName("BESLAGSKRUER");
        beslagskruer.setAmount(ConstructionSizeCalculator.bracketScrews(construction));
        beslagskruer.setUnit(LogicFacade.getUnitByName(beslagskruer.getName()));
        beslagskruer.setId(15);
        beslagskruer.setWidth(LogicFacade.getWidthByID(beslagskruer.getId(), beslagskruer.getName()));
        beslagskruer.setThickness(LogicFacade.getThicknessByID(beslagskruer.getId()));
        beslagskruer.setName("BESLAGSKRUER " + beslagskruer.getThickness() + "x" + beslagskruer.getWidth());
        beslagskruer.setComment("Til montering af universalbeslag + hulbånd");
        beslagskruer.setPrice(LogicFacade.getPrice(beslagskruer.getId()));
        metalMaterials.add(beslagskruer);
        // Universalbeslag Højre
        Material universalbeslagHøjre = LogicFacade.getMaterialByID(13);
        universalbeslagHøjre.setName("UNIVERSALBESLAG");
        universalbeslagHøjre.setAmount(ConstructionSizeCalculator.universalBracketsRight(construction));
        universalbeslagHøjre.setUnit(LogicFacade.getUnitByName(universalbeslagHøjre.getName()));
        universalbeslagHøjre.setId(13);
        universalbeslagHøjre.setWidth(LogicFacade.getWidthByID(universalbeslagHøjre.getId(), universalbeslagHøjre.getName()));
        universalbeslagHøjre.setName("UNIVERSALBESLAG " + universalbeslagHøjre.getWidth() + " MM. Højre");
        universalbeslagHøjre.setComment("Til montering af spær på rem");
        universalbeslagHøjre.setPrice(LogicFacade.getPrice(universalbeslagHøjre.getId()));
        metalMaterials.add(universalbeslagHøjre);
        // Universalbeslag Venstre
        Material universalbeslagVenstre = LogicFacade.getMaterialByID(13);
        universalbeslagVenstre.setName("UNIVERSALBESLAG");
        universalbeslagVenstre.setAmount(ConstructionSizeCalculator.universalBracketsLeft(construction));
        universalbeslagVenstre.setUnit(LogicFacade.getUnitByName(universalbeslagVenstre.getName()));
        universalbeslagVenstre.setId(13);
        universalbeslagVenstre.setWidth(LogicFacade.getWidthByID(universalbeslagVenstre.getId(), universalbeslagVenstre.getName()));
        universalbeslagVenstre.setName("UNIVERSALBESLAG " + universalbeslagVenstre.getWidth() + " MM. Venstre");
        universalbeslagVenstre.setComment("Til montering af spær på rem");
        universalbeslagVenstre.setPrice(LogicFacade.getPrice(universalbeslagVenstre.getId()));
        metalMaterials.add(universalbeslagVenstre);
        // Skruer til stern og vandbræt
        Material skruer = LogicFacade.getMaterialByID(14);
        skruer.setName("SKRUER");
        skruer.setAmount(ConstructionSizeCalculator.screwAmount);
        skruer.setUnit(LogicFacade.getUnitByName(skruer.getName()));
        skruer.setId(14);
        skruer.setWidth(LogicFacade.getWidthByID(skruer.getId(), skruer.getName()));
        skruer.setThickness(LogicFacade.getThicknessByID(skruer.getId()));
        skruer.setName("SKRUER " + skruer.getThickness() + "x" + skruer.getWidth());
        skruer.setComment("Til montering af stern&vandbrædt");
        skruer.setPrice(LogicFacade.getPrice(skruer.getId()));
        metalMaterials.add(skruer);

        return metalMaterials;
    }


}