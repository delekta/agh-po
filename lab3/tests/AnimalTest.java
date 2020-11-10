package agh.cs.lab3.tests;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import org.junit.Assert;
import org.junit.Test;

public class AnimalTest{
    IWorldMap map = new RectangularMap(10, 5);
    @Test
    public void orientationTest(){
        // Mostly for orientation, position
        Animal animal = new Animal(map);
        String[] moves = new String[]{"l", "f", "l", "f", "f", "left", "backward"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.getOrientation(), MapDirection.EAST);
        Assert.assertEquals(animal.getPosition(), new Vector2d(0,0));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void positionTest(){
        Animal animal = new Animal(map);
        String[] moves = new String[]{"r", "r", "f", "l", "backward", "right", "forward", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(1,-1));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void goBeyondMapTest(){
        Animal animal = new Animal(map);
        String[] moves = new String[]{"r", "r", "f", "l", "backward", "right", "forward", "f", "f", "f", "f", "f", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(1,-4));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void parserTest(){
        Animal animal = new Animal(map);
        String[] moves = new String[]{"r", "l", "kawa", "l", "backward", "right", "forward", "herbata", "woda", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(3,4));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

}