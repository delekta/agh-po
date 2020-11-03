package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMapUsingList;

import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        String[] moves = new String[]{"f",  "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> directions = new OptionsParser().parse(moves);
        IWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));

        map.place(animal1);
        map.place(animal2);

        map.run(directions);
    }
}
