package agh.cs.lab7;

import agh.cs.lab3.Animal;
import agh.cs.lab5.Grass;

import java.util.Comparator;

public class YComparator implements Comparator<IMapElement> {

    @Override
    public int compare(IMapElement o1, IMapElement o2) {
        if(o1.getPosition().y > o2.getPosition().y ){
           return 1;
        }
        else if(o1.getPosition().y == o2.getPosition().y ){
            if(o1.getPosition().x > o2.getPosition().x){
                return 1;
            }
            else if(o1.getPosition().x < o2.getPosition().x){
                return -1;
            }
            else if (o1 instanceof Animal && o2 instanceof Grass){
                return 1;
            }
            else if(o1 instanceof Grass && o2 instanceof Animal){
                return -1;
            }
            else {
                return 0;
            }
        }
        return -1;
    }
}
