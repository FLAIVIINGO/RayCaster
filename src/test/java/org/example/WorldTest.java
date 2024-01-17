package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
    @Test
    void changeDefaultWorld() {
        MatrixOperations mo = new MatrixOperations();
        World world = new World();
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        world.setLight(light);
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        world.addShape(s1);
        world.addShape(s2);

        assertTrue(world.getLight().getPosition().equalTuples(light.getPosition()));
        assertEquals(world.getObjects().size(), 2);
    }

    // Finish Test
    @Test
    void intersectWorldTest() {
        /*
        *
        * default world: light point (-10, 10, -10) light color (1, 1, 1)
        * outermost sphere r = 1 : innermost sphere r = 0.5
        * */
        MatrixOperations matrixOperations = new MatrixOperations();
        World world = new World();
        Scene scene = new Scene();
        Sphere s1 = new Sphere();
        Sphere s2 = new Sphere();
        s2.setRadius(0.5);
        s2.setTransform(matrixOperations.scaling(0.5, 0.5, 0.5));
        world.addShape(s1);
        world.addShape(s2);

        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        scene.intersectWorld(world, ray);
        List<Intersection> xs = scene.intersectWorld(world, ray);
        assertEquals(xs.get(0).getTime(), 4);
        assertEquals(xs.get(1).getTime(), 4.5);
        assertEquals(xs.get(2).getTime(), 5.5);
        assertEquals(xs.get(3).getTime(), 6);
    }

    @Test
    void computationTest1() {
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        Shape3D shape = new Sphere();
        Intersection intersection = new Intersection(4, shape);
        Computations computations = new Computations(intersection, r);
        assertTrue(computations.getPoint().equalTuples(new Tuple(0, 0, -1, 1)));
        assertTrue(computations.getEyev().equalTuples(new Tuple(0, 0, -1, 0)));
        assertTrue(computations.getNormalv().equalTuples(new Tuple(0, 0, -1, 0)));
    }

    @Test
    void computationTest2() {
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        Shape3D shape = new Sphere();
        Intersection intersection = new Intersection(4, shape);
        Computations computations = new Computations(intersection, r);
        assertFalse(computations.getInside());
    }

    @Test
    void computationTest3() {
        Ray r = new Ray(new Tuple(0, 0, 0, 1), new Tuple(0, 0, 1, 0));
        Shape3D shape = new Sphere();
        Intersection intersection = new Intersection(1, shape);
        Computations computations = new Computations(intersection, r);
        assertTrue(computations.getPoint().equalTuples(new Tuple(0, 0, 1, 1)));
        assertTrue(computations.getEyev().equalTuples(new Tuple(0, 0, -1, 0)));
        assertTrue(computations.getInside());
        assertTrue(computations.getNormalv().equalTuples(new Tuple(0, 0, -1, 0)));
    }

    @Test
    void shadeIntersectTest1() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World world = new World();
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        world.setLight(light);
        world.addShape(s1);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        world.addShape(s2);
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        Intersection i = new Intersection(4, world.getShape(0));
        Computations computations = new Computations(i, r);
        Color color = scene.shadeHit(world, computations);
        assertTrue(color.equalColors(new Color(0.38066, 0.47583, 0.2855)));
    }

    @Test
    void shadeIntersectTest2() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World world = new World();
        world.setLight(new Light(new Color(1, 1, 1), new Tuple(0, 0.25, 0, 1)));
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        world.addShape(s1);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        world.addShape(s2);
        Ray r = new Ray(new Tuple(0, 0, 0, 1), new Tuple(0, 0, 1, 0));
        Intersection i = new Intersection(0.5, world.getShape(1));
        Computations comps = new Computations(i, r);
        Color c = scene.shadeHit(world, comps);
        assertTrue(c.equalColors(new Color(0.90498, 0.90498, 0.90498)));
    }

    @Test
    void colorAtTest1() {
        Scene scene = new Scene();
        World world = new World();
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 1, 0, 0));
        Color c = scene.colorAt(world, r);
    }

    @Test
    void colorAtTest2() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World world = new World();
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        world.setLight(light);
        world.addShape(s1);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        world.addShape(s2);
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        Color c = scene.colorAt(world, r);
        assertTrue(c.equalColors(new Color(0.38066, 0.47583, 0.2855)));
    }

    @Test
    void colorAtTest3() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World world = new World();
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        world.setLight(light);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        s2.getMaterial().setAmbient(1);
        s1.getMaterial().setAmbient(1);
        world.addShape(s2);
        world.addShape(s1);
        Ray ray = new Ray(new Tuple(0, 0, 0.75, 1), new Tuple(0, 0, -1, 0));
        Color c = scene.colorAt(world, ray);
        assertTrue(c.equalColors(new Color(1, 1, 1)));
    }
}
