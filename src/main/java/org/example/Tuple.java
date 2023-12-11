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

    public Tuple subtract(Tuple t) {
        return new Vector(t.x - this.x, t.y - this.y, t.z - this.z);
    }

    public Tuple multiplyScalar(double scalar) {
        return new Tuple(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar,
                this.w * scalar);
    }

    public void setAtIndex(int index, double element) {
        if(index == 0) {
            this.x = element;
        }
        else if(index == 1) {
            this.y = element;
        }
        else if(index == 2) {
            this.z = element;
        }
        else if(index == 3) {
            this.w = element;
        }
    }

    public double getAtIndex(int index) {
        double item = 0;
        if(index == 0) {
            item = this.x;
        }
        else if(index == 1) {
            item = this.y;
        }
        else if(index == 2) {
            item = this.z;
        }
        else {
            item = this.w;
        }
        return item;
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
