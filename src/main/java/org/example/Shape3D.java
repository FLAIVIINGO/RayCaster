package org.example;

import java.util.List;
import java.util.UUID;

public abstract class Shape3D {
    protected final UUID id;
    protected Tuple origin;

    protected double radius;

    protected double[][] transform;

    protected Material material;

    public Shape3D() {
        MatrixOperations mo = new MatrixOperations();
        this.id = UUID.randomUUID();
        this.origin = new Tuple(0, 0, 0, 1);
        this.radius = 1;
        this.transform = mo.identityMatrix();
        this.material = new Material();
    }

    public UUID getId() {
        return this.id;
    }

    public void setTransform(double[][] transform) { this.transform = transform; }

    public double[][] getTransform() {
        return this.transform;
    }

    public Material getMaterial() {
        return this.material;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setMaterial(double ambient, double diffuse, double specular, double shininess) {
        this.material.setAmbient(ambient);
        this.material.setDiffuse(diffuse);
        this.material.setSpecular(specular);
        this.material.setShininess(shininess);
    }

    public abstract Tuple normalAt(Tuple wp);

    public abstract List<Intersection> intersect(Ray ray);
}
