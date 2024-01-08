package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        int cnt = 0;
        for(Intersection i : xs) {
            System.out.println(cnt+": "+i.getTime());
            cnt++;
        }
    }
}
