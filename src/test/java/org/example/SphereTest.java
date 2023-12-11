package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTest {

    @Test
    void twoPointSphere() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        double[] xs = sphere.intersect(ray);
        assertEquals(xs[0], 4.0);
        assertEquals(xs[1], 6.0);
    }

    @Test
    void tangentPointSphere() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        double[] xs = sphere.intersect(ray);
        assertEquals(xs[0], 5.0);
        assertEquals(xs[1], 5.0);
    }

    @Test
    void rayMissesSphere() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
        double[] xs = sphere.intersect(ray);
        assertEquals(xs.length, 0);
    }

    @Test
    void rayInsideSphere() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double[] xs = sphere.intersect(ray);
        assertEquals(xs[0], -1.0);
        assertEquals(xs[1], 1.0);
    }

    @Test
    void sphereBehindRay() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
        double[] xs = sphere.intersect(ray);
        assertEquals(xs[0], -6);
        assertEquals(xs[1], -4);
    }
}
