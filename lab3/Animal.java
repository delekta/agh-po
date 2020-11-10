package agh.cs.lab3;

import agh.cs.lab2.*;
import agh.cs.lab4.IWorldMap;

public class Animal {

    private IWorldMap map;
    
    private MapDirection orientation = MapDirection.NORTH;
    public void setOrientation(MapDirection o){ this.orientation = o;}
    public MapDirection getOrientation() { return orientation; }

    private Vector2d position = new Vector2d(2,2);
    public void setPosition(Vector2d v){ this.position = v;}
    public Vector2d getPosition(){return position;}

// Modified at Lab4
//    public String toString(){
//        return "Position: " + position.toString() + " Orientation: " + orientation.toString();
//    }

    //?
    public String toString(){
        switch(this.orientation){
            case NORTH:
                return "^";
            case EAST:
                return ">";
            case SOUTH:
                return "v";
            case WEST:
                return "<";
            default:
                return "Error";
        }
    }


    public void move(MoveDirection direction){

        switch(direction) {
            case LEFT:
                orientation = orientation.previous();
                break;
            case RIGHT:
                orientation = orientation.next();
                break;
            case FORWARD:
                // Changed in lab4
                if(map.canMoveTo(this.position.add(orientation.toUnitVector()))){
                    this.position = this.position.add(orientation.toUnitVector());
                    // Lab3
//                    if(!isOnMap()){
//                        // System.out.println("End of map!!!");
//                        this.position = this.position.subtract(orientation.toUnitVector());
//                    }
                }
                break;
            case BACKWARD:
                // Changed in lab4
                if(map.canMoveTo(this.position.subtract(orientation.toUnitVector()))){
                    this.position = this.position.subtract(orientation.toUnitVector());
                    // Lab3
//                    if(!isOnMap()){
//                        // System.out.println("End of map!!!");
//                        this.position = this.position.add(orientation.toUnitVector());
//                    }
                }
                break;
        }
    }

    private int abs(int i) {
        if(i > 0){
            return i;
        }
        else{
            return -i;
        }
    }

    public boolean isOnMap(){
        return abs(this.position.x) <= 4 && abs(this.position.y) <= 4;
    }

    /*
     * Lab 4 27.10.2020
     * */
    // Constructors
    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.setPosition(initialPosition);
    }


}
