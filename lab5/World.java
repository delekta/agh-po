package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMapUsingList;

import java.util.HashMap;
import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        // added during lab6
        try {
            String[] moves = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            LinkedList<MoveDirection> directions = new OptionsParser().parse(moves);
            IWorldMap map = new GrassField(10);
            Animal animal1 = new Animal(map);
            Animal animal2 = new Animal(map, new Vector2d(7, 7));

            map.place(animal1);
            map.place(animal2);

            // added during lab6
//            Animal animal3 = new Animal(map);
//            map.place(animal3);

            map.run(directions);

            System.out.println(map.toString());
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
}
