package agh.cs.lab2.tests;

import agh.cs.lab2.Vector2d;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest extends TestCase {

    Vector2d v1 = new Vector2d(1, 2);
    Vector2d v2 = new Vector2d(99,99);
    Vector2d v3 = new Vector2d(99,99);
    Vector2d v4 = new Vector2d(3,2);
    Vector2d v5 = new Vector2d(4,1);
    Object other = new Object();

    @Test
    public void testToString() {
        Assert.assertEquals(v1.toString(), "(1, 2)");
        Assert.assertEquals(v2.toString(), "(99, 99)");
        Assert.assertEquals(v3.toString(), "(99, 99)");
        Assert.assertEquals(v4.toString(), "(3, 2)");
        Assert.assertEquals(v5.toString(), "(4, 1)");
    }

    @Test
    public void equalsTest(){
        Assert.assertTrue(v1.equals(v1));
        Assert.assertFalse(v1.equals(v2));
        Assert.assertTrue(v2.equals(v3));
        Assert.assertFalse(v1.equals(other));
    }

    @Test
    public void testPrecedes() {
        Assert.assertTrue(v1.precedes(v4));
        Assert.assertTrue(v2.precedes(v3));
        Assert.assertFalse(v2.precedes(v4));
    }

    @Test
    public void testFollows() {
        Assert.assertFalse(v1.follows(v4));
        Assert.assertTrue(v2.follows(v3));
        Assert.assertTrue(v2.follows(v4));
    }

    @Test
    public void testUpperRight() {
        Assert.assertEquals(v1.upperRight(v5),new Vector2d(4, 2));
        Assert.assertEquals(v1.upperRight(v2),new Vector2d(99, 99));
        Assert.assertEquals((new Vector2d(7, 13)).upperRight(new Vector2d(9, 2)),new Vector2d(9, 13));
    }

    @Test
    public void testLowerLeft() {
        Assert.assertEquals(v1.lowerLeft(v5),new Vector2d(1, 1));
        Assert.assertEquals(v1.lowerLeft(v2),new Vector2d(1, 2));
        Assert.assertEquals((new Vector2d(7, 13)).lowerLeft(new Vector2d(9, 2)),new Vector2d(7, 2));

    }

    public void testAdd() {
        Assert.assertEquals(v1.add(v5),new Vector2d(5, 3));
        Assert.assertEquals(v1.add(v2),new Vector2d(100, 101));
        Assert.assertEquals((new Vector2d(7, 13)).add(new Vector2d(9, 2)),new Vector2d(16, 15));
    }

    public void testSubtract() {
        Assert.assertEquals(v1.subtract(v5),new Vector2d(-3, 1));
        Assert.assertEquals(v1.subtract(v2),new Vector2d(-98, -97));
        Assert.assertEquals((new Vector2d(7, 13)).subtract(new Vector2d(9, 2)),new Vector2d(-2, 11));
    }

    public void testOpposite() {
        Assert.assertEquals(v1.opposite(),new Vector2d(-1, -2));
        Assert.assertEquals(v2.opposite(),new Vector2d(-99, -99));
        Assert.assertEquals(v4.opposite(), new Vector2d(-3, -2));
    }
    
}