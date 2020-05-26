package FunctionLayer;

import DBAccess.MaterialMapper;

import java.util.ArrayList;

public class PitchedRoofMaterialCalculator {

    private int numberOfTaglaegter; // T1 taglægter til spær
    private int numberOfStern;
    private int numberOfToplaegteHolder;
    private int numberOfToplaegte; // T1 toplægter til rygsten
    private int numberOfTagfodsLaegte;
    private int numberOfVindskeder;
    private int numberOfVandbraet;
    private int SCREWSPERTAGLÆGTEHOLDER = 5;
    private int LÆGTESTENDISTANCE = 307;
    private int RYGSTENCOVERS = 330;
    private int tagstenNakkekrog;
    private int screwForTaglægter;
    private int screwsForVindskeder;
    private int screwsForVandbræt;
    private int beslagForToplægte;
    private int vindskedeLængde;
    private int vandBrætsLength;
    private int tagstenEntirePitchedRoof;
    //NOTE: tagpladerne fylder 307mm i længden og vi antager 200mm i bredden, derfor disse værdier for tagsten.
    private int tagstenBredde;
    private int rygstenBeslag;
    private int spærFullPlankLength;
    private int spærAmount;
    private int spærFullQuatityOfPlanks;
    private int amountOfRygsten;

    ConstructionSizeCalculator constructionSizeCalculator;
    Construction construction;
    RoofSizing roofSizing;
    Roof roof;


    public PitchedRoofMaterialCalculator(Construction construction) {
        this.construction = construction;
        roofSizing = new RoofSizing(construction);
        roof = construction.getRoof();
    }

    //todo: fix skruer til vandbræt - beregning (amount = 0?)

