package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

public class Grass {
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
