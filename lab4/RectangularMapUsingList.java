package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab5.Grass;

import java.util.*;

public class RectangularMapUsingList extends AbstractWorldMap {
    // debugger() inherited from AbstractWorldMap
    //   animals inherited from AbstractWorldMap
    // run() inherited from AbstractWorldMap
    // toString() inherited from AbstractWorldMap
    // width, height inherited from AbstractWorldMap

    public RectangularMapUsingList(int width, int height){
        // Map is a rectangular created by points: (0, 0), (width, height)
        this.height = height;
        this.width = width;
        this.animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(0 <= position.x && position.x <= width && 0 <= position.y && position.y <= height) {
            if(!isOccupied(position)) {
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public boolean place(Animal animal) {
        // sprawdzam czy dana pozycja zawiera sie w mapie
        if(canMoveTo(animal.getPosition())) {
            // placing animal
            animals.add(animal);
            return true;
        }
        else{
            // added during lab 6
            throw new IllegalArgumentException("x:" + animal.getPosition().x + " " + "y: " + animal.getPosition().y + " jest juz zajęte");
//            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if(animal.getPosition().x == position.x && animal.getPosition().y == position.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if(animal.getPosition().x == position.x && animal.getPosition().y == position.y){
                return animal;
            }
        }
        return null;
    }
}
