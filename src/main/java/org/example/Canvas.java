package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Canvas {
    private final Color[][] canvas;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        canvas = new Color[height][width];
        this.width = width;
        this.height = height;
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                canvas[i][j] = new Color(0, 0, 0);
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getPixel(int x, int y) {
        if(x < 0 || y < 0 || x >= this.width || y >= this.height) {
            throw new IndexOutOfBoundsException();
        }
        return this.canvas[y][x];
    }

    public void setPixel(int x, int y, Color c) {
        if(x < 0 || y < 0 || x >= this.width || y >= this.height) {
            throw new IndexOutOfBoundsException();
        }
        this.canvas[y][x] = c;
    }

    public void canvasToPPM(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);){
            writer.println("P3");
            writer.println(this.width + " " + this.height);
            writer.println("255");
            int count = 0;
            for(int i = 0; i < this.height; i++) {
                for(int j = 0; j < this.width; j++) {
                    int[] rgb = {
                            (int)Math.ceil(Math.max(0, Math.min(1, this.canvas[i][j].getRed()) * 255)),
                            (int)Math.ceil(Math.max(0, Math.min(1, this.canvas[i][j].getGreen()) * 255)),
                            (int)Math.ceil(Math.max(0, Math.min(1, this.canvas[i][j].getBlue()) * 255))
                    };
                    for (int value : rgb) {
                        String valueString = Integer.toString(value);

                        if (count + valueString.length() + 1 > 70) {
                            writer.println();
                            count = 0;
                        }

                        writer.print(valueString + " ");
                        count += valueString.length() + 1;
                    }
                }

                writer.println();
                count = 0;
            }
            writer.println();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
