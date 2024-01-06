package org.example;

public class Ray {

    private Tuple origin;
    private Tuple direction;

    public Ray(Tuple origin, Tuple direction) {
        this.origin = new Tuple(origin.getX(), origin.getY(), origin.getZ(), 1);
        this.direction = new Tuple(direction.getX(), direction.getY(), direction.getZ(), 0);
    }

    public Tuple getOrigin() {
        return this.origin;
    }

    public Tuple getDirection() {
        return this.direction;
    }


    public Tuple position(Ray ray, double t) {
        Tuple p = new Tuple(this.origin.getX(), this.origin.getY(), this.origin.getZ(), 1);
        Tuple v = this.direction.multiplyScalar(t);
        p = p.add(v);
        return p;
    }

    public void normalizeDirection() {
        this.direction = this.direction.normal();
    }

    public double dotProduct(Tuple vector) {
        return this.direction.dotProduct(vector);
    }
}
