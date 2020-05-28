package FunctionLayer;


import java.util.ArrayList;

public class RoofMaterialCalculator {

    // TODO gem vandbræt, bundskruer, tætningsprofil


    Construction construction;
    RoofSizing roofSizing;

    private int T300ROOFPLADELENGTH = 3000; //Dette skulle rigtig beregnes ud fra 360 cm istedet men vi prioterer
    //på andre ting i forløbet
    private int T600ROOFPLADELENGTH = 6000; // målt i cm
    private int OVERLAP = 200;

    private int numberOfT600Trapezplates;
    private int numberOfT300Trapezplates;
    private int square1numberOfT600Trapezplates = 0;
    private int square2numberOfT600Trapezplates = 0;
    private int square3numberOfT600Trapezplates = 0;
    private int roofWidth;
    private int roofLength;

    ArrayList materialsList;
    Material material;

    public RoofMaterialCalculator(Construction construction) {
        this.construction = construction;
        this.roofSizing = new RoofSizing(construction);
        this.roofWidth = roofSizing.roofWidthSurface();
        this.roofLength = roofSizing.roofLengthSurface();
    }

    public ArrayList<Material> getflatRoofMaterials() throws LoginSampleException {
        ArrayList<Material> flatRoofMaterialsList = new ArrayList();
        ArrayList<Material> flatRoofMaterialsTrapezPladesList = flatRoofMaterialsTrapezPlades(
                construction.getRoof().getColor());
        ArrayList<Material> flatRoofMaterialsRestList = flatRoofMaterialsRest();
        for (Material m: flatRoofMaterialsTrapezPladesList) {
            flatRoofMaterialsList.add(m);
        }
        for (Material mRest:flatRoofMaterialsRestList) {
            flatRoofMaterialsList.add(mRest);
        }
        construction.getRoof().setRoofMaterialList(flatRoofMaterialsList);
        return construction.getRoof().getRoofMaterialList();
    }

    public ArrayList<Material> flatRoofMaterialsTrapezPlades(String color) throws LoginSampleException { //TODO - TEST
        materialsList = new ArrayList();
        material = null;

        //TrapezPlader
        String nameTrapez = "TRAPEZPLADE";
        int TRAPEZPLADEICM = T600ROOFPLADELENGTH/10;
        material = LogicFacade.getMaterialByNameColourAndSize(nameTrapez, TRAPEZPLADEICM, color);
        material.setName(nameTrapez);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName( nameTrapez + " "+ color + " " + material.getThickness() + "x" + material.getWidth());
        material.setAvailablesize(T600ROOFPLADELENGTH);
        int quantityOfT600 = quantityOfT600ForRoof(material.getWidth());
        material.setAmount(quantityOfT600);
        material.setComment("tagplader monteres på spær");
        material.setPrice(LogicFacade.getPrice(material.getId()));
        if (quantityOfT600 != 0) {
            materialsList.add(material);
        }
        material = new Material();

        TRAPEZPLADEICM = T300ROOFPLADELENGTH/10;
        material = LogicFacade.getMaterialByNameColourAndSize(nameTrapez, TRAPEZPLADEICM, color);
        material.setName(nameTrapez);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName(nameTrapez + " "+ color + " " + material.getThickness() + "x" + material.getWidth());
        material.setAvailablesize(T300ROOFPLADELENGTH);
        int quantityOfT300 = quantityOfT300ForRoof(material.getWidth());
        material.setAmount(quantityOfT300);
        material.setComment("tagplader monteres på spær");
        material.setPrice(LogicFacade.getPrice(material.getId()));

        materialsList.add(material);
        return materialsList;
    }

