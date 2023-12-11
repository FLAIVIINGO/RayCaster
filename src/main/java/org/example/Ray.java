package org.example;

public class Ray {

    private Point origin;
    private Vector direction;

    public Ray(Point origin, Vector direction) {
        this.origin = new Point(origin.getX(), origin.getY(), origin.getZ());
        this.direction = new Vector(direction.getX(), direction.getY(), direction.getZ());
    }

    public Point getOrigin() {
        return this.origin;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public Point position(Ray ray, double t) {
        Point p = new Point(this.origin.getX(), this.origin.getY(), this.origin.getZ());
        Vector v = this.direction.multiplyScalar(t);
        p = p.add(v);
        return p;
    }

    public double dotProduct(Vector vector) {
        return this.direction.dotProduct(vector);
    }
}
