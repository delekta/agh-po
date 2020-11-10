package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;

import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        // sprawdzam dla: f b r l f f r r f f f f f f f f -> OK
        LinkedList<MoveDirection> directions = new OptionsParser().parse(args);
        int width = 10;
        int height = 5;
        IWorldMap map = new RectangularMapUsingList(width, height);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(2,3)));
        map.run(directions);
    }
}
