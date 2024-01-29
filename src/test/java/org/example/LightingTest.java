package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightingTest {

    @Test
    void lightEye1() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, 0, -1, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 0, -10, 1));
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, false);
        assertTrue(result.equalColors(new Color(1.9, 1.9, 1.9)));
    }

    @Test
    void lightEye2() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, Math.sqrt(2)/2, -Math.sqrt(2)/2, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 0, -10, 1));
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, false);
        assertTrue(result.equalColors(new Color(1.0, 1.0, 1.0)));
    }

    @Test
    void lightEye3() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, 0, -1, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 10, -10, 1));
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, false);
        assertTrue(result.equalColors(new Color(0.7364, 0.7364, 0.7364)));
    }

    @Test
    void lightEye4() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, -Math.sqrt(2)/2, -Math.sqrt(2)/2, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 10, -10, 1));
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, false);
        assertTrue(result.equalColors(new Color(1.6364, 1.6364, 1.6364)));
    }

    @Test
    void lightEye5() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, 0, -1, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 0, 10, 1));
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, false);
        assertTrue(result.equalColors(new Color(0.1, 0.1, 0.1)));
    }

    @Test
    void lightEye6() {
        Scene scene = new Scene();
        Tuple eyev = new Tuple(0, 0, -1, 0);
        Tuple normalv = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 0, -10, 1));
        boolean inShadow = true;
        Color result = scene.lighting(new Material(), new Tuple(0, 0, 0, 1), light, eyev, normalv, inShadow);
        assertTrue(result.equalColors(new Color(0.1, 0.1, 0.1)));
    }

    @Test
    void shadowTest1() {
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
        Tuple point = new Tuple(0, 10, 0, 1);
        boolean result = scene.isShadowed(world, point);
        assertFalse(result);
    }

    @Test
    void shadowTest2() {
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
        Tuple point = new Tuple(10, -10, 10, 1);
        boolean result = scene.isShadowed(world, point);
        assertTrue(result);
    }

    @Test
    void shadowTest3() {
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
        Tuple point = new Tuple(-20, 20, -20, 1);
        boolean result = scene.isShadowed(world, point);
        assertFalse(result);
    }

    @Test
    void shadowTest4() {
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
        Tuple point = new Tuple(-2, 2, -2, 1);
        boolean result = scene.isShadowed(world, point);
        assertFalse(result);
    }

    @Test
    void renderShadowTest1() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World w = new World();
        w.setLight(new Light(new Color(1, 1, 1), new Tuple(0, 0, -10, 1)));
        Sphere s1 = new Sphere();
        w.addShape(s1);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.translation(0, 0, 10));
        w.addShape(s2);
        Ray r = new Ray(new Tuple(0, 0, 5, 1), new Tuple(0, 0, 1, 0));
        Intersection i = new Intersection(4, s2);
        Computations comps = new Computations(i, r);
        Color c = scene.shadeHit(w, comps);
        assertTrue(c.equalColors(new Color(0.1, 0.1, 0.1)));
    }

    @Test
    void renderShadowTest2() {
        MatrixOperations mo = new MatrixOperations();
        Ray r = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        Sphere s = new Sphere();
        s.setTransform(mo.translation(0, 0, 1));
        Intersection i = new Intersection(5, s);
        Computations comps = new Computations(i, r);
        assertTrue(comps.getOverPoint().getZ() < -0.00001/2);
        assertTrue(comps.getPoint().getZ() > comps.getOverPoint().getZ());
    }
}
