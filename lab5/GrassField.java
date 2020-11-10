package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.*;

import java.lang.Math;

public class GrassField extends AbstractWorldMap {
    // debugger() inherited from AbstractWorldMap
    //   animals inherited from AbstractWorldMap
    // run() inherited from AbstractWorldMap
    // toString() inherited from AbstractWorldMap
    // width, height inherited from AbstractWorldMap


    int n;
    public List<Grass> grasses;

    public GrassField(int number) {
        // initialization of limits
        this.height = Integer.MIN_VALUE;
        this.width = Integer.MIN_VALUE;

        this.n = number;
        List<Grass> grasses= new ArrayList<>();
        this.animals = new ArrayList<>();
        this.grasses = new ArrayList<>();
        placeGrasses(number);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // I check only others Animals because only it can block move
        if(!animals.isEmpty()){
            for (Animal animal : animals) {
                if (animal.getPosition().x == position.x && animal.getPosition().y == position.y) {
                    return false;
                }
            }
        }
        return true;
    }

    // we update limits and then use them in toString() function
    private void updateLimits(Vector2d v){
        if(v.x > this.width){
            this.width = v.x;
        }
        if(v.y > this.height){
            this.height = v.y;
        }
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
            }while(isOccupied(new Vector2d(x, y))); // if place is occupied, fine new place
            updateLimits(new Vector2d(x, y));
            this.grasses.add(new Grass(new Vector2d(x, y)));
        }
    }

    @Override
    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPosition())) {
            // placing animal
            updateLimits(animal.getPosition());
            animals.add(animal);
            return true;
        }
        else{
//            System.out.println("XD1");
            Object object = objectAt(animal.getPosition());
            // if grass takes the place, put the animal anyway
            if(object instanceof Grass){
//                System.out.println("XD2");
                updateLimits(animal.getPosition());
                animals.add(animal);
                return true; // important, because we succeed in placing animal
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
        // [ fulfilling the higher priority of animals, we return it first]
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
}
