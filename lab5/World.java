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
        // sprawdzam dla: f b r l f f r r f f f f f f f f -> OK
        LinkedList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map,new Vector2d(5,5)));
        map.place(new Animal(map,new Vector2d(7,7)));
        map.run(directions);
        System.out.println(map.toString());
    }
}
