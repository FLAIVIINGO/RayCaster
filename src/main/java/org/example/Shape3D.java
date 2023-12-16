package org.example;

import java.util.UUID;

public abstract class Shape3D {
    protected final UUID id;
    protected Tuple origin;

    protected double radius;

    protected double[][] transform;

    public Shape3D() {
        MatrixOperations mo = new MatrixOperations();
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
