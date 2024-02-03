package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plane extends Shape3D{

    final double EPSILON = 0.00001;

    public Plane() {
        super();
    }
    @Override
    public Tuple normalAt(Tuple wp) {
        return new Tuple(0, 1, 0, 0);
    }

    @Override
    public List<Intersection> intersect(Ray ray) {
        if(Math.abs(ray.getDirection().getY()) < EPSILON) {
            return new ArrayList<>();
        }
        double t = -ray.getOrigin().getY() / ray.getDirection().getY();
        List<Intersection> xs = new ArrayList<>();
        xs.add(new Intersection(t, this));
        return xs;
    }

}
