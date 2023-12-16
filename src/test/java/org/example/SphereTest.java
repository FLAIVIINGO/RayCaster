package org.example;

import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.PrinterStateReasons;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTest {
    @Test
    void twoPointSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        assertTrue(scene.intersect(sphere, ray));
        assertEquals(scene.getShapes().get(0).getTime(), 4.0);
        assertEquals(scene.getShapes().get(1).getTime(), 6.0);
    }

    @Test
    void tangentPointSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 1, -5, 0), new Tuple(0, 0, 1, 0));
        assertTrue(scene.intersect(sphere, ray));
        assertEquals(scene.getShapes().get(0).getTime(), 5.0);
        assertEquals(scene.getShapes().get(1).getTime(), 5.0);
    }

    @Test
    void rayMissesSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 2, -5, 1), new Tuple(0, 0, 1, 0));
        assertFalse(scene.intersect(sphere, ray));
    }

    @Test
    void rayInsideSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 0, 0, 1), new Tuple(0, 0, 1, 0));
        assertTrue(scene.intersect(sphere, ray));
        assertEquals(scene.getShapes().get(0).getTime(), -1.0);
        assertEquals(scene.getShapes().get(1).getTime(), 1.0);
    }

    @Test
    void sphereBehindRay() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 0, 5, 1), new Tuple(0, 0, 1, 0));
        assertTrue(scene.intersect(sphere, ray));
        assertEquals(scene.getShapes().get(0).getTime(), -6);
        assertEquals(scene.getShapes().get(1).getTime(), -4);
    }

    @Test
    void intersectionEncapsulationTest() {
        Sphere s = new Sphere();
        Intersection i = new Intersection(3.5, s);
        assertEquals(i.getTime(), 3.5);
        assertEquals(i.getShape().getClass(), Sphere.class);
    }

    @Test
    void aggregatingIntersections() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(1, sphere);
        Intersection i2 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        assertEquals(2, xs.size());
        assertEquals(xs.get(0).getTime(), 1);
        assertEquals(xs.get(1).getTime(), 2);
    }

    @Test
    void setObjectIntersection() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        assertTrue(scene.intersect(sphere, ray));
        assertEquals(scene.getShapes().get(0).getShape().getClass(), Sphere.class);
        assertEquals(scene.getShapes().get(1).getShape().getClass(), Sphere.class);
    }

    @Test
    void positiveTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(1, sphere);
        Intersection i2 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i1));
    }

    @Test
    void someNegativeTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(-1, sphere);
        Intersection i2 = new Intersection(1, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i2));
    }

    @Test
    void allNegativeTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(-2, sphere);
        Intersection i2 = new Intersection(-1, sphere);
        List<Intersection> xs = scene.intersections(i2, i1);
        Intersection i = scene.hit(xs);
        assertNull(i);
    }

    @Test
    void alwaysLowTHit() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(5, sphere);
        Intersection i2 = new Intersection(7, sphere);
        Intersection i3 = new Intersection(-3, sphere);
        Intersection i4 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2, i3, i4);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i4));
    }

    @Test
    void translatingRay() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(1, 2, 3, 1), new Tuple(0, 1, 0, 0));
        double[][] m = mo.translation(3, 4, 5);
        Ray r2 = scene.transform(ray, m);
        assertTrue(r2.getOrigin().equalTuples(new Tuple(4, 6, 8, 1)));
        assertTrue(r2.getDirection().equalTuples(new Tuple(0, 1, 0, 0)));
    }

    @Test
    void scalingRay() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(1, 2, 3, 1), new Tuple(0, 1, 0, 0));
        double[][] m = mo.scaling(2, 3, 4);
        Ray r2 = scene.transform(ray, m);
        assertTrue(r2.getOrigin().equalTuples(new Tuple(2, 6, 12, 1)));
        assertTrue(r2.getDirection().equalTuples(new Tuple(0, 3, 0, 0)));
    }

    @Test
    void defaultSphereTransform() {
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        assertTrue(mo.equalMatrices(identity, sphere.getTransform()));
    }

    @Test
    void changeSphereTransform() {
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] t = mo.translation(2, 3, 4);
        sphere.setTransform(t);
        assertTrue(mo.equalMatrices(t, sphere.getTransform()));
    }

    @Test
    void intersectScaledSphere1() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        double[][] t = mo.scaling(2, 2, 2);
        sphere.setTransform(t);
        assertTrue(scene.intersect(sphere, ray));
        List<Intersection> xs = scene.getShapes();
        assertEquals(xs.get(0).getTime(), 3);
        assertEquals(xs.get(1).getTime(), 7);
    }

    @Test
    void intersectScaledSphere2() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        sphere.setTransform(mo.translation(5, 0, 0));
        assertFalse(scene.intersect(sphere, ray));
    }
}