    //........Pitched Roof Material list.......
    public ArrayList<Material> pitchedRoof() throws LoginSampleException {

        System.out.println("I am in PitchedRoofMAtCalc. pitchedRoof() - hello!");
        ArrayList<Material> pitchedRoofMaterialList  = new ArrayList<>();

        //Toplægter
        Material toplaegter = new Material();

        //Name, comment, size, amount

        toplaegter.setName("TOPLÆGTE");
        int quantityOfToplaegter = amountOfT1_RygstenLength();
        toplaegter.setAmount(quantityOfToplaegter);
        toplaegter.setUnit(LogicFacade.getUnitByName(toplaegter.getName()));
        toplaegter.setComment("Lægte til montering af rygsten - lægges i toplægte holder");
        toplaegter.setCategory("RejsningTag Konstruktion");
        toplaegter.setId(38);
        toplaegter.setPrice(LogicFacade.getPrice(toplaegter.getId()));


        //TEST
        System.out.println("name: "+toplaegter.getName());
        System.out.println("amount: "+toplaegter.getAmount());
        System.out.println("comment: "+toplaegter.getComment());
        System.out.println("category: "+toplaegter.getCategory());
        System.out.println("unit "+toplaegter.getUnit());
        System.out.println("price "+toplaegter.getPrice());


        pitchedRoofMaterialList.add(toplaegter);

        //Taglægter
        Material taglaegter = new Material();

        taglaegter.setName("TAGLÆGTE");
        int quantityOfTaglaegter = amountOfT1_Spaer_Taglaegter();
        taglaegter.setAmount(quantityOfTaglaegter);
        taglaegter.setUnit(LogicFacade.getUnitByName(toplaegter.getName()));
        taglaegter.setComment("Lægte til montering på spær");
        taglaegter.setCategory("RejsningTag Konstruktion");
        taglaegter.setId(30);
        taglaegter.setPrice(LogicFacade.getPrice(taglaegter.getId()));

        //TEST
        System.out.println("name: "+taglaegter.getName());
        System.out.println("amount: "+taglaegter.getAmount());
        System.out.println("comment: "+taglaegter.getComment());
        System.out.println("category: "+taglaegter.getCategory());
        System.out.println("unit "+taglaegter.getUnit());
        System.out.println("price "+taglaegter.getPrice());

        pitchedRoofMaterialList.add(taglaegter);


        //Sternbrædder
        Material stern = new Material();

        stern.setName("STERNBRÆT");
        int quantityOfStern = amountOfStern();
        stern.setAmount(quantityOfStern);
        stern.setUnit(LogicFacade.getUnitByName(stern.getName()));
        stern.setComment("Bræt påsømmes enderne af tagspærene - dækker og beskytter spærene");
        stern.setCategory("RejsningTag Konstruktion");
        stern.setId(28);
        stern.setPrice(LogicFacade.getPrice(stern.getId()));

        //TEST
        System.out.println("name: "+stern.getName());
        System.out.println("amount: "+stern.getAmount());
        System.out.println("comment: "+stern.getComment());
        System.out.println("category: "+stern.getCategory());
        System.out.println("unit "+stern.getUnit());
        System.out.println("price "+stern.getPrice());

        pitchedRoofMaterialList.add(stern);

        //Vandbræt
        Material vandbraet = new Material();

        vandbraet.setName("VANDBRÆT");
        int quantityOfVandbraet = amountOfVandbraet();
        vandbraet.setAmount(quantityOfStern);
        vandbraet.setUnit(LogicFacade.getUnitByName(vandbraet.getName()));
        vandbraet.setComment("Bræt til montering på vindskeder -  beskytter mod fugtfæld");
        vandbraet.setCategory("RejsningTag Konstruktion");
        vandbraet.setId(29);
        vandbraet.setPrice(LogicFacade.getPrice(vandbraet.getId()));

        //TEST
        System.out.println("name: "+vandbraet.getName());
        System.out.println("amount: "+vandbraet.getAmount());
        System.out.println("comment: "+vandbraet.getComment());
        System.out.println("category: "+vandbraet.getCategory());
        System.out.println("unit "+vandbraet.getUnit());
        System.out.println("price "+vandbraet.getPrice());

        pitchedRoofMaterialList.add(vandbraet);

        //Vindskeder
        Material vindskeder = new Material();

        vindskeder.setName("VINDSKEDER");
        int quantityOfVindskeder = amountOfVindskeder();
        vindskeder.setAmount(quantityOfVindskeder);
        vindskeder.setUnit(LogicFacade.getUnitByName(vindskeder.getName()));
        vindskeder.setComment("Bræt til montering på tag rejsning som afslutning på tagpaptage");
        vindskeder.setCategory("RejsningTag Konstruktion");
        vindskeder.setId(27);
        vindskeder.setPrice(LogicFacade.getPrice(vindskeder.getId()));



        //TEST
        System.out.println("name: "+vindskeder.getName());
        System.out.println("amount: "+vindskeder.getAmount());
        System.out.println("comment: "+vindskeder.getComment());
        System.out.println("category: "+vindskeder.getCategory());
        System.out.println("unit "+vindskeder.getUnit());
        System.out.println("price "+vindskeder.getPrice());

        pitchedRoofMaterialList.add(vindskeder);


        //TagfodsLægte
        Material tagfodsLaegte = new Material();

        tagfodsLaegte.setName("TAGFODSLÆGTE BRÆDT");
        int quantityOfTagfodsLaegte = amountOfTagfodsLaegte();
        tagfodsLaegte.setAmount(quantityOfTagfodsLaegte);
        tagfodsLaegte.setUnit(LogicFacade.getUnitByName(tagfodsLaegte.getName()));
        tagfodsLaegte.setComment("Lægte til tagfod");
        tagfodsLaegte.setCategory("Rejsning Konstruktion");
        tagfodsLaegte.setId(31);
        tagfodsLaegte.setPrice(LogicFacade.getPrice(tagfodsLaegte.getId()));

        //TEST
        System.out.println("name: "+tagfodsLaegte.getName());
        System.out.println("amount: "+tagfodsLaegte.getAmount());
        System.out.println("comment: "+tagfodsLaegte.getComment());
        System.out.println("category: "+tagfodsLaegte.getCategory());
        System.out.println("unit "+tagfodsLaegte.getUnit());
        System.out.println("price "+tagfodsLaegte.getPrice());


        pitchedRoofMaterialList.add(tagfodsLaegte);

        //Rygsten
        Material rygsten = new Material();

        rygsten.setName("RYGSTEN");
        int quantityOfRygsten = quantityRygsten();
        rygsten.setAmount(quantityOfRygsten);
        rygsten.setUnit(LogicFacade.getUnitByName(rygsten.getName()));
        rygsten.setComment("Monteres på toplægte");
        rygsten.setCategory("RejsningTag Konstruktion");
        rygsten.setId(39);
        rygsten.setPrice(LogicFacade.getPrice(rygsten.getId()));

        //TEST
        System.out.println("name: "+rygsten.getName());
        System.out.println("amount: "+rygsten.getAmount());
        System.out.println("comment: "+rygsten.getComment());
        System.out.println("category: "+rygsten.getCategory());
        System.out.println("unit "+rygsten.getUnit());
        System.out.println("price "+rygsten.getPrice());

        pitchedRoofMaterialList.add(rygsten);

        // ** Beslag **

        System.out.println("** BESLAG **");
        //toplægteHolder
        Material toplaegteHolder = new Material();

        toplaegteHolder.setName("TOPLÆGTEHOLDER");
        int quantityOfToplaegteHolder = amountOfToplaegteHolder();
        toplaegteHolder.setAmount(quantityOfToplaegteHolder);
        toplaegteHolder.setUnit(LogicFacade.getUnitByName(toplaegteHolder.getName()));
        toplaegteHolder.setComment("monteres på toppen af spæret af toplægte");
        toplaegteHolder.setCategory("RejsningTag Konstruktion");
        toplaegteHolder.setId(34);
        toplaegteHolder.setPrice(LogicFacade.getPrice(toplaegteHolder.getId()));

        //TEST
        System.out.println("name: "+toplaegteHolder.getName());
        System.out.println("amount: "+toplaegteHolder.getAmount());
        System.out.println("comment: "+toplaegteHolder.getComment());
        System.out.println("category: "+toplaegteHolder.getCategory());
        System.out.println("unit "+toplaegteHolder.getUnit());
        System.out.println("price "+toplaegteHolder.getPrice());


        pitchedRoofMaterialList.add(toplaegteHolder);

        //Taglægter skruer
        Material taglaegterSkruer = new Material();

        taglaegterSkruer.setName("TAGLÆGTER SKRUER");
        int quantityOfTaglaegteScrews = screwForTaglægterCalculated();
        taglaegterSkruer.setAmount(quantityOfTaglaegteScrews);
        taglaegterSkruer.setUnit(LogicFacade.getUnitByName(taglaegterSkruer.getName()));
        taglaegterSkruer.setComment("Skruer til taglægter");
        taglaegterSkruer.setCategory("RejsningTag Konstruktion");
        taglaegterSkruer.setId(37);
        taglaegterSkruer.setPrice(LogicFacade.getPrice(taglaegterSkruer.getId()));

        //TEST
        System.out.println("name: "+taglaegterSkruer.getName());
        System.out.println("amount: "+taglaegterSkruer.getAmount());
        System.out.println("comment: "+taglaegterSkruer.getComment());
        System.out.println("category: "+taglaegterSkruer.getCategory());
        System.out.println("unit "+taglaegterSkruer.getUnit());
        System.out.println("price "+taglaegterSkruer.getPrice());

        pitchedRoofMaterialList.add(taglaegterSkruer);


        //Vandbræt skruer
        Material vandbraetSkruer = new Material();

        vandbraetSkruer.setName("SKRUER");
        int quantityOfVandbraerScrews = screwsForVandbrætCalculated();
        vandbraetSkruer.setAmount(quantityOfVandbraerScrews);
        vandbraetSkruer.setUnit(LogicFacade.getUnitByName(vandbraetSkruer.getName()));
        vandbraetSkruer.setComment("Skruer til vandbræt");
        vandbraetSkruer.setCategory("RejsningTag Konstruktion");
        vandbraetSkruer.setId(35);
        vandbraetSkruer.setPrice(LogicFacade.getPrice(vandbraetSkruer.getId()));

        //TEST
        System.out.println("name: "+vandbraetSkruer.getName());
        System.out.println("amount: "+vandbraetSkruer.getAmount());
        System.out.println("comment: "+vandbraetSkruer.getComment());
        System.out.println("category: "+vandbraetSkruer.getCategory());
        System.out.println("unit "+vandbraetSkruer.getUnit());
        System.out.println("price "+vandbraetSkruer.getPrice());

        pitchedRoofMaterialList.add(vandbraetSkruer);

        //toplægte skruer
        Material toplægteSkruer = new Material();

        toplægteSkruer.setName("SKRUER");
        int quantityOfToplaegteScrews = amountOfBeslagScrewsForToplægteCalculated();
        toplægteSkruer.setAmount(quantityOfToplaegteScrews);
        toplægteSkruer.setUnit(LogicFacade.getUnitByName(toplægteSkruer.getName()));
        toplægteSkruer.setComment("Skruer til toplægter");
        toplægteSkruer.setCategory("RejsningTag Konstruktion");
        toplægteSkruer.setId(35);
        toplægteSkruer.setPrice(LogicFacade.getPrice(toplægteSkruer.getId()));

        //TEST
        System.out.println("name: "+toplægteSkruer.getName());
        System.out.println("amount: "+toplægteSkruer.getAmount());
        System.out.println("comment: "+toplægteSkruer.getComment());
        System.out.println("category: "+toplægteSkruer.getCategory());
        System.out.println("unit "+toplægteSkruer.getUnit());
        System.out.println("price "+toplægteSkruer.getPrice());

        pitchedRoofMaterialList .add(toplægteSkruer);


        //Vindskeder skruer
        Material vindskederSkruer = new Material();

        vindskederSkruer.setName("SKRUER");
        int quantityOfVindskederScrews = screwsForVindskederCalculated();
        vindskederSkruer.setAmount(quantityOfVindskederScrews);
        vindskederSkruer.setUnit(LogicFacade.getUnitByName(vindskederSkruer.getName()));
        vindskederSkruer.setComment("Skruer til vindskeder");
        vindskederSkruer.setCategory("RejsningTag Konstruktion");
        vindskederSkruer.setId(35);
        vindskederSkruer.setPrice(LogicFacade.getPrice(vindskederSkruer.getId()));

        //TEST
        System.out.println("name: "+vindskederSkruer.getName());
        System.out.println("amount: "+vindskederSkruer.getAmount());
        System.out.println("comment: "+vindskederSkruer.getComment());
        System.out.println("category: "+vindskederSkruer.getCategory());
        System.out.println("unit "+vindskederSkruer.getUnit());
        System.out.println("price "+vindskederSkruer.getPrice());

        pitchedRoofMaterialList.add(vindskederSkruer);

        //toplægteHolder skruer
        Material toplaegterHolderSkruer = new Material();

        toplaegterHolderSkruer.setName("TOPLÆGTE BESLAGSKRUER");
        int quantityOfToplaegteHolderScrews = amountOfBeslagScrewsForToplægteCalculated();
        toplaegterHolderSkruer.setAmount(quantityOfToplaegteHolderScrews);
        toplaegterHolderSkruer.setUnit(LogicFacade.getUnitByName(toplaegterHolderSkruer.getName()));
        toplaegterHolderSkruer.setComment("Skruer til toplægteholder");
        toplaegterHolderSkruer.setCategory("RejsningTag Konstruktion");
        toplaegterHolderSkruer.setId(36);
        toplaegterHolderSkruer.setPrice(LogicFacade.getPrice(toplaegterHolderSkruer.getId()));

        //TEST
        System.out.println("name: "+toplaegterHolderSkruer.getName());
        System.out.println("amount: "+toplaegterHolderSkruer.getAmount());
        System.out.println("comment: "+toplaegterHolderSkruer.getComment());
        System.out.println("category: "+toplaegterHolderSkruer.getCategory());
        System.out.println("unit "+toplaegterHolderSkruer.getUnit());
        System.out.println("price "+toplaegterHolderSkruer.getPrice());

        pitchedRoofMaterialList.add(toplaegterHolderSkruer);


        //Rygsten Beslag
        Material rygstenBeslag = new Material();

        rygstenBeslag.setName("RYGSTENBESLAG");
        int quantityOfRygstenBeslag =  amoutOfRygstenBeslagCalculated();
        rygstenBeslag.setAmount(quantityOfRygstenBeslag);
        rygstenBeslag.setUnit(LogicFacade.getUnitByName(rygstenBeslag.getName()));
        rygstenBeslag.setComment("Beslag til rygsten");
        rygstenBeslag.setCategory("RejsningTag Konstruktion");
        rygstenBeslag.setId(40);
        rygstenBeslag.setPrice(LogicFacade.getPrice(rygstenBeslag.getId()));

        //TEST
        System.out.println("name: "+rygstenBeslag.getName());
        System.out.println("amount: "+rygstenBeslag.getAmount());
        System.out.println("comment: "+rygstenBeslag.getComment());
        System.out.println("category: "+rygstenBeslag.getCategory());
        System.out.println("unit " +rygstenBeslag.getUnit());
        System.out.println("price " +rygstenBeslag.getPrice());

        pitchedRoofMaterialList.add(rygstenBeslag);

        //Nakkekrog til eternittagB7
        Material tagstenNakkekrog = new Material();

        tagstenNakkekrog.setName("TAGSTEN NAKKEKROGE");
        int quantityOfNakkekrog = tagstenNakkekrogeCalculated();
        tagstenNakkekrog.setAmount(quantityOfNakkekrog);
        tagstenNakkekrog.setUnit(LogicFacade.getUnitByName(tagstenNakkekrog.getName()));
        tagstenNakkekrog.setComment("Til montering af eternittagB7");
        tagstenNakkekrog.setCategory("RejsningTag Konstruktion");
        tagstenNakkekrog.setId(33);
        tagstenNakkekrog.setPrice(LogicFacade.getPrice(tagstenNakkekrog.getId()));

        //TEST
        System.out.println("name: "+tagstenNakkekrog.getName());
        System.out.println("amount: "+tagstenNakkekrog.getAmount());
        System.out.println("comment: "+tagstenNakkekrog.getComment());
        System.out.println("category: "+tagstenNakkekrog.getCategory());
        System.out.println("unit "+tagstenNakkekrog.getUnit());
        System.out.println("price "+tagstenNakkekrog.getPrice());

        pitchedRoofMaterialList.add(tagstenNakkekrog);

        //Bindere til eternittagB7
        Material tagstenBindere = new Material();

        tagstenBindere.setName("TAGSTEN BINDERE");
        int quantityOfBindere = tagstenBindereCalculated();
        tagstenBindere.setAmount(quantityOfBindere);
        tagstenBindere.setUnit(LogicFacade.getUnitByName(tagstenBindere.getName()));
        tagstenBindere.setComment("Til montering af eternittagB7");
        tagstenBindere.setCategory("RejsningTag Konstruktion");
        tagstenBindere.setId(32);
        tagstenBindere.setPrice(LogicFacade.getPrice(tagstenBindere.getId()));

        //TEST
        System.out.println("name: "+tagstenBindere.getName());
        System.out.println("amount: "+tagstenBindere.getAmount());
        System.out.println("comment: "+tagstenBindere.getComment());
        System.out.println("category: "+tagstenBindere.getCategory());
        System.out.println("unit "+tagstenBindere.getUnit());
        System.out.println("price "+tagstenBindere.getPrice());

        pitchedRoofMaterialList.add(tagstenBindere);

        //spær til taglægter + rygsten
        Material tagSpaer = new Material();

        tagSpaer.setName("SPÆR");
        int quantityOfSpaer = constructionSizeCalculator.roofSpaerAmount(construction);
        tagSpaer.setAmount(quantityOfSpaer);
        tagSpaer.setUnit(LogicFacade.getUnitByName(tagSpaer.getName()));
        tagSpaer.setComment("Spær til taglægter og rysgten");
        tagSpaer.setCategory("RejsningTag Konstruktion");
        tagSpaer.setId(58);
        tagSpaer.setPrice(LogicFacade.getPrice(tagSpaer.getId()));

        //TEST
        System.out.println("name: "+tagSpaer.getName());
        System.out.println("amount: "+tagSpaer.getAmount());
        System.out.println("comment: "+tagSpaer.getComment());
        System.out.println("category: "+tagSpaer.getCategory());
        System.out.println("unit "+tagSpaer.getUnit());
        System.out.println("price "+tagSpaer.getPrice());

        pitchedRoofMaterialList.add(tagSpaer);

        //** Tagplader **
        System.out.println("** TAGPLADER ** ");

        //Beton
        Material betontagsten = new Material();

        betontagsten.setName("BETONTAGSTEN");
        int quantityOfTagstenBeton = amountOfTagsten();
        betontagsten.setAmount(quantityOfTagstenBeton);
        betontagsten.setId(24);
        betontagsten.setUnit(LogicFacade.getUnitByName(betontagsten.getName()));
        betontagsten.setComment("Tagplader til tag med rejsning");
        betontagsten.setCategory("RejsningTag Konstruktion");
        betontagsten.setPrice(LogicFacade.getPrice(betontagsten.getId()));

        //TEST
        System.out.println("name: "+betontagsten.getName());
        System.out.println("amount: "+betontagsten.getAmount());
        System.out.println("comment: "+betontagsten.getComment());
        System.out.println("category: "+betontagsten.getCategory());
        System.out.println("unit "+betontagsten.getUnit());
        System.out.println("price "+betontagsten.getPrice());


        pitchedRoofMaterialList.add(betontagsten);

        //Eternittag B6
        Material eternittagB6 = new Material();

        eternittagB6.setName("ETERNITTAG B6");
        int quantityOfTagstenB6 = amountOfTagsten();
        eternittagB6.setAmount(quantityOfTagstenB6);
        eternittagB6.setId(25);
        eternittagB6.setUnit(LogicFacade.getUnitByName(eternittagB6.getName()));
        eternittagB6.setComment("Tagplader til tag med rejsning");
        eternittagB6.setCategory("RejsningTag Konstruktion");
        eternittagB6.setPrice(LogicFacade.getPrice(eternittagB6.getId()));

        //TEST
        System.out.println("name: "+eternittagB6.getName());
        System.out.println("amount: "+eternittagB6.getAmount());
        System.out.println("comment: "+eternittagB6.getComment());
        System.out.println("category: "+eternittagB6.getCategory());
        System.out.println("unit "+eternittagB6.getUnit());
        System.out.println("price "+eternittagB6.getPrice());

        pitchedRoofMaterialList.add(eternittagB6);

        //Eternittag B7
        Material eternittagB7 = new Material();

        eternittagB7.setName("ETERNITTAG B7");
        int quantityOfTagstenB7 = amountOfTagsten();
        eternittagB7.setAmount(quantityOfTagstenB7);
        eternittagB7.setId(26);
        eternittagB7.setUnit(LogicFacade.getUnitByName(eternittagB7.getName()));
        eternittagB7.setComment("Tagplader til tag med rejsning");
        eternittagB7.setCategory("RejsningTag Konstruktion");
        eternittagB7.setPrice(LogicFacade.getPrice(eternittagB7.getId()));

        //TEST
        System.out.println("name: " +eternittagB7.getName());
        System.out.println("amount: " +eternittagB7.getAmount());
        System.out.println("comment: "+eternittagB7.getComment());
        System.out.println("category: "+eternittagB7.getCategory());
        System.out.println("unit "+eternittagB7.getUnit());
        System.out.println("price "+eternittagB7.getPrice());

        pitchedRoofMaterialList.add(eternittagB7);


        System.out.println("færdig med liste - YASS!!! ");


        return pitchedRoofMaterialList;
    }


