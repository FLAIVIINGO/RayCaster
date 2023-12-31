package org.example;

import java.util.List;
import java.util.UUID;

public class Sphere extends Shape3D{

    private MatrixOperations mo = new MatrixOperations();
    private final UUID id;
    private Tuple origin;
    private double radius;

    private Material material;

    private double[][] transform;

    public Sphere() {
        this.id = UUID.randomUUID();
        this.origin = new Tuple(0, 0, 0, 1);
        this.radius = 1;
        this.material = new Material();
        this.transform = mo.identityMatrix();
    }

    public UUID getId() {
        return this.id;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setTransform(double[][] transform) { this.transform = transform; }

    public double[][] getTransform() {
        return this.transform;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(double ambient, double diffuse, double specular, double shininess) {
        this.material.setAmbient(ambient);
        this.material.setDiffuse(diffuse);
        this.material.setSpecular(specular);
        this.material.setShininess(shininess);
    }

    public Tuple normalAt(Sphere s, Tuple wp) {
        // Convert the world point to object space
        double[][] inverseTransformation = mo.inverse(s.getTransform(), mo.determinant(s.getTransform()));
        Tuple objectPoint = mo.multiplyMatrixByTuple(inverseTransformation, wp);

        // Compute the object space normal
        Tuple objectNormal = objectPoint.subtract(new Tuple(0, 0, 0, 1));

        // Convert the object space normal to world space
        double[][] transposedInverse = mo.transposeMatrix(inverseTransformation);

        Tuple worldNormal = mo.multiplyMatrixByTuple(transposedInverse, objectNormal);

        worldNormal.setW(0); // Ensure w is 0 to make it a vector
        // Normalize the world space normal

        return worldNormal.normal();
    }

}
