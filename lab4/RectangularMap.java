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
    private Animal[][] animals;
    private List<Animal> order;


    public RectangularMap(int width, int height){
        this.height = height;
        this.width = width;
        this.animals = new Animal[height + 1][width + 1];

        // filing array with null
        for(int i = 0; i < height + 1; i++)
        {
            Arrays.fill(animals[i], null);
        }
        this.order = new LinkedList<>();
    }

    @Override
    public void run(LinkedList<MoveDirection> directions) {
        int i = 0;
        for(MoveDirection direction: directions){
            if(i >= this.order.size()){
                i = 0;
            }
            Animal animal = order.get(i);

            // mój toString, wypisuje aktualną sytuacje
            // debugger(animal, direction);

                switch(direction) {
                    case LEFT:
                        animal.setOrientation(animal.getOrientation().previous());
                        break;
                    case RIGHT:
                        animal.setOrientation(animal.getOrientation().next());
                        break;
                    case FORWARD:
                        //?
                        if(canMoveTo(animal.getPosition().add(animal.getOrientation().toUnitVector()))){
                            animals[animal.getPosition().y][animal.getPosition().x] = null;
                            animal.setPosition(animal.getPosition().add(animal.getOrientation().toUnitVector()));
                            animals[animal.getPosition().y][animal.getPosition().x] = animal;
                        }
                    break;
                    case BACKWARD:
                        //?
                        if(canMoveTo(animal.getPosition().subtract(animal.getOrientation().toUnitVector()))){
                            animals[animal.getPosition().y][animal.getPosition().x] = null;
                            animal.setPosition(animal.getPosition().subtract(animal.getOrientation().toUnitVector()));
                            animals[animal.getPosition().y][animal.getPosition().x] = animal;
                        }
                        break;
                    }
                i++;
            }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // rectangular created by points: (0, 0), (width, height)
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
        // sprawdza czy dana pozycja znajduję się na mapie
        if(position.x >= 0 && position.x <= this.width && position.y >= 0 && position.y <= this.height){
            if(animals[position.y][position.x] == null){
                return false;
            }
            else{
                return true;
            }
        } // zwracane gdy jestesmy poza mapa, bo nie mozemy sie tam ruszyc, wiec jest tak jakby polem zajetym!!!
        else return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(isOccupied(position)){
            return animals[position.y][position.x];
        }
        else return null;
    }

    // mój toString
    public void debugger(Animal animal, MoveDirection direction){
        MapVisualizer visual = new MapVisualizer(this);
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
        + ") ruszy sie " + direction.toString());
        System.out.println(visual.draw(new Vector2d(0, 0), new Vector2d(width, height)));
    }
}
