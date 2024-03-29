package org.example;

import java.util.List;

public class Computations {
    private double time;
    private Shape3D object;
    private Tuple point;
    private Tuple eyev;
    private Tuple normalv;
    private Tuple overPoint;

    private boolean inside;

    final double EPSILON = 0.00001;

    public Computations(Intersection intersection, Ray ray) {
        Scene scene = new Scene();
        this.object = intersection.getShape();
        this.time = intersection.getTime();
        this.point = ray.position(ray, this.time);
        this.eyev = ray.getDirection();
        this.eyev.negate();
        // need to make this more abstract for other 3D shapes !!!!!!
        Sphere sphere = (Sphere)object;
        this.normalv = object.normalAt(this.point);
        this.inside = isInside();
        this.overPoint = this.point.add(this.normalv.multiplyScalar(EPSILON));
    }

    private boolean isInside() {
        if(this.normalv.dotProduct(this.eyev) < 0) {
            this.normalv.negate();
            return true;
        }
        else {
            return false;
        }
    }

    public Tuple getOverPoint() {
        return this.overPoint;
    }

    public Tuple getPoint() {
        return this.point;
    }

    public Tuple getEyev() {
        return this.eyev;
    }

    public Tuple getNormalv() {
        return this.normalv;
    }

    public boolean getInside() {
        return this.inside;
    }

    public Shape3D getShape() {
        return this.object;
    }
}
