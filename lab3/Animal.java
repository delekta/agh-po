package agh.cs.lab3;

import agh.cs.lab2.*;

public class Animal {

    
    private MapDirection orientation = MapDirection.NORTH;
    public void setOrientation(MapDirection o){ this.orientation = o;}
    public MapDirection getOrientation() { return orientation; }

    private Vector2d position = new Vector2d(2,2);
    public void setPosition(Vector2d v){ this.position = v;}
    public Vector2d getPosition(){return position;}

    public String toString(){
        return "Position: " + position.toString() + " Orientation: " + orientation.toString();
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
                this.position = this.position.add(orientation.toUnitVector());
                if(!isOnMap()){
                    // System.out.println("End of map!!!");
                    this.position = this.position.subtract(orientation.toUnitVector());
                }
                break;
            case BACKWARD:
                this.position = this.position.subtract(orientation.toUnitVector());
                if(!isOnMap()){
                    // System.out.println("End of map!!!");
                    this.position = this.position.add(orientation.toUnitVector());
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
}
