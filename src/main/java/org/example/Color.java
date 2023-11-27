package org.example;

public class Color {
    private double red;
    private double green;
    private double blue;

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean equal(double a, double b) {
        double EPSILON = 0.00001;
        return Math.abs(a - b) < EPSILON;
    }

    public boolean equalColors(Color c) {
        return (equal(this.red, c.getRed()) &&
                equal(this.green, c.getGreen()) &&
                equal(this.blue, c.getBlue()));
    }

    public Color addColors(Color c) {
        return new Color(this.red + c.red, this.green + c.green, this.blue + c.blue);
    }

    public Color subtractColors(Color c) {
        return new Color(this.red - c.red, this.green - c.green, this.blue - c.blue);
    }

    public void scalarColor(double scalar) {
        this.red *= scalar;
        this.green *= scalar;
        this.blue *= scalar;
    }

    public Color hadamardProduct(Color c) {
        return new Color(this.red * c.red, this.green * c.green, this.blue * c.blue);
    }

    public double getRed() { return red; }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }
}
