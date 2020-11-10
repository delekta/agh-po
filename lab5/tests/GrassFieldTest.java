package agh.cs.lab5.tests;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMapUsingList;
import agh.cs.lab5.GrassField;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.LinkedList;
//
public class GrassFieldTest extends TestCase {

    public void testTestRun() {
        // test1
        String[] moves = new String[]{"f",  "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> directions = new OptionsParser().parse(moves);
        IWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));

        map.place(animal1);
        map.place(animal2);

        map.run(directions);

        Assert.assertEquals(animal1.getPosition(), new Vector2d(2, -1));
        Assert.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);

        Assert.assertEquals(animal2.getPosition(), new Vector2d(3 ,7));
        Assert.assertEquals(animal2.getOrientation(), MapDirection.NORTH);


        // test2
        String[] moves2 = new String[]{"r",  "l", "f", "f", "l", "l", "f", "f", "r", "r", "f", "f"};
        LinkedList<MoveDirection> directions2 = new OptionsParser().parse(moves2);
        IWorldMap map2 = new GrassField(15);
        Animal animal12 = new Animal(map2, new Vector2d(4,4));
        Animal animal22 = new Animal(map2,new Vector2d(6,6));

        map2.place(animal12);
        map2.place(animal22);

        map2.run(directions2);

        Assert.assertEquals(animal12.getPosition(), new Vector2d(6, 5));
        Assert.assertEquals(animal12.getOrientation(), MapDirection.EAST);

        Assert.assertEquals(animal22.getPosition(), new Vector2d(4 ,6));
        Assert.assertEquals(animal22.getOrientation(), MapDirection.WEST);

        // added during lab6
        // i had to change run function to changing also animalHashMap
        System.out.println(map2.toString());
    }

    public void testCanMoveTo(){
        IWorldMap map = new GrassField(20);

        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map,new Vector2d(3,4));

        map.place(animal1);
        map.place(animal2);

        Assert.assertTrue(map.canMoveTo(new Vector2d(10, 5)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(11, 0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0, 6)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3, 4)));
    }

    // nie zawsze!!!
    public void testPlace() {
        IWorldMap map2 = new GrassField(20);
        Animal animal12 = new Animal(map2, new Vector2d(4,4));
        Animal animal22 = new Animal(map2,new Vector2d(6,6));
//        Animal animal32 = new Animal(map2,new Vector2d(4,4));
        Assert.assertTrue(map2.place(animal12));
        Assert.assertTrue(map2.place(animal22));
//        Assert.assertFalse(map2.place(animal32));
    }

    public void testIsOccupied() {
        IWorldMap map3 = new GrassField(20);
        Animal animal12 = new Animal(map3, new Vector2d(1,1));
        Animal animal22 = new Animal(map3,new Vector2d(6,2));
        Animal animal32 = new Animal(map3,new Vector2d(4,3));

        // added during lab6
        String[] moves = new String[]{"f", "b", "r"};
        LinkedList<MoveDirection> directions = new OptionsParser().parse(moves);
        map3.place(animal12);
        map3.place(animal22);
        map3.place(animal32);
        map3.run(directions);
        Assert.assertTrue(map3.isOccupied(new Vector2d(1, 2)));
        Assert.assertTrue(map3.isOccupied(new Vector2d(6, 1)));
        Assert.assertFalse(map3.isOccupied(new Vector2d(0, 3))); // czasem zwraca true, bo na tym miejscu mamy trawe
    }

    public void testObjectAt() {
        IWorldMap map3 = new GrassField(15);
        Animal animal12 = new Animal(map3, new Vector2d(1,1));
        Animal animal22 = new Animal(map3,new Vector2d(6,2));
        Animal animal32 = new Animal(map3,new Vector2d(4,3));
        map3.place(animal12);
        map3.place(animal22);
        map3.place(animal32);
        Assert.assertEquals(map3.objectAt(new Vector2d(6,2)), animal22);
        Assert.assertEquals(map3.objectAt(new Vector2d(1,1)), animal12);
        Assert.assertEquals(map3.objectAt(new Vector2d(3,3)), null); // czasem zwraca true, bo na tym miejscu mamy trawe
    }
}