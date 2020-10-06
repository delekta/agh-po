package agh.cs.lab1;

public class World {

    public static void main(String[] args) {
	    System.out.println("Start of the system");

	    // String[] arr = {"wrr", "brr", "grr"};
        //Moves[] arr = {Moves.FORWARD, Moves.LEFT, Moves.RIGHT};
        Direction[] arr = new Direction[args.length];
        arr = changeToEnum(arr, args);
        run(arr);


	    System.out.println("End of the system");
    }

    public static Direction[] changeToEnum(Direction[] arr, String[] args){
        for(int i = 0; i < args.length; i++){
            switch (args[i]){
                case "f":
                    arr[i] = Direction.FORWARD;
                    break;
                case "b":
                    arr[i] = Direction.BACKWARD;
                    break;
                case "r":
                    arr[i] = Direction.RIGHT;
                    break;
                case "l":
                    arr[i] = Direction.LEFT;
                    break;
            }
        }
        return arr;
    }

    public static void run(Direction[] arr){
        for(Direction a: arr){
            if(a == Direction.FORWARD){
                System.out.println("Beast move forward");
            }
            else if(a == Direction.BACKWARD){
                System.out.println("Beast move backward");
            }
            else if(a == Direction.RIGHT){
                System.out.println("Beast move right");
            }
            else if(a == Direction.LEFT){
                System.out.println("Beast move left");
            }
        }
    }
}
