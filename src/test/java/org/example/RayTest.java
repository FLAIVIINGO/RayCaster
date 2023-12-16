package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RayTest {

    @Test
    void createRay() {
        Tuple p = new Tuple(1, 2, 3, 1);
        Tuple v = new Tuple(3, 4, 5, 0);
        Ray r = new Ray(p, v);
        assertTrue(p.equalTuples(r.getOrigin()));
        assertTrue(v.equalTuples(r.getDirection()));
    }

    @Test
    void testPosition() {
        Ray r = new Ray(new Tuple(2, 3, 4, 1), new Tuple(1, 0, 0, 0));
        Tuple cmp1 = new Tuple(2, 3, 4, 1);
        Tuple cmp2 = new Tuple(3, 3, 4, 1);
        Tuple cmp3 = new Tuple(1, 3, 4, 1);
        Tuple cmp4 = new Tuple(4.5, 3, 4, 1);
        Tuple rslt1 = r.position(r, 0);
        Tuple rslt2 = r.position(r, 1);
        Tuple rslt3 = r.position(r, -1);
        Tuple rslt4 = r.position(r, 2.5);
        assertTrue(cmp1.equalTuples(rslt1));
        assertTrue(cmp2.equalTuples(rslt2));
        assertTrue(cmp3.equalTuples(rslt3));
        assertTrue(cmp4.equalTuples(rslt4));
    }
}
