package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanvasTest {

    @Test
    void verifyNoExceptionThrown() {
        Main.main(new String[]{});
    }

    @Test
    void testCanvas() {
        Canvas canvas = new Canvas(3, 3);
        Color test = new Color(0, 0, 0);
        for(int i = 0; i < canvas.getWidth(); i++) {
            for(int j = 0; j < canvas.getHeight(); j++) {
                boolean eq = test.equalColors(canvas.getPixel(i, j));
                assertTrue(eq);
            }
        }
    }

    @Test
    void testWritePixel() {
        Color crimson = new Color(1, 0, 0);
        Canvas canvas = new Canvas(10, 20);
        canvas.setPixel(2, 3, crimson);
        boolean eq = crimson.equalColors(canvas.getPixel(2, 3));
        assertTrue(eq);
    }

    @Test
    void testLineLength() throws IOException {
        Canvas canvas = new Canvas(5, 3);
        Color c1 = new Color(1.5, 0, 0);
        Color c2 = new Color(0, 0.5, 0);
        Color c3 = new Color(-0.5, 0, 1);

        canvas.setPixel(0, 0, c1);
        canvas.setPixel(2, 1, c2);
        canvas.setPixel(4, 2, c3);
        canvas.canvasToPPM("canvas.txt");
        Path path = Paths.get("canvas.txt");
        Files.lines(path)
                .skip(3)  // Skip the first 3 lines to get to the 4th
                .findFirst().ifPresent(line -> assertTrue(line.length() <= 70));

    }

    @Test
    void testLongerLineLength() throws IOException{
        Canvas canvas = new Canvas(10, 2);
        Color color = new Color(1, 0.8, 0.6);
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 10; j++) {
                canvas.setPixel(j, i, color);
            }
        }
        canvas.canvasToPPM("canvas2.txt");

        Path path = Paths.get("canvas2.txt");
        Files.lines(path)
                .skip(3)  // Skip the first 3 lines to get to the 4th
                .findFirst().ifPresent(line -> assertTrue(line.length() <= 70));
    }

}
