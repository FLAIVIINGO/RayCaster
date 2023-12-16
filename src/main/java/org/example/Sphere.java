package org.example;

import java.util.List;
import java.util.UUID;

public class Sphere extends Shape3D{

    private MatrixOperations mo = new MatrixOperations();
    private final UUID id;
    private Tuple origin;
    private double radius;

    private double[][] transform;

    public Sphere() {
        this.id = UUID.randomUUID();
        this.origin = new Tuple(0, 0, 0, 1);
        this.radius = 1;
        this.transform = mo.identityMatrix();
    }

    public UUID getId() {
        return this.id;
    }

    public void setTransform(double[][] transform) { this.transform = transform; }

    public double[][] getTransform() {
        return this.transform;
    }

}
