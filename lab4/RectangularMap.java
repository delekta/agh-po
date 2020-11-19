package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private int height;
    private int width;

    // Implementation using matrix array
    private Animal[][] animals;
    // Array needed to remember the order animals
    private List<Animal> order;


    public RectangularMap(int width, int height){
        // Map is a rectangular created by points: (0, 0), (width, height)
        this.height = height;
        this.width = width;
        this.animals = new Animal[height + 1][width + 1];

        // Filling array with null
        for(int i = 0; i < height + 1; i++)
        {
            Arrays.fill(animals[i], null);
        }
        this.order = new LinkedList<>();
    }

    @Override
    public void run(LinkedList<MoveDirection> directions) {
        // Needed to assign current animal
        int i = 0;
        for(MoveDirection direction: directions){
            // If we have already taken the last animal, the queue starts again
            if(i >= this.order.size()){
                i = 0;
            }
            Animal animal = order.get(i);

//             My toString, print out current situation(position on the map, next move)
             debugger(animal, direction);

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
                            // Reset previous position
                            animals[animal.getPosition().y][animal.getPosition().x] = null;
                            animal.setPosition(animal.getPosition().add(animal.getOrientation().toUnitVector()));
                            animals[animal.getPosition().y][animal.getPosition().x] = animal;
                        }
                    break;
                    case BACKWARD:
                        // Check if we can move to new position
                        if(canMoveTo(animal.getPosition().subtract(animal.getOrientation().toUnitVector()))){
                            // Reset old previous position
                            animals[animal.getPosition().y][animal.getPosition().x] = null;
                            animal.setPosition(animal.getPosition().subtract(animal.getOrientation().toUnitVector()));
                            animals[animal.getPosition().y][animal.getPosition().x] = animal;
                        }
                        break;
                    }
                i++; // <- Take another animal
            }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!isOccupied(position)){
            return true;
        }
        else return false;
    }

    @Override
    public boolean place(Animal animal) {
        // sprawdzam czy dana pozycja zawiera sie w mapie
        if(canMoveTo(animal.getPosition())) {
            // placing animal
            order.add(animal);
            animals[animal.getPosition().y][animal.getPosition().x] = animal;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        // Check that the position is on the map
        if(position.x >= 0 && position.x <= this.width && position.y >= 0 && position.y <= this.height){
            if(animals[position.y][position.x] == null){
                return false;
            }
            else{
                return true;
            }
        } // Returned when we are off the map as we can't move there so it's like occupied field!!!
        else return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(isOccupied(position)){
            return animals[position.y][position.x];
        }
        else return null;
    }

    // My toString
    public void debugger(Animal animal, MoveDirection direction){
        MapVisualizer visual = new MapVisualizer(this);
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
        + ") ruszy sie " + direction.toString());
        System.out.println(visual.draw(new Vector2d(0, 0), new Vector2d(width, height)));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        //
    }
}
