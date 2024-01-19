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
}
