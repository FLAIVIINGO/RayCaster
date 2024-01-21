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
}