    //........ CALCULATIONS .........


    public int amoutOfRygstenBeslagCalculated() {
        rygstenBeslag = quantityRygsten();
        return rygstenBeslag;
    }


    public int amountOfTagsten(){
        //NOTE: tagpladerne fylder 307mm i længden og vi antager 200mm i bredden, derfor disse værdier.
        tagstenBredde = 200;
        int tagstenHalfePitchedRoof = 0;
        //Vi trækker ikke en tagstenbredde fra i tagets længde i for-loopet fordi vi vil have det hele antal + en hvis
        // der er en rest
        for (int i = 0; i < roofSizing.roofWidthSurface()- LÆGTESTENDISTANCE; i= i + LÆGTESTENDISTANCE) {
            for (int j = 0; j < roofSizing.roofLengthSurface(); j = j + tagstenBredde) {
                tagstenHalfePitchedRoof++;
            }
        }
        tagstenEntirePitchedRoof = tagstenHalfePitchedRoof * 2;
        return tagstenEntirePitchedRoof;
    }

    public int tagstenBindereCalculated(){
        int tagstenBinder = amountOfTagsten();
        for (int i = 0; i < tagstenBinder; i = i+2) {
            tagstenBinder ++;
        }
        return tagstenBinder;
    }

    public int tagstenNakkekrogeCalculated(){
        //Vi formoder der er en tagstensnakkekrog pr. tagsten
        tagstenNakkekrog = amountOfTagsten();
        return tagstenNakkekrog;
    }

