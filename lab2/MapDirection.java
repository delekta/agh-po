package agh.cs.lab2;


public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;


    public String toString(){
        switch(this) {
            case NORTH: return "NORTH - Północ";
            case SOUTH: return "SOUTH - Południe";
            case WEST: return "WEST - Zachód";
            case EAST: return "EAST - Wschód";
            default: return " ";
        }
    }

    public MapDirection next(){
        switch(this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case WEST:  return NORTH;
            case EAST: return SOUTH;
            default: return EAST;
        }
    }

    public MapDirection previous(){
        switch(this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST:  return SOUTH;
            case EAST: return NORTH;
            default: return WEST;
        }

    }
    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return  new Vector2d(0, -1);
            case WEST: return  new Vector2d(-1, 0);
            case EAST: return new Vector2d(1,0);
            default: return new Vector2d(0, 0);
        }

    }
}
