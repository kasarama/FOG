package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarOutputStream;

/**
 * @author Magdalena
 */
public class WallDrawer {
    final static int POSTwidth = 10;
    final static int SPAERwidth = 10;
    final static int SPAERthick = 4;
    final static int FYRwidth = 5;
    final static int FYRthick = 2;
    final static int MERGE = 25;
    final static int OVERLAYthick = 2;
    final static int DOORwidth = 90;
    final static int SPAERdist = 100;


    public static String allWallsFromAbove(Construction construction) {

        //Setup :
   /*     Construction construction = new Construction();
        construction.setCarportLength(6800);
        construction.setCarportWidth(6200);
        construction.setConstructionHeight(2000);
        Shed setupshed = new Shed(construction.getCarportWidth()/2+50, 2400, "right");
        construction.setShed(setupshed);


        construction.setShed(setupshed);
        construction.setConstructionLength();
        construction.setConstructionWidth();
        Roof roof = new RoofFlat(0, construction.getConstructionLength(), construction.getConstructionWidth(), 20);
        construction.setRoof(roof);
        ArrayList<Wall> shedWalls = WallBuilder.addShedWalls(construction);
        setupshed.setWalls(shedWalls);
        ArrayList<String> sides = new ArrayList<>();
        sides.add("back");
        sides.add("left");
        sides.add("right");

        ArrayList<Wall> carportWalls = WallBuilder.createCarportWalls(construction, sides);
        construction.setWalls(carportWalls);
*/

//........................end of setUp.................................//
        ArrayList<String > drawings = new ArrayList<>();

        Shed shed = construction.getShed();
        ArrayList<Wall> allWalls = new ArrayList<>();
        allWalls.addAll(construction.getWalls());
        allWalls.addAll(construction.getShed().getWalls());

        int canvaWidth = construction.getConstructionLength() / 10 + 2 * MERGE + DOORwidth;
        int canvaHeight = construction.getConstructionWidth() / 10 + 2 * MERGE;
        int viewWidth = 600;
        int viewLHeight = (int) canvaHeight / canvaWidth * 600;


        String viewbox = String.format("0 0 %d %d", viewWidth, viewLHeight);

        Svg svgFromAbove = new Svg(canvaWidth, canvaHeight, "", 0, 0);


        Wall right = null;
        Wall left = null;
        Wall front = null;
        Wall back = null;
        Wall carportright = null;
        Wall carportleft = null;
        Wall carportback = null;


        for (Wall wall : allWalls) {
            switch (wall.getSide()) {
                case "right":
                    right = wall;
                    break;
                case "left":
                    left = wall;
                    break;
                case "back":
                    back = wall;
                    break;
                case "front":
                    System.out.println("Wall length "+wall.getLength());
                    front = wall;
                    break;

                case "carportright":
                    carportright = wall;
                    break;

                case "carportleft":
                    carportleft = wall;
                    break;

                case "carportback":
                    carportback = wall;
                    break;
            }
        }


        posts(svgFromAbove,construction);

        if (shed.getDepth() != 0) {
            if (shed.getSide().equals("right")) {
                rightWallL(svgFromAbove, right, 0, 0);  //shed right
                leftWallL(svgFromAbove, left, 0, shed.getWidth() / 10 - POSTwidth); //shed left
                backWall(svgFromAbove, back, 0); //shed back
                frontWall(svgFromAbove, front, shed.getDepth() / 10 - POSTwidth, 0); //shed front
                System.out.println("In WallDrawer , shed on right side: front wall length: " +front.getLength()+" , where shed width is: "+construction.getShed().getWidth());

                door(svgFromAbove, shed.getDepth() / 10 - DOORwidth, shed.getWidth() / 10 - POSTwidth); //door

                if(carportleft!=null) {
                    leftWallL(svgFromAbove, left, 0, construction.getCarportWidth() / 10 - POSTwidth); //like shed left
                }

                if (carportback != null && shed.getWidth() < construction.getCarportWidth()) {
                    backWall(svgFromAbove, back, shed.getWidth() / 10 - POSTwidth); //carport back
                }


            } else {
                rightWallL(svgFromAbove, right, 0, construction.getCarportWidth() / 10 / 2 - POSTwidth / 2); //shed rigt
                leftWallL(svgFromAbove, left, 0, construction.getCarportWidth() / 10 - POSTwidth); //shed left
                backWall(svgFromAbove, back, shed.getWidth() / 10 - POSTwidth); //shed back
                frontWall(svgFromAbove, front, shed.getDepth() / 10 - POSTwidth, shed.getWidth() / 10 + DOORwidth); // shed front
                System.out.println("In WallDrawer , shed on left side: front wall length: " +front.getLength()+" , where shed width is: "+construction.getShed().getWidth());
                door(svgFromAbove, shed.getDepth() / 10, shed.getWidth() / 10); //door

                if (carportright!=null) {
                    rightWallL(svgFromAbove, right, 0, 0); // like shed right
                }

                if (carportback != null && shed.getWidth() < construction.getCarportWidth()) {
                    backWall(svgFromAbove, back, 0); //carport back
                }
            }

            if (carportright != null) {
                rightWallL(svgFromAbove, carportright, shed.getDepth() / 10 - POSTwidth, 0);
            }
            if (carportleft != null) {
                leftWallL(svgFromAbove, carportleft, shed.getDepth() / 10 - POSTwidth, construction.getCarportWidth() / 10 - 10);
            }

        } else {

            if (carportright != null) {
                rightWallL(svgFromAbove, carportright, shed.getDepth() / 10 , 0);
            }
            if (carportleft != null) {
                leftWallL(svgFromAbove, carportleft, shed.getDepth() / 10 , construction.getCarportWidth() / 10 - 10);
            }
            if (carportback!=null) {
                backWall(svgFromAbove, carportback, 0);
            }
        }
        drawings.add(0,svgFromAbove.toString());

        return svgFromAbove.toString();
    }