    public int screwForTaglægterCalculated(){
        //Vi antager der er er en skrue pr toplægteholder + samt et pr spær for at sætte toplægten fast
        screwForTaglægter = amountOfToplaegteHolder() * SCREWSPERTAGLÆGTEHOLDER;
        return screwForTaglægter;
    }

    public int screwsForVindskederCalculated(){
        //Vi antager der bruges en skrue for hvert 50cm
        vindskedeLængde = (int)(Math.hypot((construction.getConstructionWidth()/2.0), (double) (roofSizing.roofHeight(
                construction.getRoof().getIsPitched(), construction.getConstructionLength(),
                construction.getConstructionWidth()))));
        for (int i = 50; i < vindskedeLængde-50 ; i = i + 50) {
            screwsForVindskeder++;
        }
        return screwsForVindskeder;
    }

    public int screwsForVandbrætCalculated(){
        //Vi antager der skal bruges til hver 300 mm en skrue, med mindst 10 mm fra sidste kant
        vandBrætsLength = construction.getRoof().getLength();
        for (int i = 300; i < vandBrætsLength -10 ; i = i + 300) {
            screwsForVandbræt++;
        }
        return screwsForVandbræt;
    }

    /*public int AmountOfScrewPackages(){
        int screwsTotal = screwForTaglægterCalculated() + screwsForVindskederCalculated()
                + screwsForVandbrætCalculated();
        screwPackage = Math.round(screwsTotal/200);
        return screwPackage;
    }*/

