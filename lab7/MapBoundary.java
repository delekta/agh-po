package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private Comparator yComparator = new YComparator();

    public Comparator getYComparator() {
        return yComparator;
    }

    public Comparator getXComparator() {
        return xComparator;
    }

    public SortedSet<IMapElement> getYSorted() {
        return ySorted;
    }

    public SortedSet<IMapElement> getXSorted() {
        return xSorted;
    }

    private Comparator xComparator = new XComparator();

    private SortedSet<IMapElement> ySorted = new TreeSet<>(yComparator);
    private SortedSet<IMapElement> xSorted = new TreeSet<>(xComparator);

    public void addXSorted(IMapElement element){
        xSorted.add(element);
    }

    public void addYSorted(IMapElement element){
        ySorted.add(element);
    }




    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
