package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

abstract public class AbstractWorldMap implements IWorldMap {
    public List<Animal> animals;
    public int height;
    public int width;


    // added during lab6
    public HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();
    public HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();

    //added during lab6
    // uzycie metody values niczego nie usprawni, poniewaz nadal będziemy musieli iterowac po tablicy
    // a nic to nie zmienia w porownaniu do iterowania po liscie
    @Override
    public void run(LinkedList<MoveDirection> directions) {
        ListIterator<Animal> animalListIterator = animals.listIterator();

        for(MoveDirection direction: directions){
            if(!animalListIterator.hasNext()){
                animalListIterator = animals.listIterator();
            }
            Animal animal = animalListIterator.next();

//            debugger(animal, direction);

            switch(direction){
                case LEFT:
                    animal.setOrientation(animal.getOrientation().previous());
                    // we dont need to change orientation of animal in animalHashMap
                    break;
                case RIGHT:
                    // added during lab6
                    // we dont need to change orientation of animal in animalHashMap
                    animal.setOrientation(animal.getOrientation().next());
                    break;
                case FORWARD:
                    // Check if we can move to new position
                    if(canMoveTo(animal.getPosition().add(animal.getOrientation().toUnitVector()))){
                        // added during lab6
                        // updating animal hashMap
                        animalHashMap.remove(animal.getPosition());
                        animal.setPosition(animal.getPosition().add(animal.getOrientation().toUnitVector()));
                        animalHashMap.put(animal.getPosition(), animal);

                    }
                    break;
                case BACKWARD:
                    // Check if we can move to new position
                    if(canMoveTo(animal.getPosition().subtract(animal.getOrientation().toUnitVector()))){
                        // added during lab6
                        // updating animal hashMap
                        animalHashMap.remove(animal.getPosition());
                        animal.setPosition(animal.getPosition().subtract(animal.getOrientation().toUnitVector()));
                        animalHashMap.put(animal.getPosition(), animal);
                    }
                    break;
            }
        }
    }

    @Override
    public String toString(){
        MapVisualizer visual = new MapVisualizer(this);
        return visual.draw(new Vector2d(-1, -1), new Vector2d(width, height));
    }


    public void debugger(Animal animal, MoveDirection direction){
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
                + ") ruszy sie " + direction.toString());
        System.out.println(this.toString());
    }
}