    //todo: fix amountOfBeslagScrewsForToplægteCalculated() line 620
    public int amountOfBeslagScrewsForToplægteCalculated(){
        beslagForToplægte = 9*2* amountOfT1_RygstenLength();
        return beslagForToplægte;
    }


    public int spærPlankLengthPerSpær(){
        int spærfodLength = construction.getCarportWidth();
        int spærArm = (int) (spærfodLength/(Math.cos(Math.toRadians(construction.getRoof().getDegree()))))*2;
        spærFullPlankLength = (spærArm*2)+spærfodLength;
        return spærFullPlankLength;
    }
    //TODO beregning af ekstra spær (til sidst)
    public int spærQuantity(){
        int carportLength = construction.getCarportLength();
        int distanceBestweenSpær = 89;
        for (int i = 0; i < carportLength; i = 1 + distanceBestweenSpær) {
            spærAmount++;
        }
        if (construction.getShed() != null) {
            spærAmount = spærAmount+ 2;
        }
        return spærAmount;
    }

    public int spærFullQuantityOfPlanksTotal(){
        spærFullQuatityOfPlanks = spærPlankLengthPerSpær()* spærQuantity();
        return spærFullQuatityOfPlanks;
    }

    public int gavlOverlayQuantity(int overlayPlankWidthKonstant, int overlayPlankLenghtAvailable){
        int gavlOverlayPlanksQuantity = 0;
        int lengthOfTriangleGavl = construction.getCarportWidth();
        int lenghtOfTriangleGavlShorter = construction.getCarportWidth();
        int restTotal;
        int restUseable = 1;
        int roofHeight = construction.getRoof().getHeight();
        double newHeight = construction.getRoof().getHeight();
        int roofAngleInTop = (construction.getRoof().getDegree())*2;
        int lengthOfHalfRoofWidthSurface = roofSizing.roofWidthSurface();
        int overlayPlankWidth;
        double kFactor;
        double tempHeigth;

        for (int i = 0; i < roofHeight-1; i = i + overlayPlankWidth) {
            overlayPlankWidth = overlayPlankWidthKonstant;
            gavlOverlayPlanksQuantity++;
            restTotal = overlayPlankLenghtAvailable % lenghtOfTriangleGavlShorter;
            if ( restTotal != 0){
                restUseable = overlayPlankLenghtAvailable/restTotal;
                gavlOverlayPlanksQuantity ++;
                overlayPlankWidth = overlayPlankWidth * restUseable;
            }
            tempHeigth = newHeight;
            if (overlayPlankWidth<newHeight)
                newHeight = newHeight - overlayPlankWidth;
            else
                newHeight = overlayPlankWidth - newHeight;
            kFactor = newHeight/tempHeigth;

            lenghtOfTriangleGavlShorter = (int) (kFactor * lenghtOfTriangleGavlShorter);
        }
        if (lenghtOfTriangleGavlShorter !=0 )
            return (int) gavlOverlayPlanksQuantity + 1;

        return (int) gavlOverlayPlanksQuantity;
    }


