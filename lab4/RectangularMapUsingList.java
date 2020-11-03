package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.*;

public class RectangularMapUsingList implements IWorldMap{

    private int height;
    private int width;

    // Implementation using list
    private List<Animal> animals;


    public RectangularMapUsingList(int width, int height){
        // Map is a rectangular created by points: (0, 0), (width, height)
        this.height = height;
        this.width = width;
        this.animals = new ArrayList<>();
    }

    @Override
    public void run(LinkedList<MoveDirection> directions) {
        ListIterator<Animal> animalListIterator = animals.listIterator();

        for(MoveDirection direction: directions){
            if(!animalListIterator.hasNext()){
                animalListIterator = animals.listIterator();
            }
            Animal animal = animalListIterator.next();

            // My toString, print out current situation(position on the map, next move)
//            debugger(animal, direction);

            switch(direction) {
                case LEFT:
                    animal.setOrientation(animal.getOrientation().previous());
                    break;
                case RIGHT:
                    animal.setOrientation(animal.getOrientation().next());
                    break;
                case FORWARD:
                    // Check if we can move to new position
                    if(canMoveTo(animal.getPosition().add(animal.getOrientation().toUnitVector()))){
                        animal.setPosition(animal.getPosition().add(animal.getOrientation().toUnitVector()));
                    }
                    break;
                case BACKWARD:
                    // Check if we can move to new position
                    if(canMoveTo(animal.getPosition().subtract(animal.getOrientation().toUnitVector()))){
                        animal.setPosition(animal.getPosition().subtract(animal.getOrientation().toUnitVector()));
                    }
                    break;
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(0 <= position.x && position.x <= width && 0 <= position.y && position.y <= height) {
            if(!isOccupied(position)) {
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public boolean place(Animal animal) {
        // sprawdzam czy dana pozycja zawiera sie w mapie
        if(canMoveTo(animal.getPosition())) {
            // placing animal
            animals.add(animal);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if(animal.getPosition().x == position.x && animal.getPosition().y == position.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if(animal.getPosition().x == position.x && animal.getPosition().y == position.y){
                return animal;
            }
        }
        return null;
    }

    // My toString
    public void debugger(Animal animal, MoveDirection direction){
        MapVisualizer visual = new MapVisualizer(this);
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
                + ") ruszy sie " + direction.toString());
        System.out.println(visual.draw(new Vector2d(0, 0), new Vector2d(width, height)));
    }
}
