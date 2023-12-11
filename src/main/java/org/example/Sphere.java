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
        Tuple sphereToRay = ray.getOrigin().subtract(this.origin);
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = 2 * ray.getDirection().dotProduct((Vector)sphereToRay);
        double c = ((Vector) sphereToRay).dotProduct((Vector)sphereToRay) - 1;
        double discriminant = b * b - 4 * a * c;

        if(discriminant < 0) {
            return new double[0];
        }
        double t1 = (-b-Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b+Math.sqrt(discriminant)) / (2 * a);
        return new double[] {Math.max(t1, t2), Math.min(t1, t2)};
    }

}
