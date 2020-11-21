package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private Comparator yComparator = new YComparator();
    private Comparator xComparator = new XComparator();

    private SortedSet<IMapElement> ySorted = new TreeSet<>(yComparator);
    private SortedSet<IMapElement> xSorted = new TreeSet<>(xComparator);

    public void addXSorted(IMapElement element){
        xSorted.add(element);
    }

    public void addYSorted(IMapElement element){
        ySorted.add(element);
    }

    //Getters

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


    // Added during lab7
    // [aphollo github] 6. W przypadku aktualizacji pozycji obiektu, należy sprawdzić,
    // czy należy zaktualizować odpowiedni indeksi zrobić to, tylko jeśli jest to konieczne.
    // Rozumiem ze aktualizujemy gdy zmieniany element jest większy niż aktualnie największy element
    // My PositionChanged
    public void updateSortedElements(Vector2d oldPosition, Vector2d newPosition, IMapElement element){
        if(isGreaterThanLast(xComparator, xSorted, element)){
            addXSorted(element);

        }if(isGreaterThanLast(yComparator, ySorted, element)){
            addYSorted(element);
        }
    }

    // Added during lab7
    // If added element or updated element is greater than last in sortedSet => update sortedSet
    private boolean isGreaterThanLast(Comparator comparator, SortedSet sortedSet, IMapElement element){
        if(comparator.compare(element, sortedSet.last()) > 0){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
