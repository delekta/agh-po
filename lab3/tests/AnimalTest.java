package agh.cs.lab3.tests;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void orientationTest(){
        // Mostly for orientation, position
        Animal animal = new Animal();
        String[] moves = new String[]{"l", "f", "l", "f", "f", "left", "backward"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.orientation, MapDirection.EAST);
        Assert.assertEquals(animal.position, new Vector2d(0,0));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void positionTest(){
        Animal animal = new Animal();
        String[] moves = new String[]{"r", "r", "f", "l", "backward", "right", "forward", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.orientation, MapDirection.SOUTH);
        Assert.assertEquals(animal.position, new Vector2d(1,-1));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void goBeyondMapTest(){
        Animal animal = new Animal();
        String[] moves = new String[]{"r", "r", "f", "l", "backward", "right", "forward", "f", "f", "f", "f", "f", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.orientation, MapDirection.SOUTH);
        Assert.assertEquals(animal.position, new Vector2d(1,-4));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }

    @Test
    public void parserTest(){
        Animal animal = new Animal();
        String[] moves = new String[]{"r", "l", "kawa", "l", "backward", "right", "forward", "herbata", "woda", "f"};
        OptionsParser parser = new OptionsParser();

        for(MoveDirection m: parser.parse(moves)){
            animal.move(m);
        }
        Assert.assertEquals(animal.orientation, MapDirection.NORTH);
        Assert.assertEquals(animal.position, new Vector2d(3,4));


        Assert.assertTrue(animal.isOnMap());
        Assert.assertTrue(animal.isOnMap());
    }



}