    public ArrayList<Material> flatRoofMaterialsRest() throws LoginSampleException {
        materialsList = new ArrayList();

        // TRÆ OG ANDET:

        // Vandbræt
        //oversternboartU360 på forende
        String oversternsbræt = "STERNBRÆDT";
        material.setAvailablesize(360);
        material = LogicFacade.getMaterialBySizeName(material.getAvailablesize(), oversternsbræt);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName(oversternsbræt + " " + material.getThickness() + "x" + material.getWidth());
        material.setAmount(understernboartU360(construction.getConstructionLength(),construction.getConstructionWidth()));
        material.setComment("vandbrædt på stern i forenden");
        material.setAvailablesize(360);

        materialsList.add(material);
        material = new Material();

        //oversternboartU540 på siderne
        material.setAvailablesize(540);
        material = LogicFacade.getMaterialBySizeName(material.getAvailablesize(), oversternsbræt);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName(oversternsbræt + " " + material.getThickness() + "x" + material.getWidth());
        material.setAmount(understernboartU540(construction.getConstructionLength(),construction.getConstructionWidth()));
        material.setComment("vandbrædt på stern i siderne");
        material.setAvailablesize(540);

        materialsList.add(material);
        material = new Material();

        // Tætningsprofil
        String tætningsprofilName = "TÆTNINGSPROFIL";
        ArrayList<Integer> tætningsprofillength = LogicFacade.getLengthForMaterials(tætningsprofilName);
        material.setAvailablesize(tætningsprofillength.get(0)); //Dette kan vi fordi vi kun har en størrelse
        material = LogicFacade.getMaterialBySizeName(material.getAvailablesize(), tætningsprofilName);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName(material.getName() + " " + material.getThickness() + "x" + material.getWidth());
        material.setAmount(gasket(construction.getConstructionWidth()));
        material.setComment("Tætningsprofil til trapezplader");
        material.setAvailablesize(tætningsprofillength.get(0));

        materialsList.add(material);
        material = new Material();

        // SKRUER OG BESLAG:
        // Bundskruer
        String bundskruerName = "BUNDSKRUER";
        ArrayList<Integer> quantityBottomScrews = LogicFacade.getLengthForMaterials(tætningsprofilName);
        int quantityBottomScrewsPackages = bottomScrews(construction.getConstructionLength(),
                construction.getConstructionWidth());
        material.setAmount(quantityBottomScrewsPackages);
        material = LogicFacade.getMaterialBySizeName(0, bundskruerName);
        material.setUnit(LogicFacade.getUnitByName(material.getName()));
        material.setWidth(LogicFacade.getWidthByID(material.getId(), material.getName()));
        material.setThickness(LogicFacade.getThicknessByID(material.getId()));
        material.setName(material.getName() + " " + material.getThickness() + "x" + material.getWidth());
        material.setAmount(gasket(construction.getConstructionWidth()));
        material.setComment("Skruer til tagplade og spær");

        materialsList.add(material);

        return materialsList;
    }

    ////////////////// Trapezplader - START

    //Antal T600 Trapezplader
    public int quantityOfT600ForRoof(int trapezPladeWidth) {
        ///////////////Beregning af første del af tag (hvor mange HELE T600 plader kan der være)
        trapezPladeWidth = trapezPladeWidth*10;
        int tempTrapezPladeWidth = trapezPladeWidth;
        for (int i = 0; i < (roofWidth- tempTrapezPladeWidth + OVERLAP); i = i+ tempTrapezPladeWidth) {
            for (int j = 0; j < roofLength-1; j = j+ T600ROOFPLADELENGTH) {
                square1numberOfT600Trapezplates++;
                tempTrapezPladeWidth = trapezPladeWidth*10 - OVERLAP;
            }
        }
        tempTrapezPladeWidth = trapezPladeWidth;
        /////////////////////////////////////////////////////

        /////Beregning af anden del af tag (T600 plader inkl. T600 pladerester - hvor pladerne er delt på bredden)
        for (int i = 0; i < roofLength - T600ROOFPLADELENGTH; i = i + T600ROOFPLADELENGTH) {
            square2numberOfT600Trapezplates++;
        }

        int restWidth = roofWidth % tempTrapezPladeWidth;

        int restPart;
        double temp2;

        if (restWidth != 0 ) {
            restPart = tempTrapezPladeWidth / restWidth;
            temp2 = Math.round((double)square2numberOfT600Trapezplates / restPart);
            square2numberOfT600Trapezplates = (int) temp2;
        }

        /////////////////////////////////////////////////////

        ///////////////Beregning af tredje del af tag (om hvor mange antal T600 plader der er (delt i længden))
        int quantityOfT300 = quantityOfT300ForRoof(trapezPladeWidth);

        tempTrapezPladeWidth = trapezPladeWidth;

        if (quantityOfT300 == 0) {
            for (int i = 0; i < (roofWidth- tempTrapezPladeWidth + OVERLAP) ; i = i + tempTrapezPladeWidth) {
                square3numberOfT600Trapezplates++;

            }
        }

        /////////////////////////////////////////////////////

        //Mellemregning til samlet antal plader
        numberOfT600Trapezplates = square1numberOfT600Trapezplates + square2numberOfT600Trapezplates +
                square3numberOfT600Trapezplates;

        /////////////Beregning af fjerde og sidste del af tag (om den sidste plade skal være en T600 eller T300)

        if (quantityOfT300 == 0)
            numberOfT600Trapezplates++;


        /////////////////////////////////////////////////////

        return numberOfT600Trapezplates;
    }

    //Antal T300 Trapezplader
    public int quantityOfT300ForRoof(int trapezPladeWidth) {
        int tempTrapezPladeWidth = (trapezPladeWidth*10);
        int restOfLength = roofLength % T600ROOFPLADELENGTH;
        int quantityOfT300 = 0;
        if (restOfLength > 0 && restOfLength <= T300ROOFPLADELENGTH){
            for (int i = 0; i < roofWidth - tempTrapezPladeWidth + OVERLAP; i=i+ tempTrapezPladeWidth) {
                quantityOfT300++;
                tempTrapezPladeWidth = trapezPladeWidth*10 - OVERLAP;
            }
        }
        numberOfT300Trapezplates = quantityOfT300;
        if (quantityOfT300 != 0)
            numberOfT300Trapezplates = quantityOfT300 +1;
        //(Beregning af fjerde og sidste del
        // af taget betyder det når jeg skriver +1)


        return numberOfT300Trapezplates;
    }

