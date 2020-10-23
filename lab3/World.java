package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args){
        Animal animal = new Animal();
        System.out.println(animal.toString());
        /*animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        System.out.println(animal.toString());*/
        OptionsParser parser = new OptionsParser();
        LinkedList<MoveDirection> moves = parser.parse(args);
        for(MoveDirection m: moves){
            animal.move(m);
            System.out.println("Moved: " + m.toString() + " Current position :" + animal.toString());
        }
    }
}
