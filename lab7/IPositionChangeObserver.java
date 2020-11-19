package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;

public interface IPositionChangeObserver {
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
