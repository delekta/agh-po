package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

abstract public class AbstractWorldMap implements IWorldMap {
    public List<Animal> animals;
    public int height;
    public int width;

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
    public String toString(){
        MapVisualizer visual = new MapVisualizer(this);
        return visual.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }


    public void debugger(Animal animal, MoveDirection direction){
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
                + ") ruszy sie " + direction.toString());
        System.out.println(this.toString());
    }
}
