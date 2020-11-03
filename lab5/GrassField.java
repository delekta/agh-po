package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.*;

import java.lang.Math;

public class GrassField implements IWorldMap {
    // Co z ujemnymi współrzędnymi?

    int n;
    public List<Grass> grasses;
    public List<Animal> animals;


    public GrassField(int number) {
        this.n = number;
        List<Grass> grasses= new ArrayList<>();
        this.animals = new ArrayList<>();
        this.grasses = new ArrayList<>();
        placeGrasses(number);
    }

    // because i must implement everything
    @Override
    public boolean canMoveTo(Vector2d position) {
        // sprawdzam tylko inne zwierzeta, bo tylko to blokuje ruch
        if(!animals.isEmpty()){
            for (Animal animal : animals) {
                if (animal.getPosition().x == position.x && animal.getPosition().y == position.y) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeGrasses(int number){
        Random rand = new Random();
        int x;
        int y;
        for(int i = 0; i < number; i++){
            do{
                x = rand.nextInt((int)Math.sqrt(this.n * 10));
                y = rand.nextInt((int)Math.sqrt(this.n * 10));
//                System.out.println("x:" + x + " | y:" + y);
            }while(isOccupied(new Vector2d(x, y))); // jesli okupowany to powtarzaj
            this.grasses.add(new Grass(new Vector2d(x, y)));
        }
    }

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
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        // iterate through animals
        for(Animal animal : animals){
            if(animal.getPosition().x > maxX){
                maxX = animal.getPosition().x;
            }
            if(animal.getPosition().y > maxY){
                maxY = animal.getPosition().y;
            }
        }

        // iterate through grasses
        for(Grass grass : grasses){
            if(grass.getPosition().x > maxX){
                maxX = grass.getPosition().x;
            }
            if(grass.getPosition().y > maxY){
                maxY = grass.getPosition().y;
            }
        }
        MapVisualizer visual = new MapVisualizer(this);

        return visual.draw(new Vector2d(-1, -1), new Vector2d(maxX, maxY));
    }

    @Override
    public boolean place(Animal animal) {
        // sprawdzam czy dana pozycja zawiera sie w mapie
        // [TO DO] obecnosc zwierzat ma priorytet nad trawa ?
        if(!isOccupied(animal.getPosition())) {
            // placing animal
            animals.add(animal);
            return true;
        }
        else{
            // musimy to robic
            // prirytet zmierzecia nad trawa, chyba nie o to chodzi ?
//            System.out.println("XD1");
            Object object = objectAt(animal.getPosition());
            if(object instanceof Grass){
//                System.out.println("XD2");
                animals.add(animal);
                return true; // wazne, bo udalo sie dodac
            }
            else{
                return false;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(!animals.isEmpty()){
            for (Animal animal : animals) {
                if (animal.getPosition().x == position.x && animal.getPosition().y == position.y) {
                    return true;
                }
            }
        }
        if (!grasses.isEmpty()) {
            for(Grass grass : grasses) {
                if (grass.getPosition().x == position.x && grass.getPosition().y == position.y) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        // [ realizowanie wyzszego priorytetu zwierzat]
        for (Animal animal : animals){
            if(animal.getPosition().x == position.x && animal.getPosition().y == position.y){
                return animal;
            }
        }

        for (Grass grass : grasses){
            if(grass.getPosition().x == position.x && grass.getPosition().y == position.y){
                return grass;
            }
        }
        return null;
    }

    public void debugger(Animal animal, MoveDirection direction){
        System.out.println("Animal (x = " + animal.getPosition().x + " y = " + animal.getPosition().y
                + ") ruszy sie " + direction.toString());
        System.out.println(this.toString());
    }
}
