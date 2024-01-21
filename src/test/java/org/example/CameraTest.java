package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CameraTest {

    MatrixOperations mo = new MatrixOperations();
    @Test
    void constructingCamera() {
        int hsize = 160;
        int vsize = 120;
        double fieldOfView = Math.PI / 2;
        Camera c = new Camera(hsize, vsize, fieldOfView);
        assertEquals(c.getHsize(), 160);
        assertEquals(c.getVsize(), 120);
        assertEquals(c.getFieldOfView(), Math.PI / 2);
        assertTrue(mo.equalMatrices(c.getTransform(), mo.identityMatrix()));
    }

    @Test
    void rayThroughCenterCanvas() {
        Scene scene = new Scene();
        Camera c = new Camera(201, 101, Math.PI / 2);
        Ray ray = scene.rayForPixel(c, 100, 50);
        assertTrue(ray.getOrigin().equalTuples(new Tuple(0, 0, 0, 1)));
        assertTrue(ray.getDirection().equalTuples(new Tuple(0, 0, -1, 0)));
    }

    @Test
    void rayThroughCornerCanvas() {
        Scene scene = new Scene();
        Camera c = new Camera(201, 101, Math.PI / 2);
        Ray ray = scene.rayForPixel(c, 0, 0);
        assertTrue(ray.getOrigin().equalTuples(new Tuple(0, 0, 0, 1)));
        assertTrue(ray.getDirection().equalTuples(new Tuple(0.66519, 0.33259, -0.66851, 0)));
    }

    @Test
    void rayTransformedCanvas() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        Camera c = new Camera(201, 101, Math.PI / 2);
        double[][] rotation = mo.rotationY(Math.PI / 4);
        double[][] translation = mo.translation(0, -2, 5);
        c.setTransform(mo.multiplyMatrices(rotation, translation));
        Ray ray = scene.rayForPixel(c, 100, 50);
        assertTrue(ray.getOrigin().equalTuples(new Tuple(0, 2, -5, 1)));
        assertTrue(ray.getDirection().equalTuples(new Tuple(Math.sqrt(2)/2, 0, -Math.sqrt(2)/2, 0)));
    }

    @Test
    void testRender() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        World w = new World();
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        Sphere s1 = new Sphere();
        s1.getMaterial().setColor(new Color(0.8, 1.0, 0.6));
        s1.getMaterial().setDiffuse(0.7);
        s1.getMaterial().setSpecular(0.2);
        w.setLight(light);
        w.addShape(s1);
        Sphere s2 = new Sphere();
        s2.setTransform(mo.scaling(0.5, 0.5, 0.5));
        w.addShape(s2);
        Camera c = new Camera(11, 11, Math.PI / 2);
        Tuple from = new Tuple(0, 0, -5, 1);
        Tuple to = new Tuple(0, 0, 0, 1);
        Tuple up = new Tuple(0, 1, 0, 0);
        c.setTransform(mo.viewTransform(from, to ,up));
        Canvas image = scene.render(c, w);
        assertTrue(image.getPixel(5, 5).equalColors(new Color(0.38066, 0.47583, 0.2855)));
    }
}
