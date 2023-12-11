package org.example;

import java.util.UUID;

public class Sphere {
    private final UUID id;
    private Point origin;
    private double radius;

    public Sphere() {
        this.id = UUID.randomUUID();
        this.origin = new Point(0, 0, 0);
        this.radius = 1;
    }

    public UUID getId() {
        return this.id;
    }

    public double[] intersect(Ray ray) {
        Vector sphereToRay = calculateVectorToCenter(ray.getOrigin(), this.origin);
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = 2 * ray.getDirection().dotProduct(sphereToRay);
        double c = sphereToRay.dotProduct((Vector)sphereToRay) - 1;
        double discriminant = b * b - 4 * a * c;

        if(discriminant < 0) {
            return new double[0];
        }
        double t1 = (-b-Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b+Math.sqrt(discriminant)) / (2 * a);
        return new double[] {Math.min(t1, t2), Math.max(t1, t2)};
    }

    private Vector calculateVectorToCenter(Point p1, Point p2) {
        // p1 = ray origin
        // p2 = sphere center (0, 0, 0)
        return new Vector(p1.getX() - p2.getX(), p1.getY() - p2.getY(), p1.getZ() - p2.getZ());
    }

}
