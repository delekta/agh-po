package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public LinkedList<MoveDirection> parse(String[] moves){
        LinkedList<MoveDirection> result = new LinkedList<>();

        for(int i = 0; i < moves.length; i++){
            switch (moves[i]){
                case "f":
                case "forward":
                    result.add(MoveDirection.FORWARD);
                    break;
                case "r":
                case "right":
                    result.add(MoveDirection.RIGHT);
                    break;
                case "b":
                case "backward":
                    result.add(MoveDirection.BACKWARD);
                    break;
                case "l":
                case "left":
                    result.add(MoveDirection.LEFT);
                    break;
            }
        }
        return result;
    }
}
