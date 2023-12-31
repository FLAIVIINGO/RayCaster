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

    public void printTuple() {
        System.out.println("x: "+this.x+" y: "+this.y+" z: "+this.z+" w: "+this.w);
    }

    public Tuple add(Tuple p) {return new Tuple(p.x + this.x, p.y + this.y, p.z + this.z, p.w + this.w);}

    public Tuple subtract(Tuple t) {return new Tuple(this.x - t.x, this.y - t.y, this.z - t.z, this.w - t.w);}

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
    }

    public Tuple normal() {
        return new Tuple(
                this.x / this.magnitude(),
                this.y / this.magnitude(),
                this.z / this.magnitude(),
                this.w / this.magnitude()
        );
    }

    public double dotProduct(Tuple a) {
        return (this.x * a.x +
                this.y * a.y +
                this.z * a.z +
                this.w * a.w);
    }

    public Tuple crossProduct(Tuple a) {
        return new Tuple(this.y * a.z - this.z * a.y,
                this.z * a.x - this.x * a.z,
                this.x * a.y - this.y * a.x, 0);
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
