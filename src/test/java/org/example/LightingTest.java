package org.example;

import org.junit.jupiter.api.Test;

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
}
