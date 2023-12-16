package org.example;

public class Intersection {

    private Shape3D shape;
    private double time;

    public Intersection(double time, Shape3D shape) {
        this.time = time;
        this.shape = shape;
    }

    public double getTime() {
        return this.time;
    }

    public Shape3D getShape() {
        return this.shape;
    }

    public boolean equals(Intersection i) {
        return i.time == this.time && this.shape.id.equals(i.shape.id);
    }
}
