package agh.cs.lab1;

public class World {

    public static void main(String[] args) {
	    System.out.println("Start of the system");
        run(changeToEnum(args));
	    System.out.println("End of the system");

    }

    public static Direction[] changeToEnum(String[] args){
        Direction[] arr = new Direction[args.length];
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
            switch (a){
                case FORWARD:
                    System.out.println("Beast move forward");
                    break;
                case BACKWARD:
                    System.out.println("Beast move backward");
                    break;
                case RIGHT:
                    System.out.println("Beast move right");
                    break;
                case LEFT:
                    System.out.println("Beast move left");
                    break;
            }
        }
    }
}
