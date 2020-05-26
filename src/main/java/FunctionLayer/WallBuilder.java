package FunctionLayer;

import java.util.ArrayList;

/**
 * @author Magdalena
 * The purpose of this class is to fill up the lists with proper Walls objects
 */
public class WallBuilder {

    private final static int DOORSIZE = 1000;
    private final static int POSTWIDTH = 100;

    /**
     * @param construction : Construction object that it's attributes can not be null
     * @return int height : calculated from dependencies between Tilt of Roof and Shed's depth -
     * is a value of height of front Wall of shed.
     * Used in WallBuilder.addShedWalls method.
     */
    public static int frontWallHeight(Construction construction) {
        int tilt = construction.getRoof().getTilt();
        int shedDepth = construction.getShedDepth();
        double raising = ConstructionSizeCalculator.raising(shedDepth, tilt);
        int height = construction.getConstructionHeight() + (int) raising;
        return height;
    }


    /**
     * @param construction Construction object that it's attributes can not be null
     * @return ArrayList with four Wall objects that are being created from dependencies
     * between Shed's lengths and width, constructions height and Roof's tilt.
     * After being used changes wall list of Shed object of construcion from onewith 0 elements to one with 4 different elements
     * Used in FunctionLayer.LogicFacade.setMaterialsForOrder, PresentationLayer.CarportBase.execute, PresentationLayer.EditOrderPrices.execute
     */
    public static ArrayList<Wall> addShedWalls(Construction construction) {
        ArrayList<Wall> walls = new ArrayList<>();
        if (construction.getShedDepth() != 0) {

            Wall right = new Wall();
            right.setSide("right");
            right.setLength(construction.getShedDepth());
            right.setRaising(construction.getRoof().getTilt());
            right.setMinHeight(construction.getConstructionHeight());

            Wall left = new Wall();
            left.setSide("left");
            left.setLength(construction.getShedDepth());
            left.setRaising(construction.getRoof().getTilt());
            left.setMinHeight(construction.getConstructionHeight());

            Wall back = new Wall();
            back.setSide("back");
            back.setLength(construction.getShed().getWidth());
            back.setRaising(0);
            back.setMinHeight(construction.getConstructionHeight());

            Wall front = new Wall();
            front.setSide("front");
            front.setLength(construction.getShed().getWidth() - DOORSIZE);
            front.setRaising(0);
            front.setMinHeight(frontWallHeight(construction));

            walls.add(right);
            walls.add(left);
            walls.add(front);
            walls.add(back);
        }
        return walls;

    }

