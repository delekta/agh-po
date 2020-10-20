package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

public class OptionsParser {
    public MoveDirection[] parse(String[] moves){
        int count = 0;
        String[] familiar = new String[]{"f", "forward", "l", "left", "b", "backward", "r", "right"};

        // Counting familiar directions, Can it be done different? Because need result length.
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < familiar.length; j++){
                if(moves[i].equals(familiar[j])){
                    count++;
                    break;
                }
            }
        }

        MoveDirection[] result = new MoveDirection[count];
        int idx = 0;
        for(int i = 0; i < moves.length; i++){
            switch (moves[i]){
                case "f":
                case "forward":
                    result[idx] = MoveDirection.FORWARD;
                    idx++;
                    break;
                case "r":
                case "right":
                    result[idx] = MoveDirection.RIGHT;
                    idx++;
                    break;
                case "b":
                case "backward":
                    result[idx] = MoveDirection.BACKWARD;
                    idx++;
                    break;
                case "l":
                case "left":
                    result[idx] = MoveDirection.LEFT;
                    idx++;
                    break;
            }
        }
        return result;
    }
}
