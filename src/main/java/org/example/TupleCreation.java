package org.example;

public class TupleCreation {

    public Tuple point(double x, double y, double z) {
        return new Tuple(x, y, z, 1.0);
    }

    public Tuple vector(double x, double y, double z) {
        return new Tuple(x, y, z, 0.0);
    }
}