    public int quantityRygsten() {
        amountOfRygsten = construction.getConstructionLength() / RYGSTENCOVERS;
        if (amountOfRygsten% RYGSTENCOVERS != 0 )
            amountOfRygsten++;
        return amountOfRygsten;
    }

    //Vi antager at der herfra og ned er et slags materiale og derfor disse beregninger:

    //** Beregning af antal taglægter i forhold til tagets bredde - Remember: tilpas med t1_SpaerLength!? **
    private int amountOfT1_Spaer_Taglaegter()
    {
        int roofWidth = roof.getWidth();
        int T1_SpaerDistance = 307; // 307 mm mellem hvert lægte - dog ikke den første
        int topDistance = 30; // 30 mm på hver side dvs * 2

        numberOfTaglaegter = roofWidth - (topDistance * 2)/T1_SpaerDistance + 2; // 2 = 350mm bræt
        return numberOfTaglaegter;
    }

    // ** Beregning af antal sternbrædder i forhold til tagets længde - stern skal have samme længde som taget + 300 mm**
    public int amountOfStern()
    {
        int roofLength = roof.getLength();
        int sternLength = roofLength + 300; //tag længde + 300mm lægte udhæng

        if (roofLength <= 600 ) //600 mm = 1 stern længde - if roofLength equal/smaller than 600
        {
            numberOfStern = 4; // 2 on each side
        }
        else if (roofLength > 600) //if bigger than 600
        {
            numberOfStern = 6; // 4 on each side
        }
        else
        {
            numberOfStern = 8;
        }

        return numberOfStern;
    }

