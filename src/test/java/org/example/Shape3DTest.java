package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Shape3DTest {

    @Test
    void defaultPlaneParallel1() {
        Plane p = new Plane();
        Ray r = new Ray(new Tuple(0, 10, 0, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = p.intersect(r);
        assertTrue(xs.isEmpty());
    }

    @Test
    void defaultPlaneParallel2() {
        Plane p = new Plane();
        Ray r = new Ray(new Tuple(0, 0, 0, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = p.intersect(r);
        assertTrue(xs.isEmpty());
    }

    @Test
    void rayIntersectPlane1() {
        Plane p = new Plane();
        Ray r = new Ray(new Tuple(0, 1, 0, 1), new Tuple(0, -1, 0, 0));
        List<Intersection> xs = p.intersect(r);
        assertEquals(1, xs.size());
        assertEquals(1, xs.get(0).getTime());
    }
}