    public static HashMap<String, Svg> framings (Construction construction) {

/*
        //Setup :
        Construction construction = new Construction();
        construction.setCarportLength(6800);
        construction.setCarportWidth(6200);
        construction.setConstructionHeight(2000);
        Shed setupshed = new Shed(construction.getCarportWidth(), 2400, "right");
        construction.setShed(setupshed);


        construction.setShed(setupshed);
        construction.setConstructionLength();
        construction.setConstructionWidth();
        Roof roof = new RoofFlat(0, construction.getConstructionLength(), construction.getConstructionWidth(), 20);
        construction.setRoof(roof);
        ArrayList<Wall> shedWalls = WallBuilder.addShedWalls(construction);
        setupshed.setWalls(shedWalls);
        ArrayList<String> sides = new ArrayList<>();
        sides.add("back");
        sides.add("left");
        sides.add("right");

        ArrayList<Wall> carportWalls = WallBuilder.createCarportWalls(construction, sides);
        construction.setWalls(carportWalls);


*/
//........................end of setUp.................................//
        ArrayList<Svg > drawings = new ArrayList<>();
        HashMap <String, Svg> framingSvg = new HashMap<>();
        ArrayList<Wall> allWalls = new ArrayList<>();
        allWalls.addAll(construction.getWalls());
        allWalls.addAll(construction.getShed().getWalls());


        for (Wall wall :allWalls) {
            int maxH = wall.getMinHeight()/10+ (int)ConstructionSizeCalculator.raising(wall.getRaising(),wall.getLength())/10 +MERGE*2;
            int maxW = wall.getLength()/10+MERGE*2;
            Svg framing = new Svg(maxW,maxH,"",0,0);
            wallFraming(framing,wall);

            framingSvg.put(wall.getSide(),framing);
            drawings.add(framing);
        }
        return framingSvg;
    }


    public static void wallFraming (Svg svg, Wall wall) {
        int distance = ConstructionSizeCalculator.postDistanceMax3000(wall.getLength()) / 10;
        Integer[] posts = ConstructionSizeCalculator.postsHeights(wall.getMinHeight(),wall.getRaising(),wall.getLength());
        int maxHeight = (int) (wall.getMinHeight()+ConstructionSizeCalculator.raising(wall.getRaising(),wall.getLength()))/10;

        for (int i = 0; i < posts.length; i++) {
            svg.addRect((i * distance) + MERGE, maxHeight-posts[i]/10+MERGE, posts[i]/10, POSTwidth);
        }
        for (int i = 0; i < posts.length-1; i++) {
            //first spaer:
            svg.addSpaer(i*distance +POSTwidth/2 + MERGE, maxHeight-SPAERwidth + MERGE,SPAERwidth,distance);
        }

        for (int i = 0; i < posts.length-1 ; i++) {
            int pLength=posts[i]/10;
            int spareQ=(pLength-(pLength% SPAERdist))/ SPAERdist+1;
            for (int j = 0; j < spareQ-1 ; j++) {
                //middle spears:
                svg.addSpaer(i*distance +POSTwidth/2 + MERGE, 0-j*SPAERdist+maxHeight-SPAERwidth/2-SPAERdist + MERGE,SPAERwidth,distance);
            }


            ArrayList<Integer> fyrLengths = OverlaySizeCalculator.fyrLengthsOneWall(wall);
            int fyrDis= OverlaySizeCalculator.fyrDistance(wall)/10;
            for (int k = 0; k < fyrLengths.size(); k++) {
                svg.addRect((k * fyrDis)+FYRwidth/2 + MERGE, maxHeight-fyrLengths.get(k)/10+MERGE, fyrLengths.get(k)/10, FYRwidth);
            }

        }
    }




