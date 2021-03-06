package agh.cs.lab2;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + this.x + ", " + this.y+ ")";
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.x;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.x;
    }

    public Vector2d upperRight(Vector2d other){
        int biggerX = Math.max(this.x, other.x);
        int biggerY = Math.max(this.y, other.y);
        return new Vector2d(biggerX, biggerY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int smallerX = Math.min(this.x, other.x);
        int smallerY = Math.min(this.y, other.y);
        return new Vector2d(smallerX, smallerY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        return this.x == that.x && this.y == that.y;
    }

    // added during lab6
    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

}
