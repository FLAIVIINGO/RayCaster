package org.example;

import org.junit.jupiter.api.Test;

public class SphereTest {

    @Test
    void twoPointSphere() {
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        double[] sx = sphere.intersect(ray);
        System.out.println(sx[0]+" "+sx[1]);
    }

}
