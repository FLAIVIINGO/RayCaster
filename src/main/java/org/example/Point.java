package org.example;

public class Point extends Tuple{

    public Point() {
        super(0, 0, 0, 1.0);
    }


    public Point(double x, double y, double z) {
        super(x, y, z, 1.0);
    }

    public Point add(Vector v) {return new Point(this.x + v.x, this.y + v.y, this.z + v.z);}

    public Point add(Point p) {
        return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
    }

    public Point subtract(Vector v) {
        return new Point(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Point multiplyScalar(double scalar) {
        return new Point(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar
        );
    }
}
