package org.example;

public class Vector extends Tuple{

    public Vector() {
        super(0, 0, 0, 0);
    }

    public Vector(double x, double y, double z) {
        super(x, y, z, 0.0);
    }

    public Point add(Point p) {
        return new Point(p.x + this.x, p.y + this.y, p.z + this.z);
    }

    public Vector add(Vector v) {
        return new Vector(v.x + this.x, v.y + this.y, v.z + this.z);
    }

    public Vector subtract(Vector v) {
        return new Vector(v.x - this.x, v.y - this.y, v.z - this.z);
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
    }

    public void normal() {
        this.x = this.x / this.magnitude();
        this.y = this.y / this.magnitude();
        this.z = this.z / this.magnitude();
        this.w = this.w / this.magnitude();
    }

    public double dotProduct(Vector a) {
        return (this.x * a.x +
                this.y * a.y +
                this.z * a.z +
                this.w * a.w);
    }

    public Vector crossProduct(Vector a) {
        return new Vector(this.y * a.z - this.z * a.y,
                          this.z * a.x - this.x * a.z,
                          this.x * a.y - this.y * a.x);
    }
}