    ////////////////// Trapezplader - SLUT


    // TRÆ OG ANDET:
    // Understernbrædder
    public static int understernboartU360(int length, int width) {
        int antalU360;
        int lengthU360antal;
        int widthU360antal;
        if (length <= 3600) {
            lengthU360antal = 2;
        } else if (length > 5400 && length <= 7200) {
            lengthU360antal = 4;
        } else {
            lengthU360antal = 0;
        }
        if (width <= 3600) {
            widthU360antal = 2;
        } else if (width > 5400 && width <= 7200) {
            widthU360antal = 4;
        } else {
            widthU360antal = 0;
        }
        antalU360 = lengthU360antal + widthU360antal;
        return antalU360;
    }

    public static int understernboartU540(int length, int width) {
        int antalU540;
        int lengthU540antal;
        int widthU540antal;
        if (length > 3600 && length <= 5400) {
            lengthU540antal = 2;
        } else if (length > 7200 && length <= 7800) {
            lengthU540antal = 4;
        } else {
            lengthU540antal = 0;
        }
        if (width > 3600 && width <= 5400) {
            widthU540antal = 2;
        } else if (width > 7200 && width <= 7800) {
            widthU540antal = 4;
        } else {
            widthU540antal = 0;
        }
        antalU540 = lengthU540antal + widthU540antal;
        return antalU540;
    }

    // Oversternbrædder
    public static int oversternboartU360(int length, int width) {
        int oversternAntal = understernboartU360(length, width);
        return oversternAntal;
    }

    public static int oversternboartU540(int length, int width) {
        int oversternAntal = understernboartU540(length, width);
        return oversternAntal;
    }

    // Rem
    public static int rem600(int length, int width) {
        int rem600Antal;
        if (width > 600) {
            rem600Antal = 3;
        } else {
            rem600Antal = 2;
        }
        return rem600Antal;
    }

    public static int rem480(int length, int width) {
        int rem480Antal;
        if (length > 600) {
            rem480Antal = 1;
        } else if (width > 600) {
            rem480Antal = 2;
        } else {
            rem480Antal = 0;
        }
        return rem480Antal;
    }

    // Spær
    public static int raft(int length) {
        int rafts = Math.round(length / 50);
        return rafts;
    }


    // Vandbræt
    public static int vandbræt360(int length, int width) {
        int vandbrætAntal = oversternboartU360(length, width);
        return vandbrætAntal;
    }

    public static int vandbræt540(int length, int width) {
        int vandbrætAntal = oversternboartU540(length, width);
        return vandbrætAntal;
    }


    // Tætningsprofil
    public static int gasket(int width) {
        int gasket = Math.round((width / 100) * 2);
        return gasket;
    }


    // SKRUER OG BESLAG:

    // Bundskruer
    public static int bottomScrews(int length, int width) {
        // Plader fastgøres med plastmo bundskruer og skal anvendes 6 stk pr. meter på hver spær
        // Men 8 per meter på den første og den sidste spær
        double bottomScrews = (((raft(length) - 2) * 6) + 16) * (width / 100);
        double forskel = bottomScrews / 200;
        int pakker;
        if (forskel <= 1) {
            pakker = 1;
        } else if (forskel <= 2) {
            pakker = 2;
        } else {
            pakker = 3;
        }
        return pakker;
    }

    // UniversalHøjre
    public static int universalBracketsRight(int length) {
        int universalBracketsRight = raft(length);
        return universalBracketsRight;
    }

    // UniversalVenstre
    public static int universalBracketsLeft(int length) {
        int universalBracketsLeft = raft(length);
        return universalBracketsLeft;
    }

    // Skruer til vandbræt -- 1 pakke er nok til en stor carport
    public static int waterboardScrews = 1;

    // Beslagskruer
    public static int bracketScrewsRoof(int length) {
        // Beslagskruer til spær:
        int bracketScrewsS = raft(length) * 9;
        // Skal bruge antallet af pakker og der er 250 stk i 1 pakke:
        int forskel = 250 / bracketScrewsS;
        int brancketScrewPk = 0;
        if (forskel <= 1) {
            brancketScrewPk = 1;
        } else if (forskel > 1 && forskel <= 2) {
            brancketScrewPk = 2;
        } else if (forskel > 2 && forskel <= 3) {
            brancketScrewPk = 3;
        } else {
            brancketScrewPk = 4;
        }
        return brancketScrewPk;
    }


}