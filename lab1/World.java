package agh.cs.lab1;

public class World {

    public static void main(String[] args) {
	    System.out.println("Start of the system");

	    // String[] arr = {"wrr", "brr", "grr"};
        //Moves[] arr = {Moves.FORWARD, Moves.LEFT, Moves.RIGHT};
        Moves[] arr = new Moves[args.length];
        arr = changeToEnum(arr, args);
        run(arr);


	    System.out.println("End of the system");
    }

    public static Moves[] changeToEnum(Moves[] arr, String[] args){
        for(int i = 0; i < args.length; i++){
            switch (args[i]){
                case "f":
                    arr[i] = Moves.FORWARD;
                    break;
                case "b":
                    arr[i] = Moves.BACKWARD;
                    break;
                case "r":
                    arr[i] = Moves.RIGHT;
                    break;
                case "l":
                    arr[i] = Moves.LEFT;
                    break;
            }
        }
        return arr;
    }

    public static void run(Moves[] arr){
        for(Moves a: arr){
            if(a == Moves.FORWARD){
                System.out.println("Beast move forward");
            }
            else if(a == Moves.BACKWARD){
                System.out.println("Beast move backward");
            }
            else if(a == Moves.RIGHT){
                System.out.println("Beast move right");
            }
            else if(a == Moves.LEFT){
                System.out.println("Beast move left");
            }
        }

        /*System.out.print("Beast move forward");

        for(String a :arr){
            System.out.print(", " + a);
        }
        System.out.println(".");*/
    }
}