    /**
     * @param construction : Construction object that it's attributes can not be null
     * @param sides        : An ArrayLists with String as names of Walls to be created
     * @return ArrayList with Wall object that are being created from dependencies
     * After being used changes wall list of constuction object from one with 0 elements to one with
     * from 0 to 4 elements depending on sides parameter and Shed's depth and width
     * between Shed's lengths and width, constructions height, carportLength, carportWidth and Roof's tilt
     */
    public static ArrayList<Wall> createCarportWalls(Construction construction, ArrayList<String> sides) {
        ArrayList<Wall> carportWalls = new ArrayList<>();

        if (sides.size() == 0) {
            return carportWalls;
        } else {

            String side = "";
            int wallLength = 0;
            int raising = construction.getRoof().getTilt();
            int minHeight = 0;


            if (construction.getShedDepth() == 0) {

                for (String wallSide : sides) {
                    if (wallSide.equals("back")) {

                        wallLength = construction.getCarportWidth();
                        minHeight = construction.getConstructionHeight();
                        side = "back";
                        raising = 0;
                    } else {
                        wallLength = construction.getCarportLength();
                        minHeight = construction.getConstructionHeight();
                        side = wallSide;
                        raising = construction.getRoof().getTilt();
                    }
                    Wall wall = new Wall();
                    wall.setRaising(raising);
                    wall.setMinHeight(minHeight);
                    wall.setLength(wallLength);
                    wall.setSide("carport" + side);
                    carportWalls.add(wall);
                }
            } else {

                if (construction.getShed().getWidth() == construction.getCarportWidth()) {
                    for (String wallSide : sides) {
                        if (!wallSide.equals("back")) {
                            wallLength = construction.getCarportLength();
                            minHeight = frontWallHeight(construction);
                            raising = construction.getRoof().getTilt();
                            side = wallSide;
                            Wall wall = new Wall();
                            wall.setRaising(raising);
                            wall.setMinHeight(minHeight);
                            wall.setLength(wallLength);
                            wall.setSide("carport" + side);
                            carportWalls.add(wall);
                        }
                    }
                } else if (construction.getShed().getWidth() < construction.getCarportWidth() && construction.getShed().getWidth() > 0) {
                    for (String wallSide : sides) {
                        if (wallSide.equals("back")) {
                            wallLength = construction.getShed().getWidth();
                            minHeight = construction.getConstructionHeight();
                            side = "back";
                            Wall wall = new Wall();
                            wall.setRaising(0);
                            wall.setMinHeight(minHeight);
                            wall.setLength(wallLength);
                            wall.setSide("carport" + side);
                            carportWalls.add(wall);
                        } else {
                            if (wallSide.equals(construction.getShed().getSide())) {
                                wallLength = construction.getCarportLength();
                                minHeight = frontWallHeight(construction);
                                raising = construction.getRoof().getTilt();
                                side = wallSide;
                                Wall wall = new Wall();
                                wall.setRaising(raising);
                                wall.setMinHeight(minHeight);
                                wall.setLength(wallLength);
                                wall.setSide("carport" + side);
                                carportWalls.add(wall);
                            } else {
                                wallLength = construction.getCarportLength();
                                minHeight = frontWallHeight(construction);
                                raising = construction.getRoof().getTilt();
                                side = wallSide;
                                Wall wall = new Wall();
                                wall.setRaising(raising);
                                wall.setMinHeight(minHeight);
                                wall.setLength(wallLength);
                                wall.setSide("carport" + side);
                                carportWalls.add(wall);

                                wallLength = construction.getShed().getDepth();
                                minHeight = construction.getConstructionHeight();
                                Wall likeShed = new Wall();
                                likeShed.setLength(wallLength);
                                likeShed.setMinHeight(minHeight);
                                likeShed.setRaising(raising);
                                likeShed.setSide("likeShed" + side);
                                carportWalls.add(likeShed);
                            }
                        }

                    }

                }
            }
        }

        return carportWalls;



/*
                if (construction.getShed().getWidth()<construction.getCarportWidth()){
                    for (String wallSide : sides) {
                        if (wallSide.equals("back")){
                            wallLength=construction.getShed().getWidth();
                            minHeight=construction.getConstructionHeight();
                            raising=0;
                        } else {
                            wallLength=construction.getCarportLength();
                            minHeight=
                        }

                    }
                }







                for (String wallSide : sides) {

                    if (wallSide.equals("back") && construction.getCarportWidth() > construction.getShed().getWidth()) {
                        wallLength = construction.getShed().getWidth();
                        minHeight = construction.getConstructionHeight();
                        side = "back";
                        Wall wall = new Wall();
                        wall.setRaising(0);
                        wall.setMinHeight(minHeight);
                        wall.setLength(wallLength);
                        wall.setSide("carport" + side);
                        carportWalls.add(wall);
                    } else if (wallSide.equals(construction.getShed().getSide())&& !wallSide.equals("back")) {
                        wallLength = construction.getCarportLength();
                        minHeight = ConstructionSizeCalculator.carportMinHeight(construction.getConstructionHeight(),
                                construction.getShedDepth()-POSTWIDTH, construction.getRoof().getTilt());
                        side = wallSide;
                        Wall wall = new Wall();
                        wall.setRaising(raising);
                        wall.setMinHeight(minHeight);
                        wall.setLength(wallLength);
                        wall.setSide("carport" + side);
                        carportWalls.add(wall);
                    } else if (!wallSide.equals("back")){
                        Wall carportWall = new Wall();
                        wallLength = construction.getCarportLength();
                        minHeight = ConstructionSizeCalculator.carportMinHeight(construction.getConstructionHeight(),
                                construction.getShedDepth()-100, construction.getRoof().getTilt());
                        side = wallSide;
                        carportWall.setRaising(raising);
                        carportWall.setMinHeight(minHeight);
                        carportWall.setLength(wallLength);
                        carportWall.setSide("carport" + side);
                        carportWalls.add(carportWall);

                        if (construction.getShed().getWidth() != construction.getCarportWidth()) {
                            Wall likeShedWall = new Wall();
                            likeShedWall.setSide("likeShed" + wallSide);
                            likeShedWall.setLength(construction.getShedDepth());
                            likeShedWall.setMinHeight(construction.getConstructionHeight());
                            likeShedWall.setRaising(raising);
                            carportWalls.add(likeShedWall);
                        }

                    }

                }
            }
            */


    }

}