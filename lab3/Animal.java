package agh.cs.lab3;

import agh.cs.lab2.*;

public class Animal {

    // public for tests
    public MapDirection orientation = MapDirection.NORTH;
    public Vector2d position = new Vector2d(2,2);

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
                changePosition(MoveDirection.FORWARD);
                break;
            case BACKWARD:
                changePosition(MoveDirection.BACKWARD);
                break;
        }
    }

    public void changePosition(MoveDirection direction){
            int moveValue = 0;
            switch (direction) {
                case FORWARD:
                    moveValue = 1;
                    break;
                case BACKWARD:
                    moveValue = -1;
                    break;
            }

            switch (this.orientation) {
                case NORTH:
                    if(isOnMap(this.position.y, moveValue)) {
                        this.position = this.position.add(new Vector2d(0, moveValue));
                    }
                    else{
                        // System.out.println("End of the map!!!");
                    }
                    break;
                case EAST:
                    if(isOnMap(this.position.x, moveValue)) {
                        this.position = this.position.add(new Vector2d(moveValue, 0));
                    }
                    else{
                        // System.out.println("End of the map!!!");
                    }
                    break;
                case SOUTH:
                    if(isOnMap(this.position.y, -moveValue)) {
                        this.position = this.position.add(new Vector2d(0, -moveValue));
                    }
                    else{
                        // System.out.println("End of the map!!!");
                    }
                    break;
                case WEST:
                    if(isOnMap(this.position.x, -moveValue)){
                        this.position = this.position.add(new Vector2d(-moveValue, 0));
                    }
                    else{
                        // System.out.println("End of the map!!!");
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

    public boolean isOnMap(int position, int value){
        return this.abs(position + value) <= 4;
    }
}