    public static void rightWallL(Svg svg, Wall wall, int x, int y) {
        int postQ = ConstructionSizeCalculator.sidePostAmount(wall.getLength());
        int distance = ConstructionSizeCalculator.postDistanceMax3000(wall.getLength()) / 10;

        for (int i = 0; i < postQ; i++) {
            svg.addRect(x + (i * distance) + MERGE, y + MERGE, POSTwidth, POSTwidth);
        }

        for (int i = 0; i < postQ - 1; i++) {
            svg.addRect(x + (int) (i * distance + 0.5 * POSTwidth) + MERGE, y + MERGE - SPAERthick, SPAERthick, distance);
        }

        int fyrQ = OverlaySizeCalculator.fyrQuantityOnWall(wall);
        int fyrDistance = OverlaySizeCalculator.fyrDistance(wall) / 10;

        for (int i = 0; i < fyrQ; i++) {
            svg.addRect(x + (int) (i * fyrDistance + 0.5 * POSTwidth - 0.5 * FYRwidth) + MERGE, y + MERGE - (SPAERthick + FYRthick), FYRthick, FYRwidth);

        }

        svg.addRect(x + MERGE, y + MERGE - (SPAERthick + FYRthick + OVERLAYthick), OVERLAYthick, wall.getLength() / 10);

    }

    public static void leftWallL(Svg svg, Wall wall, int x, int y) {
        int postQ = ConstructionSizeCalculator.sidePostAmount(wall.getLength());
        int distance = ConstructionSizeCalculator.postDistanceMax3000(wall.getLength()) / 10;

        for (int i = 0; i < postQ; i++) {
            svg.addRect(x + (i * distance) + MERGE, y + MERGE, POSTwidth, POSTwidth);
        }

        for (int i = 0; i < postQ - 1; i++) {
            svg.addRect(x + (int) (i * distance + 0.5 * POSTwidth) + MERGE, y + MERGE + POSTwidth, SPAERthick, distance);
        }

        int fyrQ = OverlaySizeCalculator.fyrQuantityOnWall(wall);
        int fyrDistance = OverlaySizeCalculator.fyrDistance(wall) / 10;

        for (int i = 0; i < fyrQ; i++) {
            svg.addRect(x + (int) (i * fyrDistance + 0.5 * POSTwidth - 0.5 * FYRwidth) + MERGE, y + MERGE + POSTwidth + SPAERthick, FYRthick, FYRwidth);

        }

        svg.addRect(x + MERGE, y + MERGE + POSTwidth + SPAERthick + FYRthick, OVERLAYthick, wall.getLength() / 10);

    }


    public static void backWall(Svg svg, Wall wall, int y) {
        int postQ = ConstructionSizeCalculator.sidePostAmount(wall.getLength());
        int distance = ConstructionSizeCalculator.postDistanceMax3000(wall.getLength()) / 10;
        for (int i = 0; i < postQ; i++) {
            svg.addRect(0 + MERGE, y + i * distance + MERGE, POSTwidth, POSTwidth);
        }
        for (int i = 0; i < postQ - 1; i++) {
            svg.addRect(0 + MERGE - SPAERthick, y + (int) (i * distance + 0.5 * POSTwidth) + MERGE, distance, SPAERthick);
        }


        int fyrQ = OverlaySizeCalculator.fyrQuantityOnWall(wall);
        int fyrDistance = OverlaySizeCalculator.fyrDistance(wall) / 10;
        for (int i = 0; i < fyrQ; i++) {
            svg.addRect(0 + MERGE - SPAERthick - FYRthick, y + (int) (i * fyrDistance + 0.5 * POSTwidth - 0.5 * FYRwidth) + MERGE, FYRwidth, FYRthick);

        }
        svg.addRect(0 + MERGE - SPAERthick - FYRthick - OVERLAYthick, y + 0 + MERGE, wall.getLength() / 10, OVERLAYthick);

    }


