package org.example;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public Tuple normalAt(Tuple wp) {
        // Convert the world point to object space
        double[][] inverseTransformation = mo.inverse(this.getTransform(), mo.determinant(this.getTransform()));
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
    @Override
    public List<Intersection> intersect(Ray ray) {
        // transform ray before calculation
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] transform = matrixOperations.inverse(this.getTransform(), matrixOperations.determinant(this.getTransform()));
        Tuple originTransform = matrixOperations.multiplyMatrixByTuple(transform, ray.getOrigin());
        Tuple directionTransform = matrixOperations.multiplyMatrixByTuple(transform, ray.getDirection());
        Ray transformedRay = new Ray(originTransform, directionTransform);
        Tuple sphereToRay = calculateVectorToCenter(transformedRay.getOrigin(), this.origin);
        double a = transformedRay.getDirection().dotProduct(transformedRay.getDirection());
        double b = 2 * transformedRay.getDirection().dotProduct(sphereToRay);
        double c = sphereToRay.dotProduct(sphereToRay) - 1;
        double discriminant = b * b - 4 * a * c;

        if(discriminant < 0) {
            return new ArrayList<>();
        }
        double t1 = (-b-Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b+Math.sqrt(discriminant)) / (2 * a);


        Intersection i1 = new Intersection(t1, this);
        Intersection i2 = new Intersection(t2, this);
        return new ArrayList<>(Arrays.asList(i1, i2));
    }

    private Tuple calculateVectorToCenter(Tuple p1, Tuple p2) {
        // p1 = ray origin
        // p2 = sphere center (0, 0, 0)
        return new Tuple(p1.getX() - p2.getX(), p1.getY() - p2.getY(), p1.getZ() - p2.getZ(), 0);
    }

}
