package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.*;

// Lab7 jakie obie mapy mają implementowac ten interface IPositionChanged???
abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    public List<Animal> animals;
    public int height;
    public int width;


    // added during lab6
    public LinkedHashMap<Vector2d, Animal> animalHashMap = new LinkedHashMap<>();
    public LinkedHashMap<Vector2d, Grass> grassHashMap = new LinkedHashMap<>();

    //added during lab6
    // uzycie metody values niczego nie usprawni, poniewaz nadal będziemy musieli iterowac po tablicy
    // a nic to nie zmienia w porownaniu do iterowania po liscie
    @Override
    public void run(LinkedList<MoveDirection> directions) {


        // Zmiana na uzywanie tylko LinkedHashMap 3
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
//                        animalHashMap.remove(animal.getPosition());

                        // added during lab7
                        Vector2d oldPosition = animal.getPosition();
                        animal.setPosition(animal.getPosition().add(animal.getOrientation().toUnitVector()));
                        Vector2d newPosition = animal.getPosition();
                        animal.notifyPositionChanged(oldPosition, newPosition);
//                        animalHashMap.put(animal.getPosition(), animal);

                    }
                    break;
                case BACKWARD:
                    // Check if we can move to new position
                    if(canMoveTo(animal.getPosition().subtract(animal.getOrientation().toUnitVector()))){
                        // added during lab6
                        // updating animal hashMap
//                        animalHashMap.remove(animal.getPosition());


                        Vector2d oldPosition = animal.getPosition();
                        animal.setPosition(animal.getPosition().subtract(animal.getOrientation().toUnitVector()));
                        Vector2d newPosition = animal.getPosition();
                        animal.notifyPositionChanged(oldPosition, newPosition);
//                        animalHashMap.put(animal.getPosition(), animal);
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

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animalHashMap.get(oldPosition);
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, animal);
    }
}