    public static void frontWall(Svg svg, Wall wall, int x, int y) {
        int postQ = ConstructionSizeCalculator.sidePostAmount(wall.getLength());
        int distance = ConstructionSizeCalculator.postDistanceMax3000(wall.getLength()) / 10;
        for (int i = 0; i < postQ; i++) {
            svg.addRect(x + MERGE, y + i * distance + MERGE, POSTwidth, POSTwidth);
        }
        for (int i = 0; i < postQ - 1; i++) {
            svg.addRect(x + MERGE + POSTwidth, y + (int) (i * distance + 0.5 * POSTwidth) + MERGE, distance, SPAERthick);
        }


        int fyrQ = OverlaySizeCalculator.fyrQuantityOnWall(wall);
        int fyrDistance = OverlaySizeCalculator.fyrDistance(wall) / 10;
        for (int i = 0; i < fyrQ; i++) {
            svg.addRect(x + MERGE + POSTwidth + SPAERthick, y + (int) (i * fyrDistance + 0.5 * POSTwidth - 0.5 * FYRwidth) + MERGE, FYRwidth, FYRthick);
        }

        svg.addRect(x + MERGE + POSTwidth + SPAERthick + FYRthick, y + MERGE, wall.getLength() / 10, OVERLAYthick);


    }

    public static void door(Svg svg, int x, int y) {
        svg.addRect(MERGE + x, y + MERGE - SPAERthick, SPAERthick, DOORwidth);
        svg.addRect(MERGE + x, y + MERGE - SPAERthick - FYRthick, FYRthick, FYRwidth);
        svg.addRect(MERGE + x + (DOORwidth / 2 - FYRwidth / 2), y + MERGE - SPAERthick - FYRthick, FYRthick, FYRwidth);
        svg.addRect(MERGE + x + (DOORwidth - FYRwidth), y + MERGE - SPAERthick - FYRthick, FYRthick, FYRwidth);
        svg.addRect(MERGE + x, y + MERGE - SPAERthick - FYRthick - OVERLAYthick, OVERLAYthick, DOORwidth);

    }

    public static void posts (Svg svg, Construction construction) {
        if (construction.getShed().getDepth()==0){
            int postQ = ConstructionSizeCalculator.sidePostAmount(construction.getCarportLength());
            int distance = ConstructionSizeCalculator.postDistanceMax3000(construction.getCarportLength()) / 10;

            for (int i = 0; i < postQ; i++) {
                svg.addRect((i * distance) + MERGE, MERGE, POSTwidth, POSTwidth);
            }

            for (int i = 0; i < postQ; i++) {
                svg.addRect((i * distance) + MERGE, MERGE+construction.getCarportWidth()/10-POSTwidth, POSTwidth, POSTwidth);
            }

            if (construction.getCarportWidth()>6000){
                for (int i = 0; i < postQ; i++) {
                    svg.addRect((i * distance) + MERGE, MERGE+construction.getCarportWidth()/2/10-POSTwidth/2, POSTwidth, POSTwidth);
                }
            }
        } else if (construction.getShed().getDepth()>0 && construction.getShed().getWidth()==construction.getCarportWidth()){

            int postQ = ConstructionSizeCalculator.sidePostAmount(construction.getCarportLength());
            int distance = ConstructionSizeCalculator.postDistanceMax3000(construction.getCarportLength()) / 10;
            int distanceInShed = ConstructionSizeCalculator.postDistanceMax3000(construction.getShedDepth()) /10;
            int postQinShed = ConstructionSizeCalculator.sidePostAmount(construction.getShedDepth());

            for (int i = 0; i < postQ; i++) {
                svg.addRect(construction.getShed().getDepth()/10-POSTwidth+(i * distance) + MERGE, MERGE, POSTwidth, POSTwidth);
            }

            for (int i = 0; i < postQ; i++) {
                svg.addRect(construction.getShed().getDepth()/10-POSTwidth+(i * distance) + MERGE, MERGE+construction.getCarportWidth()/10-POSTwidth, POSTwidth, POSTwidth);
            }

            if (construction.getCarportWidth()>6000){
                for (int i = 0; i < postQinShed; i++) {
                    svg.addRect((i * distanceInShed) + MERGE, MERGE+construction.getCarportWidth()/10/2-POSTwidth/2, POSTwidth, POSTwidth);
                }
                for (int i = 0; i < postQ; i++) {
                    svg.addRect(construction.getShed().getDepth()/10-POSTwidth+(i * distance) + MERGE, MERGE+construction.getCarportWidth()/10/2-POSTwidth/2, POSTwidth, POSTwidth);
                }
            }

        }

    }

}
