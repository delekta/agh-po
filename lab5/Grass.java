package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab7.IMapElement;

public class Grass implements IMapElement {
    private Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return "*";
    }

    public Grass(Vector2d position){
        this.position = position;
    }
}
