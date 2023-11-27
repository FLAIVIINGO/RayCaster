package org.example;

public class Tuple {
    protected double x;
    protected double y;
    protected double z;
    protected double w;

    public Tuple() {
        this(0, 0, 0, 0);
    }

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public boolean equal(double a, double b) {
        double EPSILON = 0.00001;
        return Math.abs(a - b) < EPSILON;
    }

    public boolean equalTuples(Tuple b) {
        return (equal(this.x, b.getX()) &&
                equal(this.y, b.getY()) &&
                equal(this.z, b.getZ()) &&
                equal(this.w, b.getW()));
    }

    public void negate() {
        this.x = 0 - this.x;
        this.y = 0 - this.y;
        this.z = 0 - this.z;
        this.w = 0 - this.w;
    }

    public void multiplyScalar(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}