    //** Beregning af antal Toplægteholdere i forhold til spær (beslag)
    private int amountOfToplaegteHolder()
    {
        int spaer = constructionSizeCalculator.roofSpaerAmount(construction);
        int toplaegteholder = spaer;

        numberOfToplaegteHolder = toplaegteholder * spaer;
        return numberOfToplaegteHolder;
    }

    //** Beregning af T1 toplægte (til rygsten) i forhold til tag længde **
    public int amountOfT1_RygstenLength()
    {
        int roofLength = roof.getLength();

        //int toplaegteLength = 420; // 420 mm = 1 toplægte længde

        if(roofLength <= 840) // stk af 420 dvs *2= 840
        {
            numberOfToplaegte = 2;
        }
        else if (roofLength > 840)
        {
            numberOfToplaegte = 4;
        }
        else
        {
            numberOfToplaegte = 6;
        }
        return numberOfToplaegte;
    }

    //** Beregning af antal tagfodslægte i forhold til taget længde **
    public int amountOfTagfodsLaegte ()
    {
        int roofLength = roof.getLength();

        if (roofLength <= 1620 ) //340 * 3stk ---> 340 mm = længde af 1 tagfodslægte
        {
            numberOfTagfodsLaegte = 3;
        }
        else if (roofLength > 1620)
        {
            numberOfTagfodsLaegte = 6;
        }
        else {
            numberOfTagfodsLaegte = 9;
        }
        return numberOfTagfodsLaegte;
    }

    //** Beregning af antal vindskeder i forhold til tagets længde**
    public int amountOfVindskeder ()
    {
        int  roofLength = roof.getLength();
        if (roofLength <= 480 ) //if roofLength equal/smaller than 480 - længde af 1 vindskede bræt
        {
            numberOfVindskeder = 2;
        }
        else if (roofLength > 480 )
        {
            numberOfVindskeder = 4;
        }
        return numberOfVindskeder;
    }

    //** Beregning af antal vandbræt i forhold til antal vindskider ** - skal monteres på vindskider
    //Note: antal vandbræt = antal vindskeder.
    public  int amountOfVandbraet()
    {
        int roofLength = roof.getLength();

        if (roofLength <= 480 ) //if roofLength equal/smaller than 480 - længde af 1 vandbræt
        {
            numberOfVandbraet = 2;
        }
        else if (roofLength > 480 )
        {
            numberOfVandbraet= 4;
        }
        return numberOfVandbraet;
    }
}