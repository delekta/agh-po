package agh.cs.lab4.tests;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMapUsingList;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.LinkedList;

public class RectangularMapUsingListTest extends TestCase {

    public void testRun() {
        // test1
        String[] moves = new String[]{"f",  "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> directions = new OptionsParser().parse(moves);
        int width = 10;
        int height = 5;
        IWorldMap map = new RectangularMapUsingList(width, height);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));

        map.place(animal1);
        map.place(animal2);

        map.run(directions);

        Assert.assertEquals(animal1.getPosition(), new Vector2d(2, 0));
        Assert.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);

        Assert.assertEquals(animal2.getPosition(), new Vector2d(3 ,5));
        Assert.assertEquals(animal2.getOrientation(), MapDirection.NORTH);

        // test2
        String[] moves2 = new String[]{"r",  "l", "f", "f", "l", "l", "f", "f", "r", "r", "f", "f"};
        LinkedList<MoveDirection> directions2 = new OptionsParser().parse(moves2);
        int width2 = 10;
        int height2 = 6;
        IWorldMap map2 = new RectangularMapUsingList(width2, height2);
        Animal animal12 = new Animal(map2, new Vector2d(4,4));
        Animal animal22 = new Animal(map2,new Vector2d(6,6));

        map2.place(animal12);
        map2.place(animal22);

        map2.run(directions2);

        Assert.assertEquals(animal12.getPosition(), new Vector2d(6, 5));
        Assert.assertEquals(animal12.getOrientation(), MapDirection.EAST);

        Assert.assertEquals(animal22.getPosition(), new Vector2d(4 ,6));
        Assert.assertEquals(animal22.getOrientation(), MapDirection.WEST);
    }

    public void testCanMoveTo() {
        int width = 10;
        int height = 5;
        IWorldMap map = new RectangularMapUsingList(width, height);
        Assert.assertTrue(map.canMoveTo(new Vector2d(10, 5)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(11, 0)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(0, 6)));


    }

    public void testPlace() {
        int width2 = 10;
        int height2 = 6;
        IWorldMap map2 = new RectangularMapUsingList(width2, height2);
        Animal animal12 = new Animal(map2, new Vector2d(4,4));
        Animal animal22 = new Animal(map2,new Vector2d(6,6));
        Animal animal32 = new Animal(map2,new Vector2d(4,4));
        Assert.assertTrue(map2.place(animal12));
        Assert.assertTrue(map2.place(animal22));
        Assert.assertFalse(map2.place(animal32));
    }

    public void testIsOccupied() {
        int width2 = 10;
        int height2 = 6;
        IWorldMap map3 = new RectangularMapUsingList(width2, height2);
        Animal animal12 = new Animal(map3, new Vector2d(1,1));
        Animal animal22 = new Animal(map3,new Vector2d(6,2));
        Animal animal32 = new Animal(map3,new Vector2d(4,3));
        map3.place(animal12);
        map3.place(animal22);
        map3.place(animal32);
        Assert.assertTrue(map3.isOccupied(new Vector2d(4, 3)));
        Assert.assertTrue(map3.isOccupied(new Vector2d(6, 2)));
        Assert.assertFalse(map3.isOccupied(new Vector2d(0, 3)));

    }

    public void testObjectAt() {
        int width2 = 10;
        int height2 = 6;
        IWorldMap map3 = new RectangularMapUsingList(width2, height2);
        Animal animal12 = new Animal(map3, new Vector2d(1,1));
        Animal animal22 = new Animal(map3,new Vector2d(6,2));
        Animal animal32 = new Animal(map3,new Vector2d(4,3));
        map3.place(animal12);
        map3.place(animal22);
        map3.place(animal32);
        Assert.assertEquals(map3.objectAt(new Vector2d(6,2)), animal22);
        Assert.assertEquals(map3.objectAt(new Vector2d(1,1)), animal12);
        Assert.assertEquals(map3.objectAt(new Vector2d(3,3)), null);
    }
}