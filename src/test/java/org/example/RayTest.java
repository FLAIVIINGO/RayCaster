package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RayTest {

    @Test
    void createRay() {
        Point p = new Point(1, 2, 3);
        Vector v = new Vector(3, 4, 5);
        Ray r = new Ray(p, v);
        assertTrue(p.equalTuples(r.getOrigin()));
        assertTrue(v.equalTuples(r.getDirection()));
    }

    @Test
    void testPosition() {
        Ray r = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));
        Point cmp1 = new Point(2, 3, 4);
        Point cmp2 = new Point(3, 3, 4);
        Point cmp3 = new Point(1, 3, 4);
        Point cmp4 = new Point(4.5, 3, 4);
        Point rslt1 = r.position(r, 0);
        Point rslt2 = r.position(r, 1);
        Point rslt3 = r.position(r, -1);
        Point rslt4 = r.position(r, 2.5);
        assertTrue(cmp1.equalTuples(rslt1));
        assertTrue(cmp2.equalTuples(rslt2));
        assertTrue(cmp3.equalTuples(rslt3));
        assertTrue(cmp4.equalTuples(rslt4));
    }
}
